package com.aixue.dialogmgr

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

/**
 * 统一做关闭处理;
 * TODO 如果因为旋转导致恢复过来了，此时又弹出新的来，怎么搞？方法就是onDestroy的时候，就会把这个关闭掉。这样的话，就不会有这个问题。
 * 但是问题又来了。如果我想生命周期的销毁的时候，不关闭怎么办呢？且只有自己点的时候，关闭怎么搞呢？那就在onDestroy不发送事件通知；在dimiss的时候发送通知
 */
class BaseDialogFragment : DialogFragment() {

    private var mBaseDialogBuilder: BaseDialogBuilder? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var baseDialogBuilder = arguments?.getSerializable(BaseDialogBuilder.BASE_DIALOG_BEAN)
        if (baseDialogBuilder is BaseDialogBuilder) {
            mBaseDialogBuilder = baseDialogBuilder
            return mBaseDialogBuilder!!.createDialog(this.context!!, arguments)
        }
        return Dialog(this.context!!)
    }


    override fun onDestroy() {
        super.onDestroy()
        DialogLog.instance.debug("BaseDialogFragment","$this onDestroy")
        mBaseDialogBuilder?.let { baseDialogBuilder ->
            this.fragmentManager?.let {
                DialogMgrList.getDialogMgr(it)?.onDialogDestroy(baseDialogBuilder)
            }
        }
    }
}