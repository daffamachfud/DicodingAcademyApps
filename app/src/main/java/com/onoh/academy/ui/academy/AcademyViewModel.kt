package com.onoh.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.onoh.academy.data.source.AcademyRepository
import com.onoh.academy.data.source.local.entity.CourseEntity
import com.onoh.academy.utils.DataDummy
import com.onoh.academy.vo.Resource

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getCourses(): LiveData<Resource<PagedList<CourseEntity>>> = academyRepository.getAllCourses()
}