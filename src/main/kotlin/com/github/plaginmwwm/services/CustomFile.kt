package com.github.plaginmwwm.services

import com.github.plaginmwwm.common.TypeTemplate
import com.github.plaginmwwm.utils.*
import com.intellij.util.ResourceUtil
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths


class CustomFile {
    fun runBaseGenerate(
        typeTemplate: TypeTemplate,
        pathOutput: String,
        newName: String,
        nameProject: String
    ) {
        val files = searchTemplateBaseFile(typeTemplate, pathOutput, newName)
        for (file in files) {
            var textFile = file.readText()
            /// Запускать в replaceTextFile последним
            textFile = replaceImportFile(textFile, pathOutput, nameProject)
            textFile = renameImportFile(textFile, newName)
            textFile = replaceTextFile(textFile, newName)
            file.writeText(textFile)
        }
    }

    private fun searchTemplateBaseFile(
        typeTemplate: TypeTemplate,
        pathOutput: String,
        newName: String,
    ): Set<File> {

        val path = "mwwm_generator" + File.separator + when (typeTemplate) {
            TypeTemplate.widget -> "surf_mwwm_widget"
            TypeTemplate.screen -> "surf_mwwm_screen"
            TypeTemplate.coreMwwm -> "mwwm_widget"
        }

        val files: MutableSet<File> = HashSet()

        val urlWidget = ResourceUtil.getResource(javaClass.classLoader, path, "template.dart")
        val fileWidget = saveFile(urlWidget.readText(), newName.toLowerCase() + ".dart", pathOutput)
        if (fileWidget != null)
            files.add(fileWidget)

        val urlWM = ResourceUtil.getResource(javaClass.classLoader, path, "template_wm.dart")
        val fileWM = saveFile(urlWM.readText(), newName.toLowerCase() + "_wm.dart", pathOutput)
        if (fileWM != null)
            files.add(fileWM)

        val urlComponent = ResourceUtil.getResource(
            javaClass.classLoader,
            path + File.separator + "di",
            "template_component.dart"
        )
        val fileComponent = saveFile(
            urlComponent.readText(),
            newName.toLowerCase() + "_component.dart",
            pathOutput + File.separator + "di"
        )
        if (fileComponent != null)
            files.add(fileComponent)

        if (typeTemplate == TypeTemplate.screen) {
            val urlRoute = ResourceUtil.getResource(javaClass.classLoader, path, "template_route.dart")
            val fileRoute = saveFile(urlRoute.readText(), newName.toLowerCase() + "_route.dart", pathOutput)
            if (fileRoute != null)
                files.add(fileRoute)
        }

        return files
    }


    fun runCustomGenerate(
        dir: File,
        pathGenerator: String,
        pathOutput: String,
        newName: String,
        nameProject: String
    ) {
        val files = searchTemplateCustomFile(dir.path)
        for (file in files) {
            val newPartPath = newRelativeFilePath(file, pathGenerator, newName)
            val newFile = copyFile(file, newPartPath, pathOutput)

            if (newFile != null) {
                var textFile = newFile.readText()
                /// Запускать в replaceTextFile последним
                textFile = replaceImportFile(textFile, pathOutput, nameProject)
                textFile = renameImportFile(textFile, newName)
                textFile = replaceTextFile(textFile, newName)
                newFile.writeText(textFile)
            }
        }
    }


    @Throws(IOException::class)
    private fun searchTemplateCustomFile(dir: String): Set<File> {
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
}

