package com.shuike.demo

import android.content.res.Resources

val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()