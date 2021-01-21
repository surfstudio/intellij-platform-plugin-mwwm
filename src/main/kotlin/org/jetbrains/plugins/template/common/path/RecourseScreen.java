package org.jetbrains.plugins.template.common.path;


import org.jetbrains.plugins.template.common.CommonString;
import org.jetbrains.plugins.template.common.FileNaming;

import java.io.File;

/// Путь до ресурсов Screen
public class RecourseScreen {

    private static final String baseResources = "template_screen";
    public static final  String screen        =
            baseResources + File.separator + CommonString.temp + FileNaming.screen;
    public static final  String wm            =
            baseResources + File.separator + CommonString.temp + FileNaming.wm;
    public static final  String route         =
            baseResources + File.separator + CommonString.temp + FileNaming.route;
    public static final  String di            =
            baseResources + File.separator + CommonString.di + File.separator + CommonString.temp +
            FileNaming.di;
}
