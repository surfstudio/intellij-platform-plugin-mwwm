package com.github.plaginmwwm.service

import com.github.plaginmwwm.common.*
import com.github.plaginmwwm.common.path.RecourseCoreMwwm
import com.github.plaginmwwm.common.path.RecourseScreen
import com.github.plaginmwwm.common.path.RecourseWidget
import com.github.plaginmwwm.utils.creatingFileName
import com.github.plaginmwwm.utils.writeFile
import java.io.IOException

import java.lang.StringBuilder

import kotlin.collections.HashMap

class TemplateGenerate {
    /// Путь до проекта
    var pathDirectory: String? = null

    @Throws(IOException::class)
    fun run(path: String, pathDirectory: String?, nameClass: String, typeTemplate: TypeTemplate) {
        this.pathDirectory = pathDirectory
        if (nameClass.trim().isEmpty()) return
        when (typeTemplate) {
            TypeTemplate.widget -> generateWidget(path, nameClass)
            TypeTemplate.screen -> generateScreen(path, nameClass)
            TypeTemplate.coreMwwm -> generateCoreMwwm(path, nameClass)
            /// todo delete
//            TypeTemplate.widget -> gen(path, nameClass)
//            TypeTemplate.screen -> gen(path, nameClass)
//            TypeTemplate.coreMwwm -> gen(path, nameClass)
        }
    }

    @Throws(IOException::class)
    private  fun gen(path: String, nameClass: String){
        println("--->>>");
        println(path);
        println("--->>>");
        println(nameClass);
        println("--->>>");
    }

    /// Сгенерировать файлы для Screen
    @Throws(IOException::class)
    private fun generateScreen(path: String, nameClass: String) {
        val templateScreenText: String = readFile(RecourseScreen.screen)
        val templateWmText: String = readFile(RecourseScreen.wm)
        val templateRouteText: String = readFile(RecourseScreen.route)
        val templateDiText: String = readFile(RecourseScreen.di)

        val hashMapNameFile: MutableMap<SpecialFileNamingEnum, String> = HashMap()

        hashMapNameFile[SpecialFileNamingEnum.widget] = creatingFileName(nameClass, SpecialFileNamingEnum.widget)
        hashMapNameFile[SpecialFileNamingEnum.wm] = creatingFileName(nameClass, SpecialFileNamingEnum.wm)
        hashMapNameFile[SpecialFileNamingEnum.route] = creatingFileName(nameClass, SpecialFileNamingEnum.route)
        hashMapNameFile[SpecialFileNamingEnum.di] = creatingFileName(nameClass, SpecialFileNamingEnum.di)

        val screenText: String = generateTextFileDart(templateScreenText, nameClass, hashMapNameFile, path)
        val wmText: String = generateTextFileDart(templateWmText, nameClass, hashMapNameFile, path)
        val routeText: String = generateTextFileDart(templateRouteText, nameClass, hashMapNameFile, path)
        val diText: String = generateTextFileDart(templateDiText, nameClass, hashMapNameFile, path)

        writeFile(path, hashMapNameFile[SpecialFileNamingEnum.widget], SpecialFileNamingEnum.widget, screenText)
        writeFile(path, hashMapNameFile[SpecialFileNamingEnum.wm], SpecialFileNamingEnum.wm, wmText)
        writeFile(path, hashMapNameFile[SpecialFileNamingEnum.route], SpecialFileNamingEnum.route, routeText)
        writeFile(path, hashMapNameFile[SpecialFileNamingEnum.di], SpecialFileNamingEnum.di, diText)
    }

