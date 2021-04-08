package com.github.plaginmwwm.common.path

import com.github.plaginmwwm.common.CommonSearchString
import com.github.plaginmwwm.common.FileNaming
import java.io.File

object RecourseCoreMwwm {
    private val baseResources = "mwwm_generator" + File.separator + "mwwm_widget"
    val widget = baseResources + File.separator + CommonSearchString.temp + FileNaming.widget
    val wm = baseResources + File.separator + CommonSearchString.temp + FileNaming.wm
    val di = baseResources + File.separator + CommonSearchString.di + File.separator + CommonSearchString.temp + FileNaming.di
}
