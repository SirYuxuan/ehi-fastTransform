package com.yuxuan66.ehi.idea.plugin.fasttransform.utils;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DefaultActionGroup;

public class ActionGroupUtil {
    private ActionGroupUtil() {
    }


    public static DefaultActionGroup getActionGroup(String actionId) {

        DefaultActionGroup actionGroup = (DefaultActionGroup) ActionManager.getInstance().getAction(actionId);
        actionGroup.removeAll();

        return actionGroup;
    }
}
