package com.github.plaginmwwm.services

import com.github.plaginmwwm.common.FileNaming
import com.github.plaginmwwm.common.TemplateType
import com.github.plaginmwwm.common.di
import com.github.plaginmwwm.utils.saveFile
import com.intellij.util.ResourceUtil
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Поиск файлов темплейта
 */
class SearchTemplateFile {

    /**
     * Поиск файлов в кастомном темплейте
     * @param dir директория для поиска файлов темплейта
     */
    @Throws(IOException::class)
    fun searchTemplateCustomFile(dir: String): Set<File> {
        val files: MutableSet<File> = HashSet()
        Files.newDirectoryStream(Paths.get(dir)).use { stream ->
            for (path in stream) {
                if (!Files.isDirectory(path)) {
                    val f = path.toFile()
                    files.add(f)
                } else {
                    files.addAll(searchTemplateCustomFile(path.toString()))
                }
            }
        }
        return files
    }

    /**
     * Поиск файлов base темплейта
     * В плагине файлы находятся в виртуальной директории, более удобного способа их получить не нашел
     * @param templateType - тип темплейта
     * @param pathOutput - директория генерации темплейта
     * @param nameTemplate - имя темплейта
     */
    fun searchTemplateBaseFile(
        templateType: TemplateType,
        pathOutput: String,
        nameTemplate: String,
    ): Set<File> {

        val path = "mwwm_generator" + File.separator + when (templateType) {
            TemplateType.widget -> "surf_mwwm_widget"
            TemplateType.screen -> "surf_mwwm_screen"
            TemplateType.coreMwwm -> "mwwm_widget"
        }

        val files: MutableSet<File> = HashSet()

        val urlWidget = ResourceUtil.getResource(javaClass.classLoader, path, "template.dart")
        val fileWidget = saveFile(urlWidget.readText(), nameTemplate.toLowerCase() + FileNaming.extension, pathOutput)
        if (fileWidget != null)
            files.add(fileWidget)

        val urlWM = ResourceUtil.getResource(javaClass.classLoader, path, "template_wm.dart")
        val fileWM = saveFile(urlWM.readText(), nameTemplate.toLowerCase() + FileNaming.wm, pathOutput)
        if (fileWM != null)
            files.add(fileWM)

        val urlComponent = ResourceUtil.getResource(
            javaClass.classLoader,
            path + File.separator + di,
            "template_component.dart"
        )
        val fileComponent = saveFile(
            urlComponent.readText(),
            nameTemplate.toLowerCase() + FileNaming.component,
            pathOutput + File.separator + di
        )
        if (fileComponent != null)
            files.add(fileComponent)

        if (templateType == TemplateType.screen) {
            val urlRoute = ResourceUtil.getResource(javaClass.classLoader, path, "template_route.dart")
            val fileRoute = saveFile(urlRoute.readText(), nameTemplate.toLowerCase() + FileNaming.route, pathOutput)
            if (fileRoute != null)
                files.add(fileRoute)
        }

        return files
    }
}