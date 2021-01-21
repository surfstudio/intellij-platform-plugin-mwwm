package org.jetbrains.plugins.template.service

import java.io.IOException
import org.jetbrains.plugins.template.common.path.RecourseScreen
import org.jetbrains.plugins.template.common.SpecialFileNamingEnum
import org.jetbrains.plugins.template.common.path.RecourseWidget
import java.lang.StringBuilder
import org.jetbrains.plugins.template.common.CommonString
import java.io.FileWriter
import org.jetbrains.plugins.template.common.FileNaming
import java.io.File
import java.util.*
import kotlin.collections.HashMap

class TemplateGenerate {
    /// Путь до проекта
    var pathDirectory: String? = null

    @Throws(IOException::class)
    fun run(path: String, pathDirectory: String?, nameClass: String, isScreen: Boolean) {
        this.pathDirectory = pathDirectory
        if (nameClass.isEmpty()) return
        if (isScreen) {
            generateScreen(path, nameClass)
        } else {
            generateWidget(path, nameClass)
        }
    }

    /// Сгенерировать файлы для Screen
    @Throws(IOException::class)
    private fun generateScreen(path: String, nameClass: String) {
        val templateScreenText: String = getTextFile(RecourseScreen.screen)
        val templateWmText: String = getTextFile(RecourseScreen.wm)
        val templateRouteText: String = getTextFile(RecourseScreen.route)
        val templateDiText: String = getTextFile(RecourseScreen.di)

        val hashMapNameFile: MutableMap<SpecialFileNamingEnum, String> = HashMap()

        hashMapNameFile[SpecialFileNamingEnum.screen] =
            getPathName(nameClass, SpecialFileNamingEnum.screen)
        hashMapNameFile[SpecialFileNamingEnum.wm] = getPathName(nameClass, SpecialFileNamingEnum.wm)
        hashMapNameFile[SpecialFileNamingEnum.route] =
            getPathName(nameClass, SpecialFileNamingEnum.route)
        hashMapNameFile[SpecialFileNamingEnum.di] = getPathName(nameClass, SpecialFileNamingEnum.di)

        val screenText: String = createFileDart(
            templateScreenText, nameClass, hashMapNameFile,
            path
        )
        val wmText: String = createFileDart(templateWmText, nameClass, hashMapNameFile, path)
        val routeText: String = createFileDart(
            templateRouteText, nameClass, hashMapNameFile,
            path
        )
        val diText: String = createFileDart(templateDiText, nameClass, hashMapNameFile, path)

        writeFile(
            path, hashMapNameFile[SpecialFileNamingEnum.screen],
            SpecialFileNamingEnum.screen, screenText
        )
        writeFile(
            path, hashMapNameFile[SpecialFileNamingEnum.wm], SpecialFileNamingEnum.wm,
            wmText
        )
        writeFile(
            path, hashMapNameFile[SpecialFileNamingEnum.route],
            SpecialFileNamingEnum.route, routeText
        )
        writeFile(
            path, hashMapNameFile[SpecialFileNamingEnum.di], SpecialFileNamingEnum.di,
            diText
        )
    }

    /// Сгенерировать файлы для Widget
    @Throws(IOException::class)
    private fun generateWidget(path: String, nameClass: String) {
        val templateWidgetText = getTextFile(RecourseWidget.widget)
        val templateWmText = getTextFile(RecourseWidget.wm)
        val templateDiText = getTextFile(RecourseWidget.di)

        val hashMapNameFile: HashMap<SpecialFileNamingEnum, String> = HashMap()

        hashMapNameFile[SpecialFileNamingEnum.widget] =
            getPathName(nameClass, SpecialFileNamingEnum.widget)
        hashMapNameFile[SpecialFileNamingEnum.di] = getPathName(nameClass, SpecialFileNamingEnum.di)
        hashMapNameFile[SpecialFileNamingEnum.wm] = getPathName(nameClass, SpecialFileNamingEnum.wm)

        val screenText: String = createFileDart(
            templateWidgetText, nameClass, hashMapNameFile,
            path
        )
        val wmText: String = createFileDart(templateWmText, nameClass, hashMapNameFile, path)

        val diText: String = createFileDart(templateDiText, nameClass, hashMapNameFile, path)

        writeFile(
            path, hashMapNameFile[SpecialFileNamingEnum.widget],
            SpecialFileNamingEnum.widget, screenText
        )
        writeFile(
            path, hashMapNameFile[SpecialFileNamingEnum.wm], SpecialFileNamingEnum.wm,
            wmText
        )
        writeFile(
            path, hashMapNameFile[SpecialFileNamingEnum.di], SpecialFileNamingEnum.di,
            diText
        )
    }

