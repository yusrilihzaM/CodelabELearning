package com.b21cap0237.codelabjetpack.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.b21cap0237.codelabjetpack.data.source.remote.RemoteDataSource
import com.b21cap0237.codelabjetpack.utils.DataDummy
import com.b21cap0237.codelabjetpack.utils.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class AcademyRepositoryTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remote)

    private val courseResponses = DataDummy.generateRemoteDummyCourses()
    private val courseId = courseResponses[0].id
    private val moduleResponses = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponses[0].moduleId
    private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses() {
        com.nhaarman.mockitokotlin2.doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCoursesCallback)
                .onAllCoursesReceived(courseResponses)
            null
        }.`when`(remote).getAllCourses(com.nhaarman.mockitokotlin2.any())
        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllCourses())
        com.nhaarman.mockitokotlin2.verify(remote).getAllCourses(com.nhaarman.mockitokotlin2.any())
        assertNotNull(courseEntities)
        assertEquals(courseResponses.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getAllModulesByCourse() {
        com.nhaarman.mockitokotlin2.doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadModulesCallback)
                .onAllModulesReceived(moduleResponses)
            null
        }.`when`(remote).getModules(
            com.nhaarman.mockitokotlin2.eq(courseId),
            com.nhaarman.mockitokotlin2.any()
        )

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))

        com.nhaarman.mockitokotlin2.verify(remote)
            .getModules(com.nhaarman.mockitokotlin2.eq(courseId), com.nhaarman.mockitokotlin2.any())

        assertNotNull(courseEntities)
        assertEquals(moduleResponses.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getBookmarkedCourses() {
        com.nhaarman.mockitokotlin2.doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCoursesCallback)
                .onAllCoursesReceived(courseResponses)
            null
        }.`when`(remote).getAllCourses(com.nhaarman.mockitokotlin2.any())

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses())

        com.nhaarman.mockitokotlin2.verify(remote).getAllCourses(com.nhaarman.mockitokotlin2.any())

        assertNotNull(courseEntities)
        assertEquals(courseResponses.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getContent() {
        com.nhaarman.mockitokotlin2.doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadModulesCallback)
                .onAllModulesReceived(moduleResponses)
            null
        }.`when`(remote).getModules(
            com.nhaarman.mockitokotlin2.eq(courseId),
            com.nhaarman.mockitokotlin2.any()
        )

        com.nhaarman.mockitokotlin2.doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadContentCallback)
                .onContentReceived(content)
            null
        }.`when`(remote).getContent(
            com.nhaarman.mockitokotlin2.eq(moduleId),
            com.nhaarman.mockitokotlin2.any()
        )

        val courseEntitiesContent = LiveDataTestUtil.getValue(academyRepository.getContent(courseId, moduleId))

        com.nhaarman.mockitokotlin2.verify(remote)
            .getModules(com.nhaarman.mockitokotlin2.eq(courseId), com.nhaarman.mockitokotlin2.any())

        com.nhaarman.mockitokotlin2.verify(remote)
            .getContent(com.nhaarman.mockitokotlin2.eq(moduleId), com.nhaarman.mockitokotlin2.any())

        assertNotNull(courseEntitiesContent)
        assertNotNull(courseEntitiesContent.contentEntity)
        assertNotNull(courseEntitiesContent.contentEntity?.content)
        assertEquals(content.content, courseEntitiesContent.contentEntity?.content)
    }

    @Test
    fun getCourseWithModules() {
        com.nhaarman.mockitokotlin2.doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCoursesCallback)
                .onAllCoursesReceived(courseResponses)
            null
        }.`when`(remote).getAllCourses(com.nhaarman.mockitokotlin2.any())

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId))

        com.nhaarman.mockitokotlin2.verify(remote).getAllCourses(com.nhaarman.mockitokotlin2.any())

        assertNotNull(courseEntities)
        assertNotNull(courseEntities.title)
        assertEquals(courseResponses[0].title, courseEntities.title)
    }
}