package com.github.plaginmwwm.common.path

import com.github.plaginmwwm.common.CommonSearchString
import com.github.plaginmwwm.common.FileNaming
import java.io.File

/// Путь до ресурсов Screen
object RecourseScreen {
    private val baseResources = "mwwm_generator" + File.separator  + "surf_mwwm_screen"
    val screen = baseResources + File.separator + CommonSearchString.temp + FileNaming.widget
    val wm = baseResources + File.separator + CommonSearchString.temp + FileNaming.wm
    val route = baseResources + File.separator + CommonSearchString.temp + FileNaming.route
    val di = baseResources + File.separator + CommonSearchString.di + File.separator + CommonSearchString.temp + FileNaming.di
}