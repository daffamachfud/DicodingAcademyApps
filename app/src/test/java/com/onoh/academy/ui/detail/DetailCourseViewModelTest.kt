package com.onoh.academy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.onoh.academy.data.source.AcademyRepository
import com.onoh.academy.data.source.local.entity.CourseEntity
import com.onoh.academy.data.source.local.entity.ModuleEntity
import com.onoh.academy.utils.DataDummy
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
    private val dummyModules = DataDummy.generateDummyModules(courseId)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var courseObserver: Observer<CourseEntity>
    @Mock
    private lateinit var modulesObserver: Observer<List<ModuleEntity>>

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setSelectedCourse(courseId)
    }

    @Test
    fun getCourse() {
        val course = MutableLiveData<CourseEntity>()
        course.value = dummyCourse

        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(course)
        val courseEntity = viewModel.getCourse().value as CourseEntity
        verify(academyRepository).getCourseWithModules(courseId)
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity.courseId)
        assertEquals(dummyCourse.deadline, courseEntity.deadline)
        assertEquals(dummyCourse.description, courseEntity.description)
        assertEquals(dummyCourse.imagePath, courseEntity.imagePath)
        assertEquals(dummyCourse.title, courseEntity.title)

        viewModel.getCourse().observeForever(courseObserver)
        verify(courseObserver).onChanged(dummyCourse)
    }

    @Test
    fun getModules() {
        val module = MutableLiveData<List<ModuleEntity>>()
        module.value = dummyModules

        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(module)
        val moduleEntities = viewModel.getModules().value
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities?.size)

        viewModel.getModules().observeForever(modulesObserver)
        verify(modulesObserver).onChanged(dummyModules)
    }
}