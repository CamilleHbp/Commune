package com.camillebc.commune.utilities

import androidx.appcompat.app.AppCompatActivity
import com.camillebc.commune.utilities.inTransaction

fun AppCompatActivity.addFragment(fragment: androidx.fragment.app.Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.removeFragment(fragment: androidx.fragment.app.Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { remove(fragment) }
}

fun AppCompatActivity.replaceFragment(
    fragment: androidx.fragment.app.Fragment,
    frameId: Int,
    toBackStack: Boolean = false,
    backStackName: String? = null
) {
    if (toBackStack) {
        supportFragmentManager.inTransaction { replace(frameId, fragment).addToBackStack(backStackName) }
    } else {
        supportFragmentManager.inTransaction { replace(frameId, fragment) }
    }
}
