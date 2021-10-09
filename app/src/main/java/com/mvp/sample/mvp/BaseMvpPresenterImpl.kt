package com.mvp.sample.mvp

open class BaseMvpPresenterImpl<V : BaseMvpView> : BaseMvpPresenter<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {
        this.mView = view
    }

    override fun detachView() {
        this.mView = null
    }
}