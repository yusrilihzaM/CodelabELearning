package com.b21cap0237.codelabjetpack.reader

import androidx.lifecycle.ViewModel
import com.b21cap0237.codelabjetpack.data.ContentEntity
import com.b21cap0237.codelabjetpack.data.ModuleEntity
import com.b21cap0237.codelabjetpack.data.source.AcademyRepository
import com.b21cap0237.codelabjetpack.utils.DataDummy

import java.util.*

class CourseReaderViewModel (private val academyRepository: AcademyRepository): ViewModel() {

    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }
    fun getModules(): ArrayList<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): ModuleEntity = academyRepository.getContent(courseId, moduleId)
//    fun getModules(): List<ModuleEntity> = DataDummy.generateDummyModules(courseId)
//
//    fun getSelectedModule(): ModuleEntity {
//        lateinit var module: ModuleEntity
//        val moduleEntities = getModules()
//        for (moduleEntity in moduleEntities) {
//            if (moduleEntity.moduleId == moduleId) {
//                module = moduleEntity
//                module.contentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">" + module.title + "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
//                break
//            }
//        }
//        return module
//    }
}

