package com.github.plaginmwwm.utils

import com.github.plaginmwwm.common.CommonSearchString
import com.github.plaginmwwm.common.SpecialFileNamingEnum
import java.io.File
import java.io.FileWriter
import java.io.IOException

/// записываем в файл текст
fun writeFile(
    path: String, nameFile: String?, namingEnum: SpecialFileNamingEnum,
    textFile: String
) {
    try {
        val file: File = if (namingEnum === SpecialFileNamingEnum.di) {
            File(
                path + File.separator + CommonSearchString.di + File.separator + nameFile
            )
        } else {
            File(path + File.separator + nameFile)
        }
        File(file.parent).mkdirs() //создаём директории, если отсутствуют
        val fileWriter = FileWriter(file, false)
        fileWriter.write(textFile)
        fileWriter.flush()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}