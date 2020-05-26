package com.aixue.dialogmgr

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment

/**
 * 统一做关闭处理;
 * TODO 如果因为旋转导致恢复过来了，此时又弹出新的来，怎么搞？方法就是onDestroy的时候，就会把这个关闭掉。这样的话，就不会有这个问题。
 * 但是问题又来了。如果我想生命周期的销毁的时候，不关闭怎么办呢？且只有自己点的时候，关闭怎么搞呢？那就在onDestroy不发送事件通知；在dimiss的时候发送通知
 */
class BaseDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return (arguments?.getSerializable("dialogBean") as BaseDialogBean).createDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("rlog","onCreate $this")
    }

    override fun dismiss() {
        super.dismiss()
        // TODO 自己关闭
    }

    override fun dismissAllowingStateLoss() {
        super.dismissAllowingStateLoss()
        // TODO 自己关闭
    }
    override fun onDestroy() {
        super.onDestroy()
        // TODO 这里要发送关闭事件吗？这里还有个问题是，生命周期的问题
        Log.d("rlog","ondestroy $this")
    }
}