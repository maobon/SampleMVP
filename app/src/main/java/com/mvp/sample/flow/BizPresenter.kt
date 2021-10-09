package com.mvp.sample.flow

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.mvp.sample.mvp.BaseMvpPresenterImpl
import kotlin.concurrent.thread

class BizPresenter(mActivity: Activity) :
    BaseMvpPresenterImpl<BizContract.View>(), BizContract.Presenter {

    private val mSp: SharedPreferences = mActivity
        .getSharedPreferences("sp", Context.MODE_PRIVATE)

    override fun onIncreaseCount() {
        mView?.onLoadingBarDisplay()

        thread {
            Thread.sleep(1500)
            var num = mSp.getInt("count", 0)
            num += 1

            if (num >= 1) {
                mView?.enableDecreaseButton()
            }

            mSp.edit().putInt("count", num).apply()

            mView?.onLoadingBarDismiss()
            mView?.onRefreshDisplay(num)
        }
    }

    override fun onDecreaseCount() {
        mView?.onLoadingBarDisplay()

        thread {
            Thread.sleep(1000)
            var num = mSp.getInt("count", 0)
            num -= 1

            if (num <= 0) {
                mView?.disableDecreaseButton()
            } else {
                mView?.enableDecreaseButton()
            }


            mSp.edit().putInt("count", num).apply()

            mView?.onLoadingBarDismiss()
            mView?.onRefreshDisplay(num)
        }
    }

    override fun onCurrentCount() {
        mView?.onLoadingBarDisplay()
        thread {
            val number = mSp.getInt("count", 0)
            if (number == 0) mView?.disableDecreaseButton()

            Thread.sleep(500)
            mView?.onLoadingBarDismiss()
            mView?.onRefreshDisplay(number)
        }
    }
}