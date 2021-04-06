package com.github.plaginmwwm.common.path

import com.github.plaginmwwm.common.CommonString
import com.github.plaginmwwm.common.FileNaming
import java.io.File

object RecourseCoreMwwm {
    private const val baseResources = "template_core_widget"
    val widget = baseResources + File.separator + CommonString.temp + FileNaming.widget
    val wm = baseResources + File.separator + CommonString.temp + FileNaming.wm
    val di = baseResources + File.separator + CommonString.di + File.separator + CommonString.temp +
            FileNaming.di
}