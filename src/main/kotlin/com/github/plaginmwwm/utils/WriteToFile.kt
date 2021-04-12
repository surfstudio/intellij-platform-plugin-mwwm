package com.github.plaginmwwm.utils

import com.github.plaginmwwm.common.CommonSearchString
import com.github.plaginmwwm.common.SpecialFileNamingEnum
import java.io.*


/// записываем в файл текст
fun writeFile(
    path: String, nameFile: String?, namingEnum: SpecialFileNamingEnum,
    textFile: String
) {
    try {
        val pathNewFile = when (namingEnum) {
            SpecialFileNamingEnum.di -> path + File.separator + CommonSearchString.di + File.separator + nameFile
            else -> path + File.separator + nameFile
        }
        val file = File(pathNewFile)
        File(file.parent).mkdirs() //создаём директории, если отсутствуют
        val fileWriter = FileWriter(file, false)
        fileWriter.write(textFile)
        fileWriter.flush()
    } catch (e: IOException) {
        e.printStackTrace()
    }
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

/// Заменить Template на newValueText
fun replaceTextFile(file: File, newValueText: String) {
    val text = file.readText()
    val newText = text.replace(CommonSearchString.regexSearchWord, newValueText)
    file.writeText(newText)
}

/// Создаём относительный новый путь и имя файла
fun newPartPath(file: File, pathGenerator: String, newName: String): String {
    return file.parent.replace(pathGenerator, "") + File.separator + file.name.replace(
        CommonSearchString.regexSearchWord,
        newName
    )
}