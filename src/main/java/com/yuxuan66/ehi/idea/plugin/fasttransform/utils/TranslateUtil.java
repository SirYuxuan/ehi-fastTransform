
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

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.rjeschke.txtmark.Run;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sir丶雨轩
 * 翻译工具类
 */
public class TranslateUtil {

    private static final String API_PATH = "https://fanyi-api.baidu.com/api/trans/vip/translate";
    private static final String APP_ID = "20190909000333129";
    private static final String APP_SECRET = "63Sqr0u0FWwXCgxe0vsQ";

    /**
     * 语言翻译,从中文到英文
     *
     * @param q 源文本
     * @return 结果
     */
    public static String toEn(String q) {
        return to(q, "zh", "en");
    }

    /**
     * 语言翻译
     *
     * @param q    源文本
     * @param from 原语言
     * @param to   转换后语言
     * @return 结果
     */
    private static String to(String q, String from, String to) {
        Map<String, Object> param = new HashMap<>();
        param.put("q", q);
        param.put("from", from);
        param.put("to", to);
        String salt = String.valueOf(System.currentTimeMillis());
        param.put("salt", salt);
        param.put("appid", APP_ID);
        param.put("sign", SecureUtil.md5(StrUtil.format("{}{}{}{}", APP_ID, q, salt, APP_SECRET)));
        String jsonStr = HttpUtil.post(API_PATH, param);
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        String result = "";
        try {
            result = jsonObject.getJSONArray("trans_result").getJSONObject(0).getStr("dst");
        } catch (RuntimeException e) {

        }
        return StrUtil.isBlank(result) ? q : result;
    }

}
