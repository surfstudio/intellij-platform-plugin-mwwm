package com.github.plaginmwwm.services

import com.github.plaginmwwm.common.TemplateType
import com.github.plaginmwwm.utils.*
import java.io.File

/**
 * Генерирует файл темплейта
 */
class GenerateFile {
    private val searchTemplate = SearchTemplateFile()

    /**
     * запускает поиск файлов и вызывает функции для генерации для стандартного темплейта
     * @param templateType тип необходимого темплейта
     * @param pathOutput файл для генерации темплейта
     * @param nameTemplate имя сгенерировано темплейта
     * @param nameProject название проекта, в котором был вызван плагин
     */
    fun runBaseGenerate(
        templateType: TemplateType,
        pathOutput: String,
        nameTemplate: String,
        nameProject: String
    ) {
        val files = searchTemplate.searchTemplateBaseFile(templateType, pathOutput, nameTemplate)
        for (file in files) {
            var textFile = file.readText()
            /// Запускать replaceTextFile последним
            textFile = replaceImportFile(textFile, pathOutput, nameProject)
            textFile = renameImportFile(textFile, nameTemplate)
            textFile = replaceTextFile(textFile, nameTemplate)
            file.writeText(textFile)
        }
    }

    /**
     * запускает поиск файлов и вызывает функции для генерации для кастомного темплейта
     * @param dir директория кастомного темплейта, передаётся как файл, для будущего расширения
     * @param nameInputTemplate директория выбранного темплейта
     * @param pathOutput - директория генерации темплейта
     * @param nameTemplate имя темплейта
     * @param nameProject название проекта, в котором был вызван плагин
     */
    fun runCustomGenerate(
        dir: File,
        nameInputTemplate: String,
        pathOutput: String,
        nameTemplate: String,
        nameProject: String
    ) {
        val files = searchTemplate.searchTemplateCustomFile(dir.path)
        for (file in files) {
            val newPartPath = newRelativeFilePath(file, nameInputTemplate, nameTemplate)
            val newFile = copyFile(file, newPartPath, pathOutput)

            if (newFile != null) {
                var textFile = newFile.readText()
                /// Запускать в replaceTextFile последним
                textFile = replaceImportFile(textFile, pathOutput, nameProject)
                textFile = renameImportFile(textFile, nameTemplate)
                textFile = replaceTextFile(textFile, nameTemplate)
                newFile.writeText(textFile)
            }
        }
    }
}
