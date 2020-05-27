package com.aixue.dialogmgrlib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.aixue.dialogmgr.DialogMgr
import com.aixue.dialogmgr.LoadingDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var mDialogMgr: DialogMgr
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("rlog", "MainActivity  onCreate $this")
        mDialogMgr = DialogMgr(this.supportFragmentManager)
        haha.setOnClickListener {
            //            var dialogFragment = BaseDialogFragment()
//            dialogFragment.show(supportFragmentManager, "test")
            Log.d("rlog", "MainActivity $this show")
            mDialogMgr.show(LoadingDialog.LoadingDialogBuilder())
            Handler().postDelayed({
                Log.d("rlog", "MainActivity $this dismiss")
                mDialogMgr.dismissLoading()

            }, 5000)
//            mDialogMgr.show(OkCancelDialog.OkCancelDialogBean())
//            var builder = AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("最普通dialog")
//                .setMessage("我是最简单的dialog").setPositiveButton("确定（积极）",
//                    DialogInterface.OnClickListener { dialogInterface, i ->
//                        //ToDo: 你想做的事情
//                        Toast.makeText(this@MainActivity, "确定按钮", Toast.LENGTH_LONG).show()
//                    }).setNegativeButton("取消（消极）",
//                    DialogInterface.OnClickListener { dialogInterface, i ->
//                        //ToDo: 你想做的事情
//                        Toast.makeText(this@MainActivity, "关闭按钮", Toast.LENGTH_LONG).show()
//                        dialogInterface.closeCurAndOpenNext()
//                    }).setOnDismissListener {
//                    Log.d("rlog","onDismiss")
//                }
//            builder.create().show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("rlog", "MainActivity onDestroy $this")
        mDialogMgr.onDestroy()
    }
}
