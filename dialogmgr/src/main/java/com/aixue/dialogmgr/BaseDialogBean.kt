package com.aixue.dialogmgr

import android.app.Dialog
import android.content.Context
import androidx.fragment.app.FragmentActivity
import java.io.Serializable


open class BaseDialogBean :  Serializable {
     fun createDialog(): Dialog {
        var context: Context? = null
        return LoadingDialog(context!!)
    }

    private var mBaseDialogFragment: BaseDialogFragment? = null
    fun createBaseDialogFragment(mContext: Context): BaseDialogFragment {
        var baseDialogFragment = BaseDialogFragment()

        baseDialogFragment.show((mContext as FragmentActivity).supportFragmentManager, "")
        mBaseDialogFragment = baseDialogFragment
        return baseDialogFragment
    }

    var mType = 0


    fun dismiss() {
        mBaseDialogFragment?.dismissAllowingStateLoss()
    }

}