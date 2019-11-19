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
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "FastTransform", storages = {@Storage(value = "fastTransform.xml")})
public class FastTransformState implements PersistentStateComponent<Element> {

    public static FastTransformState getInstance() {
        return ServiceManager.getService(FastTransformState.class);
    }

    private Integer maxCount;

    public Integer getMaxCount() {
        return maxCount == null ? 5 : maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    @Nullable
    @Override
    public Element getState() {
        Element element = new Element("FastTransform");
        element.setAttribute("maxCount", String.valueOf(getMaxCount()));
        return element;
    }

    @Override
    public void loadState(@NotNull Element state) {
        this.maxCount = Convert.toInt(state.getAttributeValue("maxCount"), 5);
    }
}
