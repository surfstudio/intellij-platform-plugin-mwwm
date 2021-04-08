package com.github.plaginmwwm.common.path

import com.github.plaginmwwm.common.CommonString
import com.github.plaginmwwm.common.FileNaming
import java.io.File

/// Путь до ресурсов Screen
object RecourseScreen {
    private val baseResources = "mwwm_generator" + File.separator + "templates" + File.separator + "surf_mwwm_screen"
    val screen = baseResources + File.separator + CommonString.temp + FileNaming.widget
    val wm = baseResources + File.separator + CommonString.temp + FileNaming.wm
    val route = baseResources + File.separator + CommonString.temp + FileNaming.route
    val di = baseResources + File.separator + CommonString.di + File.separator + CommonString.temp + FileNaming.di
}