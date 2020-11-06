package com.onoh.academy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.onoh.academy.data.source.AcademyRepository
import com.onoh.academy.data.source.local.entity.CourseEntity
import com.onoh.academy.data.source.local.entity.CourseWithModule
import com.onoh.academy.data.source.local.entity.ModuleEntity
import com.onoh.academy.utils.DataDummy
import com.onoh.academy.vo.Resource
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailCourseViewModelTest {
//    objective test:
//    - Memastikan data course tidak null
//    - Memastikan jumlah data course sesuai dengan yang diharapkan
//    - Memastikan data module tidak null
//    - Memastikan jumlah data module sesuai dengan yang diharapkan

//    Memuat Course:
//    Memanipulasi data ketika pemanggilan data course di kelas repository.
//    Memastikan metode di kelas repository terpanggil.
//    Melakukan pengecekan data course apakah null atau tidak.
//    Membandingkan data course sudah sesuai dengan yang diharapkan atau tidak.

//    Memuat Modules:
//    Memanipulasi data ketika pemanggilan data module di kelas repository.
//    Memastikan metode di kelas repository terpanggil.
//    Melakukan pengecekan data module apakah null atau tidak.
//    Melakukan pengecekan jumlah data module apakah sudah sesuai atau belum.

    private lateinit var viewModel: DetailCourseViewModel
    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<Resource<CourseWithModule>>

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setCourseId(courseId)
    }

    @Test
    fun getCourseWithModule() {
        val dummyCourseWithModule = Resource.success(DataDummy.generateDummyCourseWithModules(dummyCourse, true))
        val course = MutableLiveData<Resource<CourseWithModule>>()
        course.value = dummyCourseWithModule

        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(course)

        viewModel.courseModule.observeForever(observer)

        verify(observer).onChanged(dummyCourseWithModule)
    }
}