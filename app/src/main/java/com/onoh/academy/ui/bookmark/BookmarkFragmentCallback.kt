package com.onoh.academy.ui.bookmark

import com.onoh.academy.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
