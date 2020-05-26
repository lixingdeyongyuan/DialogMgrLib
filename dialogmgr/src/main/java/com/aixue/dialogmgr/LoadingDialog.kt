package com.aixue.dialogmgr

import android.app.Dialog
import android.content.Context
import android.os.Bundle

class LoadingDialog constructor(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 这里获取bundle
        setContentView(R.layout.layout_paying_dialog)
        window?.setBackgroundDrawable(null)
    }

    class LoadingDialogBuilder : BaseDialogBuilder() {
        override fun createDialog(context: Context,bundle: Bundle?): Dialog {
            return LoadingDialog(context)
        }
    }
}