package com.yuxuan66.ehi.idea.plugin.fasttransform.action;

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

public class IntelligentRecommendAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        DefaultActionGroup intelligentRecommendationGroup = (DefaultActionGroup) ActionManager.getInstance().getAction("IntelligentRecommendationGroup");
        intelligentRecommendationGroup.removeAll();
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
        for (String text : CodeIfUtil.intelligentRecommendation(selectedText)) {
            intelligentRecommendationGroup.add(new AnAction(text) {
                @Override
                public void actionPerformed(AnActionEvent e) {
                    WriteCommandAction.runWriteCommandAction(project, () -> {
                                mEditor.getDocument().replaceString(start, end, text);
                            }
                    );
                }
            });
        }
        ListPopup listPopup = JBPopupFactory.getInstance().createActionGroupPopup("IntelligentRecommendation", intelligentRecommendationGroup, e.getDataContext(), JBPopupFactory.ActionSelectionAid.SPEEDSEARCH, false);
        listPopup.showInBestPositionFor(e.getDataContext());
    }
}
