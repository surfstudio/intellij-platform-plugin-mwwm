// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.github.plaginmwwm.action

import com.github.plaginmwwm.common.TemplateType
import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent


/**
 * Demonstrates adding an action group to a menu statically in plugin.xml, and then creating a menu item within
 * the group at runtime. See plugin.xml for the declaration of [DynamicActionGroup], and note the group
 * declaration does not contain an action. [DynamicActionGroup] is based on [ActionGroup] because menu
 * children are determined on rules other than just positional constraints.
 *
 * @see ActionGroup
 */
class DynamicActionGroup : ActionGroup() {
    /**
     * Returns an array of menu actions for the group.
     *
     * @param e Event received when the associated group-id menu is chosen.
     * @return AnAction[] An instance of [AnAction], in this case containing a single instance of the
     * [PopupDialogAction] class.
     */
    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
        /**
         * Выбор в меню
         */
        return arrayOf(
            PopupDialogAction("Create widget", "Create widget", null, TemplateType.widget),
            PopupDialogAction("Create screen", "Create screen", null, TemplateType.screen),
            PopupDialogAction("Create CoreMwwmWidget", "Create CoreMwwmWidget", null, TemplateType.coreMwwm),
        )
    }
}