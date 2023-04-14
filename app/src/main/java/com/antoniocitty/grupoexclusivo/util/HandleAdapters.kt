package com.antoniocitty.grupoexclusivo.util

import com.antoniocitty.grupoexclusivo.model.courses.CourseGE

object HandleAdapters {

    fun handleLockUnlockCourseGE(
        course: CourseGE
    ): CourseGE {
        var allModulesUnlockeds = true
        var currentModuleAssigned = false

        for (module in course.moduleGE) {
            var allStepsUnlockeds = true
            var currentStepAlreadyAssigned = false

            for (step in module.steps) {
                val checklistCompleted = step.checklist.all { it.completed == true }

                if (checklistCompleted && !currentStepAlreadyAssigned) {
                    step.completed = true
                } else {
                    allStepsUnlockeds = false

                    if (!currentStepAlreadyAssigned) {
                        step.completed = null
                        currentStepAlreadyAssigned = true
                    } else {
                        step.completed = false
                    }
                }
            }

            if (allStepsUnlockeds && !currentModuleAssigned) {
                module.completed = true
            } else {
                allModulesUnlockeds = false

                if (!currentModuleAssigned) {
                    module.completed = null
                    currentModuleAssigned = true
                } else {
                    module.completed = false
                }
            }
        }

        if (allModulesUnlockeds) {
            course.moduleGE.last().completed = true
        }

        return course
    }

}