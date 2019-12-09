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
package com.yuxuan66.ehi.idea.plugin.fasttransform.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yuxuan66.ehi.idea.plugin.fasttransform.setting.FastTransformState;

import java.util.ArrayList;
import java.util.List;

/**
 * 变量名智能推荐
 *
 * @author Sir丶雨轩
 */
public class CodeIfUtil {
    private static final String API_PATH = "https://fanyi.youdao.com/openapi.do?keyfrom=Codelf&key=2023743559&type=data&doctype=json&version=1.1&q=";

    /**
     * 变量名智能推荐
     *
     * @param q
     * @return
     */
    public static List<String> intelligentRecommendation(String q) {
        List<String> result = new ArrayList<>();
        try {
            int maxCount = FastTransformState.getInstance().getMaxCount();
            String jsonStr = HttpUtil.get(API_PATH + URLUtil.encode(q));
            JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
            JSONArray jsonArray = jsonObject.getJSONArray("web");
            if (!jsonArray.isEmpty()) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject tmp = jsonArray.getJSONObject(i);
                    JSONArray resultArr = tmp.getJSONArray("value");
                    for (int i1 = 0; i1 < resultArr.size(); i1++) {
                        if (result.size() >= maxCount) {
                            return result;
                        }
                        result.add(StrUtil.lowerFirst(StrUtil.toCamelCase(resultArr.getStr(i1).replaceAll(" ", "_"))));
                    }
                }
            }
            return result;
        } catch (Exception e) {

        }
        return result;

    }

}
