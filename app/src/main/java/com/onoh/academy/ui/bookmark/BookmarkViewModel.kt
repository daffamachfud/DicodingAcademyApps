package com.onoh.academy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.onoh.academy.data.source.AcademyRepository
import com.onoh.academy.data.source.local.entity.CourseEntity
import com.onoh.academy.utils.DataDummy

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getBookmarks() : LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCourses()
}