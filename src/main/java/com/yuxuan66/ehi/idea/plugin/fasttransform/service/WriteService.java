package com.yuxuan66.ehi.idea.plugin.fasttransform.service;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;

/**
 * 写入内容到代码编辑器
 *
 * @author Sir丶雨轩
 */
public class WriteService {
    private WriteService() {
    }

    /**
     * 往代码编辑器插入一段文本
     *
     * @param project  所在项目
     * @param document doc
     * @param start    开始位置
     * @param text     插入的文本
     */
    public static void insertStr(Project project, Document document, int start, String text) {

        WriteCommandAction.runWriteCommandAction(project, () -> document.insertString(start, text));

    }

    /**
     * 替换代码编辑器内的一段文本
     *
     * @param project  所在项目
     * @param document doc
     * @param start    开始位置
     * @param end      结束位置
     * @param text     替换的文本
     */
    public static void replaceStr(Project project, Document document, int start, int end, String text) {

        WriteCommandAction.runWriteCommandAction(project, () -> document.replaceString(start, end, text));

    }
}
