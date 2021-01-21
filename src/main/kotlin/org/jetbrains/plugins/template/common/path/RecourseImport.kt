package org.jetbrains.plugins.template.common.path

/// Получение пути импорта в файле дарт
object RecourseImport {
    fun getImport(pathFile: String): String = "import 'package:$pathFile';"
}