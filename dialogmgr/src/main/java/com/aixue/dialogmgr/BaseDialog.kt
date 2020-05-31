package com.aixue.dialogmgr

import android.app.Dialog
import android.content.Context
import android.os.Bundle

open class BaseDialog constructor(context: Context) :Dialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(null)
        window?.setDimAmount(0.55f)
    }
}