    /// Сгенерировать файлы для Widget
    @Throws(IOException::class)
    private fun generateWidget(path: String, nameClass: String) {
//        println(RecourseWidget.widget)
//        println(RecourseWidget.wm)
//        println(RecourseWidget.di)
        val templateWidgetText = readFile(RecourseWidget.widget)
        val templateWmText = readFile(RecourseWidget.wm)
        val templateDiText = readFile(RecourseWidget.di)

        val hashMapNameFile: HashMap<SpecialFileNamingEnum, String> = HashMap()

        hashMapNameFile[SpecialFileNamingEnum.widget] = creatingFileName(nameClass, SpecialFileNamingEnum.widget)
        hashMapNameFile[SpecialFileNamingEnum.di] = creatingFileName(nameClass, SpecialFileNamingEnum.di)
        hashMapNameFile[SpecialFileNamingEnum.wm] = creatingFileName(nameClass, SpecialFileNamingEnum.wm)

        val screenText: String = generateTextFileDart(templateWidgetText, nameClass, hashMapNameFile, path)
        val wmText: String = generateTextFileDart(templateWmText, nameClass, hashMapNameFile, path)
        val diText: String = generateTextFileDart(templateDiText, nameClass, hashMapNameFile, path)

        writeFile(path, hashMapNameFile[SpecialFileNamingEnum.widget], SpecialFileNamingEnum.widget, screenText)
        writeFile(path, hashMapNameFile[SpecialFileNamingEnum.wm], SpecialFileNamingEnum.wm, wmText)
        writeFile(path, hashMapNameFile[SpecialFileNamingEnum.di], SpecialFileNamingEnum.di, diText)
    }

    /// Сгенерировать файлы для CoreMwwm
    @Throws(IOException::class)
    private fun generateCoreMwwm(path: String, nameClass: String) {
        val templateWidgetText = readFile(RecourseCoreMwwm.widget)
        val templateWmText = readFile(RecourseCoreMwwm.wm)
        val templateDiText = readFile(RecourseCoreMwwm.di)

        val hashMapNameFile: HashMap<SpecialFileNamingEnum, String> = HashMap()

        hashMapNameFile[SpecialFileNamingEnum.widget] =
            creatingFileName(nameClass, SpecialFileNamingEnum.widget)
        hashMapNameFile[SpecialFileNamingEnum.di] = creatingFileName(nameClass, SpecialFileNamingEnum.di)
        hashMapNameFile[SpecialFileNamingEnum.wm] = creatingFileName(nameClass, SpecialFileNamingEnum.wm)

        val screenText: String = generateTextFileDart(templateWidgetText, nameClass, hashMapNameFile, path)
        val wmText: String = generateTextFileDart(templateWmText, nameClass, hashMapNameFile, path)
        val diText: String = generateTextFileDart(templateDiText, nameClass, hashMapNameFile, path)

        writeFile(path, hashMapNameFile[SpecialFileNamingEnum.widget], SpecialFileNamingEnum.widget, screenText)
        writeFile(path, hashMapNameFile[SpecialFileNamingEnum.wm], SpecialFileNamingEnum.wm, wmText)
        writeFile(path, hashMapNameFile[SpecialFileNamingEnum.di], SpecialFileNamingEnum.di, diText)
    }

    /// Получаем содержимое файла в виде строки
    @Throws(IOException::class)
    private fun readFile(path: String): String {
        println("--->>>")
        println(path)
        println("--->>>")
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
    private fun generateTextFileDart(
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
        var text: String = textFile.replace(CommonSearchString.regexSearchWord, nameClass)
        /// todo Для прописывания правильных импортов
//        text = text.replace(CommonString.regexImportDi.toRegex(), importDi)
//        text = text.replace(CommonString.regexImportScreen.toRegex(), importScreen)
//        text = text.replace(CommonString.regexImportWM.toRegex(), importWm)
        /// todo заглушка, пока не решена проблема с импортами
        text = text.replace(CommonSearchString.regexImportDi.toRegex(), "")
        text = text.replace(CommonSearchString.regexImportScreen.toRegex(), "")
        text = text.replace(CommonSearchString.regexImportWM.toRegex(), "")
        return text
    }
}
