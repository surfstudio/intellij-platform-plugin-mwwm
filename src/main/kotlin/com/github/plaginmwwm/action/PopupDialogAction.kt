package com.github.plaginmwwm.action

import com.github.plaginmwwm.common.TypeTemplate
import com.github.plaginmwwm.services.CustomFile
import com.github.plaginmwwm.utils.getDirectoryTemplate
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import icons.SdkIcons
import java.io.File
import javax.swing.Icon


/**
 * Action class to demonstrate how to interact with the IntelliJ Platform.
 * The only action this class performs is to provide the user with a popup dialog as feedback.
 * Typically this class is instantiated by the IntelliJ Platform framework based on declarations
 * in the plugin.xml file. But when added at runtime this class is instantiated by an action group.
 */
class PopupDialogAction
/**
 * This constructor is used to support dynamically added menu actions.
 * It sets the text, description to be displayed for the menu item.
 * Otherwise, the default AnAction constructor is used by the IntelliJ Platform.
 *
 * @param text        The text to be displayed as a menu item.
 * @param description The description of the menu item.
 * @param icon        The icon to be used with the menu item.
 */(
    text: String?, description: String?, icon: Icon?, var typeTemplate: TypeTemplate,
) : AnAction(text, description, icon) {

    /**
     * Gives the user feedback when the dynamic action menu is chosen.
     * Pops a simple message dialog. See the psi_demo plugin for an
     * example of how to use [AnActionEvent] to access data.
     *
     * @param event Event received when the associated menu item is chosen.
     */
    override fun actionPerformed(event: AnActionEvent) {


        val titleDialog: String = when (typeTemplate) {
            TypeTemplate.widget -> "Create Widget"
            TypeTemplate.screen -> "Create Screen"
            TypeTemplate.coreMwwm -> "Create CoreMwwm"
        }

        val nameNewFiles = Messages.showInputDialog(
            titleDialog,
            "SurfMwwm Gen",
            SdkIcons.mwwm_icon_standart_size,
        )

        val fileDirectory = event.dataContext.getData(PlatformDataKeys.PROJECT_FILE_DIRECTORY)
        val nameProject = event.dataContext.getData(PlatformDataKeys.PROJECT)!!.name

        val pathCustomGenerator =
            fileDirectory?.path + File.separator + "mwwm_generator" + File.separator + "templates" +
                    File.separator + getDirectoryTemplate(typeTemplate)

        val customTemplateFile = File(pathCustomGenerator)

        val file = event.dataContext.getData(PlatformDataKeys.VIRTUAL_FILE)
        val pathOutput = file?.let { getDirectory(it) }

        if (pathOutput != null && nameNewFiles != null && nameNewFiles.trim().isNotEmpty() && fileDirectory != null) {
            if (customTemplateFile.isDirectory) {
                CustomFile().runCustomGenerate(
                    customTemplateFile,
                    pathCustomGenerator,
                    pathOutput,
                    nameNewFiles,
                    nameProject
                )
            } else {
                CustomFile().runBaseGenerate(
                    typeTemplate,
                    pathOutput,
                    nameNewFiles,
                    nameProject
                )
//                val pathDirectory = getDirectory(fileDirectory)
//
//                try {
//                    TemplateGenerate().run(pathOutput, pathDirectory, nameNewFiles, typeTemplate)
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
            }
        }
    }

    private fun getDirectory(file: VirtualFile): String {
        return if (file.isDirectory) {
            file.path
        } else {
            file.parent.path
        }
    }

    /**
     * Determines whether this menu item is available for the current context.
     * Requires a project to be open.
     *
     * @param e Event received when the associated group-id menu is chosen.
     */
    override fun update(e: AnActionEvent) {
        // Set the availability based on whether a project is open
        val project = e.project
        e.presentation.isEnabledAndVisible = project != null
    }
    /**
     * This default constructor is used by the IntelliJ Platform framework to instantiate this class based on plugin.xml
     * declarations. Only needed in [PopupDialogAction] class because a second constructor is overridden.
     *
     * @see AnAction.AnAction
     */
}