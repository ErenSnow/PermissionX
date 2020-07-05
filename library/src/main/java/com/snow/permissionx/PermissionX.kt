package com.snow.permissionx

import androidx.fragment.app.FragmentActivity

object PermissionX {

    private const val TAG = "InvisibleFragment"

    fun request(
        activity: FragmentActivity,
        vararg permissions: String,
        callback: PermissionCallback
    ) {
        val fragmentManager = activity.supportFragmentManager
        val fFragmentByTag = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (fFragmentByTag != null) {
            fFragmentByTag as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        // permissions实际上是一个数组，不能够将它直接传递给另一个接收可变参数的方法，前面加上*号代表将数组转换成可变参数后传递过去
        fragment.requestNow(callback, *permissions)
    }
}