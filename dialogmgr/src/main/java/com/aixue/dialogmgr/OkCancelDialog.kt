package com.aixue.dialogmgr

import android.app.Dialog
import android.content.Context
import android.os.Bundle

class OkCancelDialog(context: Context) : Dialog(context) {

    class OkCancelDialogBean : BaseDialogBuilder() {
        override fun createDialog(context: Context, bundle: Bundle?): Dialog {
            return OkCancelDialog(context)
        }
    }
}