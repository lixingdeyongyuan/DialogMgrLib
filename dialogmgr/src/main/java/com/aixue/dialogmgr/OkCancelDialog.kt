package com.aixue.dialogmgr

import android.app.Dialog
import android.content.Context
import android.os.Bundle

class OkCancelDialog(context: Context) : BaseDialog(context) {

    class OkCancelDialogBean : BaseDialogBuilder() {
        override fun getType(): Int {
            return DIALOG_TYPE_OK_CANCEL
        }

        override fun createDialog(context: Context, bundle: Bundle?): Dialog {
            return OkCancelDialog(context)
        }
    }
}