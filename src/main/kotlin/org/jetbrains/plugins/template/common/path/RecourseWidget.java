package org.jetbrains.plugins.template.common.path;


import org.jetbrains.plugins.template.common.CommonString;
import org.jetbrains.plugins.template.common.FileNaming;

import java.io.File;

/// Путь до ресурсов Widget
public class RecourseWidget {
    private static final String baseResources = "template_widget";
    public static final  String widget        =
            baseResources + File.separator + CommonString.temp + FileNaming.widget;
    public static final  String wm            =
            baseResources + File.separator + CommonString.temp + FileNaming.wm;
    public static final  String di            =
            baseResources + File.separator + CommonString.di + File.separator + CommonString.temp +
            FileNaming.di;
}