    /// Получаем содержимое файла в виде строки
    @Throws(IOException::class)
    private fun getTextFile(path: String): String {
        val stringBuffer = StringBuilder()
        val inputStream = this.javaClass
            .classLoader
            .getResourceAsStream(path)
        if (inputStream != null) {
            var i: Int
            while (inputStream.read().also { i = it } != -1) {
                val ch = i.toChar()
                stringBuffer.append(ch)
            }
        }
        return stringBuffer.toString()
    }

    /// создаём текст файла дарт
    private fun createFileDart(
        textFile: String, nameClass: String,
        hashMapPath: Map<SpecialFileNamingEnum, String>, path: String
    ): String {
        /// todo Для прописывания правильных импортов
//        val pathSeparator = path.replace(pathDirectory!!, "") + File.separator
//        val importDi = RecourseImport.getImport(
//                pathSeparator + CommonString.di + File.separator +
//                        hashMapPath[SpecialFileNamingEnum.di])
//        val importScreen = RecourseImport.getImport(
//                pathSeparator + hashMapPath[SpecialFileNamingEnum.screen])
//        val importWm = RecourseImport.getImport(
//                pathSeparator + hashMapPath[SpecialFileNamingEnum.wm])
        var text: String = textFile.replace(CommonString.regexString, nameClass)
        /// todo Для прописывания правильных импортов
//        text = text.replace(CommonString.regexImportDi.toRegex(), importDi)
//        text = text.replace(CommonString.regexImportScreen.toRegex(), importScreen)
//        text = text.replace(CommonString.regexImportWM.toRegex(), importWm)
        /// todo заглушка, пока не решена проблема с импортами
        text = text.replace(CommonString.regexImportDi.toRegex(), "")
        text = text.replace(CommonString.regexImportScreen.toRegex(), "")
        text = text.replace(CommonString.regexImportWM.toRegex(), "")
        return text
    }

    /// записываем в файл текст
    private fun writeFile(
        path: String, nameFile: String?, namingEnum: SpecialFileNamingEnum,
        textFile: String
    ) {
        try {
            val file: File
            file = if (namingEnum === SpecialFileNamingEnum.di) {
                File(
                    path + File.separator + CommonString.di + File.separator + nameFile
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

    /// Имя файла
    private fun getPathName(nameClass: String, naming: SpecialFileNamingEnum): String {
        var nameClass = nameClass
        nameClass = getNameFile(nameClass)
        return when (naming) {
            SpecialFileNamingEnum.screen -> nameClass + FileNaming.screen
            SpecialFileNamingEnum.widget -> nameClass + FileNaming.widget
            SpecialFileNamingEnum.wm -> nameClass + FileNaming.wm
            SpecialFileNamingEnum.route -> nameClass + FileNaming.route
            SpecialFileNamingEnum.di -> nameClass + FileNaming.di
            else -> ""
        }
    }

    /// создаем имя файла дарт
    private fun getNameFile(nameFIle: String): String {
        val answer = StringBuilder()
        val findUpper = nameFIle.toCharArray()
        var i = 0
        while (findUpper.size > i) {
            // пропускаем 1-й символ
            // ascii value in between 65 and 91 is A to Z
            if (i != 0 && findUpper[i] >= 65.toChar() && findUpper[i] <= 91.toChar()) {
                answer.append("_") //adding only uppercase
            }
            answer.append(findUpper[i])
            i++
        }
        return answer.toString()
            .toLowerCase(Locale.ROOT)
    }
}