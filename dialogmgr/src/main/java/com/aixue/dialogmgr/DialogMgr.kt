package com.aixue.dialogmgr

import android.content.Context

// 作用是什么？为什么这么写？如果写在同一个类中，类会爆炸？不写在同一个类中，又如何实现
// 可以写出一个共有的类，loading confirm—cancel confirm 弹窗
// 控制弹窗的弹出顺序

class DialogMgr {
    // 实例与展示

    private lateinit var mContext: Context

    private var mList: ArrayList<BaseDialogBean> = ArrayList<BaseDialogBean>()


    fun show(bean: BaseDialogBean) {
        // 这里有顺序；如果是同一个level的话，level越大越厉害。就会弹在最上面。如果小于这个level，就会被放在后边

        // 这里添加以后
        mList.add(bean)
        // 这里是创建
        bean.createBaseDialogFragment(mContext)
    }

    // 关闭当前并打开下一个
    fun closeCurAndOpenNext() {
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