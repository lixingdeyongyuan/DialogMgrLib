package com.aixue.dialogmgr

import android.util.Log
import androidx.fragment.app.FragmentManager

// 作用是什么？为什么这么写？如果写在同一个类中，类会爆炸？不写在同一个类中，又如何实现
// 可以写出一个共有的类，loading confirm—cancel confirm 弹窗
// 控制弹窗的弹出顺序

class DialogMgr(var mFragmentManager: FragmentManager) {

    init {
        DialogMgrList.addDialogMgr(this)
    }

    fun onDestroy() {
        DialogMgrList.removeDialogMgr(this)
    }

    // 实例与展示
    private var mList: ArrayList<BaseDialogBuilder> = ArrayList<BaseDialogBuilder>()

    fun show(bean: BaseDialogBuilder) {
        // 这里有顺序；如果是同一个level的话，level越大越厉害。就会弹在最上面。如果小于这个level，就会被放在后边
        // 这里添加以后
        mList.add(bean)
        // 这里是创建
        bean.createBaseDialogFragment(mFragmentManager)
    }

    // 关闭当前并打开下一个
    open fun closeCurAndOpenNext(dialogBuilder: BaseDialogBuilder) {
        // 拿到数据，
        var type = 1
        // 这里如何关闭最新的
        var bean = mList.removeAt(0)
        bean.dismiss()
        // 如果还有的话，就去展示新的；有些没有立即展示就不要展示了
    }

    // 关闭哪一个
    fun dismiss(type: Int) {
        mList.forEach {
            if (it.mType == type) {
                it.dismiss()
                return@forEach
            }
        }
    }


}