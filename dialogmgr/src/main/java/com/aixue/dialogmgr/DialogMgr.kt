package com.aixue.dialogmgr

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

    private var mLinkedHashMap: LinkedHashMap<String, BaseDialogBuilder> =
        LinkedHashMap<String, BaseDialogBuilder>()

    // 展示
    fun show(bean: BaseDialogBuilder) {
        // 如果等级最大或等于其他等级就立即展示；小于其他等级就不展示
        var isImmediatelyShow = true
        mLinkedHashMap.forEach {
            if (it.value.mLevel > bean.mLevel) {
                isImmediatelyShow = false
            }
        }
        if (isImmediatelyShow) {
            bean.createBaseDialogFragment(mFragmentManager)
        }
        mLinkedHashMap.put(bean.mKey, bean)
    }

    fun dismiss(type: Int) {
        val list = ArrayList<String>()
        mLinkedHashMap.forEach {
            if (it.value.mType == type) {
                list.add(it.key)
            }
        }
        list.forEach {
            mLinkedHashMap.remove(it)?.dismiss()
        }
    }

    fun dismissLoading() {
        dismiss(BaseDialogBuilder.DIALOG_TYPE_LOADING)
    }

    fun dismissOkCancel() {
        dismiss(BaseDialogBuilder.DIALOG_TYPE_OK_CANCEL)
    }

    // 响应
    open fun onDialogDestroy(dialogBuilder: BaseDialogBuilder) {
        if (dialogBuilder.isCloseCurAndOpenNext()) {
            mLinkedHashMap.remove(dialogBuilder.mKey)?.dismiss()
            // 找到最大等级的，如果等级相同，找到位置排在第一位的
            var maxLevel = Int.MIN_VALUE
            var key: String? = null
            mLinkedHashMap.forEach {
                if (it.value.mLevel > maxLevel) {
                    maxLevel = it.value.mLevel
                    key = it.key
                }
            }
            key?.let {
                mLinkedHashMap.get(it)?.createBaseDialogFragment(mFragmentManager)
            }
        } else {
            mLinkedHashMap.remove(dialogBuilder.mKey)?.dismiss()
        }
    }

}