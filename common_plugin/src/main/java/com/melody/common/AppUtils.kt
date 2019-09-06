package com.melody.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import com.melody.kotlin.common.R
import java.lang.Exception
import kotlin.math.max
import kotlin.math.min

/**
 * 防止类型擦除,reified具体化一个T的类型参数
 */
inline fun <reified T> Any.classCast(): T? = this as? T?

/**
 * 整数for循环
 */
inline fun Int.forEach(value: (Int) -> Unit) {
    for (index in 0 until this) {
        value(index)
    }
}

/**
 * String 转Int
 */
fun String.toIntSafely(defaultValue: Int = 0): Int {
    return try {
        this.toInt()
    } catch (e: Exception) {
        defaultValue
    }
}

/**
 * 隐藏输入法
 */
fun FragmentActivity.hideSoftInput(view: View) {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * dp转px
 */
inline fun <reified T> Context.dp2px(dpValue: Float): T {
    val scale = resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt() as T
}

/**
 * px转dp
 */
inline fun <reified T> Context.px2dp(pxValue: Float): T {
    val scale = resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt() as T
}

/**
 * 返回屏幕的宽度
 */
inline fun <reified T> Context.getScreenWidth(): T {
    val displayMetrics = resources.displayMetrics
    return min(displayMetrics.widthPixels, displayMetrics.heightPixels) as T
}

/**
 * 返回屏幕的高度
 */
inline fun <reified T> Context.getScreenHeight(): T {
    val displayMetrics = resources.displayMetrics
    return max(displayMetrics.widthPixels, displayMetrics.heightPixels) as T
}

/**
 * 是不是pad
 */
inline fun <reified T> Context.isPad(): T = resources.getBoolean(R.bool.isPad) as T

/**
 * 打印设备信息
 */
inline fun <reified T> Context.printDeviceInfo(): String {
    val displayMetrics = resources.displayMetrics
    return "width:${displayMetrics.widthPixels},height:${displayMetrics.heightPixels}," +
            "densityDpi:${displayMetrics.densityDpi},density:${displayMetrics.density}" as T
}