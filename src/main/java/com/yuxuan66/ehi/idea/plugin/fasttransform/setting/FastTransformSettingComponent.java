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
package com.yuxuan66.ehi.idea.plugin.fasttransform.setting;

import cn.hutool.core.convert.Convert;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class FastTransformSettingComponent implements SearchableConfigurable {
    //智能推荐最大数量
    public int maxCount;
    private Setting setting;
    //持久化保存
    private FastTransformState fastTransformState = FastTransformState.getInstance();

    @NotNull
    @Override
    public String getId() {
        return "FastTransform";
    }



    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "FastTransform";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "Yuxuan";
    }


    @Nullable
    @Override
    public JComponent createComponent() {
        if (setting == null) {
            setting = new Setting();
        }
        return setting.getContentPane();
    }

    @Override
    public boolean isModified() {
        return setting != null && !Convert.toInt(setting.getTextField1().getText(),5).equals(FastTransformState.getInstance().getMaxCount());
    }

    @Override
    public void apply() throws ConfigurationException {
        fastTransformState.setMaxCount(Convert.toInt(setting.getTextField1().getText(),5));
    }

    @Override
    public void reset() {
        setting.getTextField1().setText(String.valueOf(fastTransformState.getMaxCount()));
    }




}
