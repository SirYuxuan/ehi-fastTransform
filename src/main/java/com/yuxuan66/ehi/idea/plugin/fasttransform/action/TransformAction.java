/**
 * Copyright (c) [2019] [Sir丶雨轩]
 * [fastTransform] is licensed under the Mulan PSL v1.
 * You can use this software according to the terms and conditions of the Mulan PSL v1.
 * You may obtain a copy of Mulan PSL v1 at:
 * http://license.coscl.org.cn/MulanPSL
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT, MERCHANTABILITY OR FIT FOR A PARTICULAR
 * PURPOSE.
 * See the Mulan PSL v1 for more details.
 */
package com.yuxuan66.ehi.idea.plugin.fasttransform.action;

import cn.hutool.core.util.StrUtil;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.yuxuan66.ehi.idea.plugin.fasttransform.utils.CodeIfUtil;
import com.yuxuan66.ehi.idea.plugin.fasttransform.utils.TranslateUtil;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.NotNull;

/**
 * @author Sir丶雨轩
 */
public class TransformAction extends AnAction {


    @Override
    public void actionPerformed(AnActionEvent e) {
        DefaultActionGroup actionGroup = (DefaultActionGroup) ActionManager.getInstance().getAction("TransformActionGroup");
        actionGroup.removeAll();
        final Editor mEditor = e.getData(PlatformDataKeys.EDITOR);
        if (null == mEditor) {
            return;
        }
        final Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        SelectionModel model = mEditor.getSelectionModel();
        final String selectedText = model.getSelectedText();
        if (TextUtils.isEmpty(selectedText)) {
            return;
        }
        int start = model.getSelectionStart();
        int end = model.getSelectionEnd();
        actionGroup.add(new AnAction("ToEnglish") {
            @Override
            public void actionPerformed(AnActionEvent e) {

            }
        });
        actionGroup.add(new AnAction("ToHump") {
            @Override
            public void actionPerformed(AnActionEvent e) {
                WriteCommandAction.runWriteCommandAction(project, () -> {
                            mEditor.getDocument().replaceString(start, end, StrUtil.toCamelCase(selectedText));
                        }
                );
            }
        });
        actionGroup.add(new AnAction("ToUnderline") {
            @Override
            public void actionPerformed(AnActionEvent e) {
                WriteCommandAction.runWriteCommandAction(project, () -> {
                            mEditor.getDocument().replaceString(start, end, StrUtil.toUnderlineCase(selectedText));
                        }
                );
            }
        });
        actionGroup.add(new AnAction("ToUpperCase") {
            @Override
            public void actionPerformed(AnActionEvent e) {
                WriteCommandAction.runWriteCommandAction(project, () -> {
                            mEditor.getDocument().replaceString(start, end, selectedText.toUpperCase());
                        }
                );
            }
        });
        actionGroup.add(new AnAction("ToLowerCase") {
            @Override
            public void actionPerformed(AnActionEvent e) {
                WriteCommandAction.runWriteCommandAction(project, () -> {
                            mEditor.getDocument().replaceString(start, end, selectedText.toLowerCase());
                        }
                );
            }
        });
        ListPopup listPopup = JBPopupFactory.getInstance().createActionGroupPopup("Transform", actionGroup, e.getDataContext(), JBPopupFactory.ActionSelectionAid.SPEEDSEARCH, false);
        listPopup.showInBestPositionFor(e.getDataContext());
    }
}
