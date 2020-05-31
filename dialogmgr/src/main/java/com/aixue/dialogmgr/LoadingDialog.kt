package com.aixue.dialogmgr

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.aixue.dialogmgr.BaseDialogBuilder.Companion.DIALOG_TYPE_LOADING

open class LoadingDialog constructor(context: Context) : BaseDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 这里获取bundle
        setContentView(R.layout.layout_dialog_loading)
        window?.setBackgroundDrawable(null)
    }

     open class LoadingDialogBuilder : BaseDialogBuilder() {
        override fun getType(): Int {
            return DIALOG_TYPE_LOADING
        }

        override fun createDialog(context: Context, bundle: Bundle?): Dialog {
            return LoadingDialog(context)
        }
    }
}