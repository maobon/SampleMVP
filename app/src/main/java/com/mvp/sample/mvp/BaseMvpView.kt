package com.mvp.sample.mvp

import android.app.Activity

interface BaseMvpView {

    fun onToast(string: String)

    fun onLoadingBarDisplay()

    fun onLoadingBarDismiss()

    fun getActivity(): Activity
}