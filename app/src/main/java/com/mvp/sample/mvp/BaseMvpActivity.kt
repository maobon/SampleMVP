package com.mvp.sample.mvp

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mvp.sample.createLoadingDialog

abstract class BaseMvpActivity<V : BaseMvpView, T : BaseMvpPresenter<V>>
    : AppCompatActivity(), BaseMvpView {

    abstract fun getPresenter(): T
    abstract fun initViews()
    abstract fun getLayoutResourceId(): Int

    lateinit var mPresenter: T
    private lateinit var mLoadingBar: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())

        this.mPresenter = getPresenter()
        this.mPresenter.attachView(this as V)

        initViews()
    }

    override fun getActivity(): Activity = this

    override fun onLoadingBarDisplay() {
        runOnUiThread {
            mLoadingBar = createLoadingDialog()
            mLoadingBar.show()
        }
    }

    override fun onLoadingBarDismiss() {
        runOnUiThread {
            if (mLoadingBar.isShowing)
                mLoadingBar.dismiss()
        }
    }

    override fun onToast(string: String) {
        runOnUiThread {
            Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.mPresenter.detachView()
    }
}