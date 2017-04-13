package com.alqsoft.utils.wechat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;

public class SignUtils {
    private static Logger LOG = LoggerFactory.getLogger(SignUtils.class);

    /**
     * 获得特定的待签名数据
     *
     * @param jsonObject
     * @return
     */
    public static String getSignData(JSONObject jsonObject) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (isNotEmpty(entry.getValue() + "")) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
                resultMap.put(entry.getKey(), entry.getValue());
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        // 排序
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    public static boolean isNotEmpty(String str) {
        if (str != null && !"".equals(str) && !"null".equals(str)) {
            return true;
        } else {
            return false;
        }
    }
}