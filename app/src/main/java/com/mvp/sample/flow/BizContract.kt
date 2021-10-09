package com.mvp.sample.flow

import com.mvp.sample.mvp.BaseMvpPresenter
import com.mvp.sample.mvp.BaseMvpView

object BizContract {

    interface View : BaseMvpView {

        fun onRefreshDisplay(count: Int)

        fun onReload(count: Int)

        fun disableDecreaseButton()

        fun enableDecreaseButton()
    }

    interface Presenter : BaseMvpPresenter<View> {

        fun onIncreaseCount()

        fun onDecreaseCount()

        fun onCurrentCount()
    }
}