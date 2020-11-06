package br.com.kaz.util

import br.com.kaz.model.courses.CourseKaz

object HandleAdapters {

    fun handleLockUnlockCourseKaz(
        course: CourseKaz
    ): CourseKaz {
        var allModulesUnlockeds = true
        var currentModuleAssigned = false

        for (module in course.moduleKaz) {
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
            course.moduleKaz.last().completed = true
        }

        return course
    }

}