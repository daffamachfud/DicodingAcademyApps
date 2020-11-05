package com.onoh.academy.ui.bookmark

import com.onoh.academy.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
