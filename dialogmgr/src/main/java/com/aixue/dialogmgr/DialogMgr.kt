package com.aixue.dialogmgr

import androidx.fragment.app.FragmentManager


/**
 * 作用：
 * 1.常用弹窗（loading ok ok-cancel），展示以及
 * 2.控制弹窗的弹出顺序
 */
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
        DialogLog.instance.debug("DialogMgr", "$this show BaseDialogBuilder=$bean")
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
        DialogLog.instance.debug("DialogMgr", "$this dismiss type =$type")
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

    fun showLoading(){
        show(LoadingDialog.LoadingDialogBuilder())
    }

    fun dismissLoading() {
        dismiss(BaseDialogBuilder.DIALOG_TYPE_LOADING)
    }

    fun dismissOkCancel() {
        dismiss(BaseDialogBuilder.DIALOG_TYPE_OK_CANCEL)
    }

    // 响应
    open fun onDialogDestroy(dialogBuilder: BaseDialogBuilder) {
        DialogLog.instance.debug(
            "DialogMgr",
            "$this onDialogDestroy() isCloseCurAndOpenNext=${dialogBuilder.isCloseCurAndOpenNext()}"
        )
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