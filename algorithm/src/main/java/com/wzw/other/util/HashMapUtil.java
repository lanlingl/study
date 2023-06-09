package com.wzw.other.util;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * /**
 *
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2023/2/27
 */
public class HashMapUtil {

    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("a","1");

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        map.put("a","1");
        map.putIfAbsent("a", "b");
        map.get("a");
    }
}
