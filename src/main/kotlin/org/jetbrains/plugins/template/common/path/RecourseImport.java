package org.jetbrains.plugins.template.common.path;

public class RecourseImport {
    public static String getImport(String pathFile) {
        return "import 'package:" + pathFile + "';";
    }
}
