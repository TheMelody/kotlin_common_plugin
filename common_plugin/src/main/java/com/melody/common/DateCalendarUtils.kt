package com.melody.common

import android.content.Context
import android.text.format.DateUtils
import com.melody.kotlin.common.R
import java.text.SimpleDateFormat
import java.util.*

object DateCalendarUtils {

    private const val minute = (60 * 1000).toLong()// 1分钟
    private const val hour = 60 * minute// 1小时
    private const val day = 24 * hour// 1天

    /**
     * 返回2天前，1天前，几小时前，几分钟前，刚刚，
     * 月-日(今年)
     * 年-月-日（去年）
     */
    fun getRelativeTimeSpanString(context: Context, showTimeMills: Long, nowTimeMills: Long): String {
        val diffTime = nowTimeMills - showTimeMills
        if (diffTime < minute) {
            return context.getString(R.string.time_just_now)
        } else if (diffTime >= minute) {
            return DateUtils.getRelativeTimeSpanString(showTimeMills, nowTimeMills, DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_NUMERIC_DATE).toString()
        } else if (diffTime >= hour) {
            return DateUtils.getRelativeTimeSpanString(showTimeMills, nowTimeMills, DateUtils.HOUR_IN_MILLIS, DateUtils.FORMAT_NUMERIC_DATE).toString()
        } else if (diffTime >= day && diffTime < day * 2) {
            return context.getString(R.string.time_yesterday)
        }
        val calendar = resetCalendar(Calendar.getInstance())
        return SimpleDateFormat(if (showTimeMills < calendar.timeInMillis) context.getString(R.string.time_date_with_year) else context.getString(R.string.time_date_without_year), Locale.getDefault()).format(Date(showTimeMills))
    }

    private fun resetCalendar(calendar: Calendar): Calendar {
        calendar.set(Calendar.MONTH, 0)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar
    }
}