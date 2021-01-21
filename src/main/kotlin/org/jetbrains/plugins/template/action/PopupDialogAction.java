package org.jetbrains.plugins.template.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import icons.SdkIcons;
import org.jetbrains.plugins.template.service.TemplateGenerate;


import javax.swing.*;
import java.io.IOException;

/**
 * Action class to demonstrate how to interact with the IntelliJ Platform.
 * The only action this class performs is to provide the user with a popup dialog as feedback.
 * Typically this class is instantiated by the IntelliJ Platform framework based on declarations
 * in the plugin.xml file. But when added at runtime this class is instantiated by an action group.
 */
public class PopupDialogAction extends AnAction {
    final TemplateGenerate generate = new TemplateGenerate();
    boolean isScreen;

    /**
     * This default constructor is used by the IntelliJ Platform framework to instantiate this class based on plugin.xml
     * declarations. Only needed in {@link PopupDialogAction} class because a second constructor is overridden.
     *
     * @see AnAction#AnAction()
     */


    /**
     * This constructor is used to support dynamically added menu actions.
     * It sets the text, description to be displayed for the menu item.
     * Otherwise, the default AnAction constructor is used by the IntelliJ Platform.
     *
     * @param text        The text to be displayed as a menu item.
     * @param description The description of the menu item.
     * @param icon        The icon to be used with the menu item.
     */
    public PopupDialogAction(@Nullable String text, @Nullable String description,
                             @Nullable Icon icon, boolean isScreen) {
        super(text, description, icon);
        this.isScreen = isScreen;
    }

    /**
     * Gives the user feedback when the dynamic action menu is chosen.
     * Pops a simple message dialog. See the psi_demo plugin for an
     * example of how to use {@link AnActionEvent} to access data.
     *
     * @param event Event received when the associated menu item is chosen.
     */
    @Override public void actionPerformed(@NotNull AnActionEvent event) {

        final String res = Messages.showInputDialog(isScreen ? "Create Screen" : "Create Widget",
                                                    "Surf plugin",
                                                    SdkIcons.mwwm_icon_standart_size);

        final VirtualFile file = event.getDataContext()
                                      .getData(PlatformDataKeys.VIRTUAL_FILE);
        final VirtualFile fileDirectory = event.getDataContext()
                                               .getData(PlatformDataKeys.PROJECT_FILE_DIRECTORY);
        System.out.println("--->>>");
        System.out.println(event.getDataContext()
                                .getData(PlatformDataKeys.PSI_FILE));

        if (file != null && fileDirectory != null && res != null) {
            String path          = getDirectory(file);
            String pathDirectory = getDirectory(fileDirectory);

            try {
                generate.run(path, pathDirectory, res, true);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getDirectory(VirtualFile file) {
        if (!file.isDirectory()) {
            return file.getParent()
                       .getPath();
        }
        return file.getPath();
    }

    //    private ge

    /**
     * Determines whether this menu item is available for the current context.
     * Requires a project to be open.
     *
     * @param e Event received when the associated group-id menu is chosen.
     */
    @Override public void update(AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation()
         .setEnabledAndVisible(project != null);
    }

}
