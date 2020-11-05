package com.onoh.academy.ui.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.onoh.academy.data.source.AcademyRepository
import com.onoh.academy.data.source.local.entity.CourseEntity
import com.onoh.academy.ui.academy.AcademyViewModel
import com.onoh.academy.utils.DataDummy
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {
//    objective test:
//    - Memastikan data course tidak null
//    - Memastikan jumlah data course sesuai dengan yang diharapkan

//    objective tes ke 2
//Memanipulasi data ketika pemanggilan data course di kelas repository.
//Memastikan metode di kelas repository terpanggil.
//Melakukan pengecekan data course apakah null atau tidak.
//Melakukan pengecekan jumlah data course apakah sudah sesuai atau belum.

    private lateinit var viewModel: BookmarkViewModel
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<List<CourseEntity>>

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun getBookmark() {
        val dummyCourses = DataDummy.generateDummyCourses()
        val courses = MutableLiveData<List<CourseEntity>>()
        courses.value = dummyCourses

        `when`(academyRepository.getBookmarkedCourses()).thenReturn(courses)
        val courseEntities = viewModel.getBookmarks().value
        verify(academyRepository).getBookmarkedCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)

        viewModel.getBookmarks().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}