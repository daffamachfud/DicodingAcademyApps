package com.onoh.academy.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.onoh.academy.R
import com.onoh.academy.utils.DataDummy
import com.onoh.academy.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{

//    Scenario Test
//    Menampilkan data kursus academy
//      Memastikan rv_academy dalam keadaan tampil.
//      Gulir rv_academy ke posisi data terakhir.
//    Menampilkan data detail kursus academy
//      Memberi tindakan klik pada data pertama di rv_academy.
//      Memastikan TextView untuk title tampil sesuai dengan yang diharapkan.
//      Memastikan TextView untuk deadline tampil sesuai dengan yang diharapkan.
//    Menampilkan data modul
//      Memberi tindakan klik pada data pertama di rv_academy.
//      Memberi tindakan klik pada btn_start.
//       Memastikan rv_module dalam keadaan tampil.
//    Menampilkan data kursus academy
//      Memberi tindakan klik pada data pertama di rv_academy.
//      Memberi tindakan klik pada btn_start.
//      Memberi tindakan klik pada data pertama di rv_module
//      Memastikan web_view sudah tampil.
//    Menampilkan data bookmark
//       Klik TabLayout dengan teks Bookmark
//      Memastikan rv_module dalam keadaan tampil.
//      Gulir rv_module ke posisi data terakhir.


    private val dummyCourse = DataDummy.generateDummyCourses()

    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadCourses() {

        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourse.size))
    }

    @Test
    fun loadDetailCourse() {
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyCourse[0].title)))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(withText("Deadline ${dummyCourse[0].deadline}")))
    }

    @Test
    fun loadModule() {

        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.btn_start)).perform(click())

        onView(withId(R.id.rv_module)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailModule() {

        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.btn_start)).perform(click())

        onView(withId(R.id.rv_module)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.web_view)).check(matches(isDisplayed()))
    }

    @Test
    fun loadBookmarks() {
        onView(withText("Bookmark")).perform(click())

        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_bookmark)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourse.size))
    }


}