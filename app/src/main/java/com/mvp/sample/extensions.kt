package com.mvp.sample

import android.app.Activity
import android.app.AlertDialog

fun Activity.createLoadingDialog(): AlertDialog =
    AlertDialog.Builder(this)
        .setMessage("Loading")
        .create()
