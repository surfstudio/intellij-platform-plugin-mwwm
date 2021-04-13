package com.github.plaginmwwm.services

import com.github.plaginmwwm.utils.*
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths


class CustomGenerate {

    fun run(dir: File, pathGenerator: String, pathOutput: String, newValueText: String, nameProject: String) {
        val files = searchTemplateFile(dir.path)
        for (file in files) {
            val newPartPath = newRelativeFilePath(file, pathGenerator, newValueText)
            val newFile = copyFile(file, newPartPath, pathOutput)

            if (newFile != null) {
                var textFile = newFile.readText()
                /// Запускать в replaceTextFile последним
                textFile = replaceImportFile(textFile, pathOutput, nameProject)
                textFile = renameImportFile(textFile, newValueText)
                textFile = replaceTextFile(textFile, newValueText)
                newFile.writeText(textFile)
            }
        }
    }

    @Throws(IOException::class)
    private fun searchTemplateFile(dir: String): Set<File> {
        val fileList: MutableSet<File> = HashSet()
        Files.newDirectoryStream(Paths.get(dir)).use { stream ->
            for (path in stream) {
                if (!Files.isDirectory(path)) {
                    val f = path.toFile()
                    fileList.add(f)
                } else {
                    fileList.addAll(searchTemplateFile(path.toString()))
                }
            }
        }
        return fileList
    }
}

