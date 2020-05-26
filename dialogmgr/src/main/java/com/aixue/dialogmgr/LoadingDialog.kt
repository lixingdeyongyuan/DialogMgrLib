package com.aixue.dialogmgr

import android.app.Dialog
import android.content.Context
import android.os.Bundle

class LoadingDialog constructor(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 这里获取bundle

    }
//    fun newInstance() {
//        var builder = AlertDialog.Builder(this).setTitle("最普通dialog")
//            .setMessage("我是最简单的dialog").setPositiveButton("确定（积极）",
//                DialogInterface.OnClickListener { dialogInterface, i ->
//                    //ToDo: 你想做的事情
//                    Toast.makeText(this, "确定按钮", Toast.LENGTH_LONG).show()
//                }).setNegativeButton("取消（消极）",
//                DialogInterface.OnClickListener { dialogInterface, i ->
//                    //ToDo: 你想做的事情
//                    Toast.makeText(this, "关闭按钮", Toast.LENGTH_LONG).show()
//                    dialogInterface.closeCurAndOpenNext()
//                }).setOnDismissListener {
//                Log.d("rlog", "onDismiss")
//            }
//        val dialog = builder.create()
//    }

    //
     class LoadingDialogHaha: BaseDialogBean() {

     }
}