package com.aixue.dialogmgr

import androidx.fragment.app.FragmentManager

object DialogMgrList {
    var mList = ArrayList<DialogMgr>()

    fun addDialogMgr(dialogMgr: DialogMgr) {
        mList.add(dialogMgr)
    }

    fun removeDialogMgr(dialogMgr: DialogMgr) {
        mList.remove(dialogMgr)
    }

    fun getDialogMgr(fragmentManager: FragmentManager): DialogMgr? {
        mList.forEach {
            if (it.mFragmentManager == fragmentManager) {
                return it
            }
        }
        return null
    }
}