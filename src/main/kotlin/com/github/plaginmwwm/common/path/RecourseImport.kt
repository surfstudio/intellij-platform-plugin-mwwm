package com.github.plaginmwwm.common.path

/// Получение пути импорта в файле дарт
object RecourseImport {
    fun getImport(pathFile: String): String = "import 'package:$pathFile';"
}