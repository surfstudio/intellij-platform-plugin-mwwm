package com.github.plaginmwwm.common.path

import com.github.plaginmwwm.common.CommonString
import com.github.plaginmwwm.common.FileNaming
import java.io.File

/// Путь до ресурсов Screen
object RecourseScreen {
    private const val baseResources = "template_screen"
    val screen = baseResources + File.separator + CommonString.temp + FileNaming.screen
    val wm = baseResources + File.separator + CommonString.temp + FileNaming.wm
    val route = baseResources + File.separator + CommonString.temp + FileNaming.route
    val di = baseResources + File.separator + CommonString.di + File.separator + CommonString.temp +
            FileNaming.di
}