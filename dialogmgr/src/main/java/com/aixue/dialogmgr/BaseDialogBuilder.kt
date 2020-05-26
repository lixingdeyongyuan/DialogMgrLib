package com.aixue.dialogmgr

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import java.io.Serializable


open abstract class BaseDialogBuilder : Serializable {

    var mType = 0
    var mLevel = 1
    private var mBaseDialogFragment: BaseDialogFragment? = null

    companion object {
        const val BASE_DIALOG_BEAN = "base_dialog_bean"
    }

    abstract fun createDialog(context: Context, bundle: Bundle?): Dialog

    fun createBaseDialogFragment(fragmentManager: FragmentManager): BaseDialogFragment {
        var baseDialogFragment = BaseDialogFragment()
        val bundle = Bundle()
        bundle.putSerializable(BASE_DIALOG_BEAN, this)
        baseDialogFragment.arguments = bundle
        baseDialogFragment.show(fragmentManager, "")
        mBaseDialogFragment = baseDialogFragment
        return baseDialogFragment
    }


    fun dismiss() {
        mBaseDialogFragment?.dismissAllowingStateLoss()
    }

    open fun isCloseCurAndOpenNext(): Boolean {
        return true
    }

}