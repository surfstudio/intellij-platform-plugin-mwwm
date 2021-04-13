package com.github.plaginmwwm.utils

import com.github.plaginmwwm.common.CommonSearchString
import com.github.plaginmwwm.common.TypeTemplate
import java.io.File

/// Сохранить текст в файл
fun saveFile(text: String, nameFile: String, pathOutput: String): File? {
    try {
        val file = File(pathOutput + File.separator + nameFile)
        file.parentFile.mkdir()
        file.writeText(text)
        return file
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    return null
}

/// Скопировать файл из темплейта
fun copyFile(file: File, newPath: String, pathOutput: String): File? {
    try {
        return file.copyTo(File(pathOutput + newPath), true)
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    return null
}

/// Заменить пути импорта
fun replaceImportFile(textFile: String, pathOutput: String, nameProject: String): String {
    val pathPackage = nameProject + pathOutput.split(nameProject + File.separator + "lib").last()
    return textFile.replace(CommonSearchString.importTemplate, pathPackage, true)
}

/// Переименовать в импорте файл
fun renameImportFile(textFile: String, newValueText: String): String {
    return textFile.replace(CommonSearchString.importFileTemplate, newValueText.toLowerCase(), true)
}

/// Заменить Template на newValueText
fun replaceTextFile(textFile: String, newValueText: String): String {
    return textFile.replace(CommonSearchString.regexSearchWord, newValueText, true)
}

/// Создаём относительный новый путь и имя файла
fun newRelativeFilePath(file: File, pathGenerator: String, newName: String): String {
    return file.parent.replace(pathGenerator, "", true) + File.separator +
            file.name.replace(CommonSearchString.regexSearchWord, newName.toLowerCase(), true)
}

/// Получить папку с нужным template
fun getDirectoryTemplate(typeTemplate: TypeTemplate): String {
    return when (typeTemplate) {
        TypeTemplate.widget -> widget
        TypeTemplate.screen -> screen
        TypeTemplate.coreMwwm -> coreMwwm
    }
}

private const val widget = "surf_mwwm_widget"
private const val screen = "surf_mwwm_screen"
private const val coreMwwm = "mwwm_widget"