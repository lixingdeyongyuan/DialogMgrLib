package com.aixue.dialogmgr

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import java.io.Serializable


open abstract class BaseDialogBuilder(var mKey: String = "") : Serializable {

    companion object {
        const val BASE_DIALOG_BEAN = "base_dialog_bean"
        const val DIALOG_TYPE_LOADING = 1
        const val DIALOG_TYPE_OK_CANCEL = 2
    }

    init {
        mKey = this.javaClass.name + this.hashCode()
    }

    var mType = 0
        get() = getType()
    var mLevel = 1
    private var mBaseDialogFragment: BaseDialogFragment? = null

    abstract fun createDialog(context: Context, bundle: Bundle?): Dialog

    abstract fun getType(): Int

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
        return false
    }

}