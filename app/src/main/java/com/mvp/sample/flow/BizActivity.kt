package com.mvp.sample.flow

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.mvp.sample.R
import com.mvp.sample.mvp.BaseMvpActivity

class BizActivity : BaseMvpActivity<BizContract.View, BizContract.Presenter>(),
    BizContract.View {

    override fun getLayoutResourceId(): Int = R.layout.activity_biz

    override fun getPresenter(): BizContract.Presenter = BizPresenter(getActivity())

    private lateinit var btnIncrease: Button
    private lateinit var btnDecrease: Button
    private lateinit var tvCurr: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.onCurrentCount()
    }

    override fun initViews() {
        btnIncrease = findViewById(R.id.btn_increase)
        btnDecrease = findViewById(R.id.btn_decrease)
        tvCurr = findViewById(R.id.tv_current_count)

        btnIncrease.setOnClickListener(onClickHandler)
        btnDecrease.setOnClickListener(onClickHandler)
    }

    private val onClickHandler = View.OnClickListener {
        when (it.id) {
            R.id.btn_increase -> {
                mPresenter.onIncreaseCount()
            }
            R.id.btn_decrease -> {
                mPresenter.onDecreaseCount()
            }
        }
    }

    override fun onRefreshDisplay(count: Int) {
        runOnUiThread {
            tvCurr.text = count.toString()
        }
    }

    override fun onReload(count: Int) {
        runOnUiThread {
            tvCurr.text = count.toString()
        }
    }

    override fun disableDecreaseButton() {
        runOnUiThread {
            btnDecrease.isEnabled = false
        }
    }

    override fun enableDecreaseButton() {
        runOnUiThread {
            btnDecrease.isEnabled = true
        }
    }
}