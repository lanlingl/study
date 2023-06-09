package com.wzw.algorithm.other;

import java.util.*;

/**
 * @date 2021/3/17
 */
public class PatternUtil {

    /**
     * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
     *
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * <p>例子1：
     * 输入：s = "aa" p = "a"，
     * 输出：false，
     * 解释："a" 无法匹配 "aa" 整个字符串。
     * </p>
     * <p>例子2：
     * 输入：s = "aa" p = "a*"，
     * 输出：true，
     * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素,
     * 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * </p>
     *
     * @param s 目标字符串
     * @param p 匹配格式
     * @return 是否匹配
     */
    public static boolean pattern(String s,String p){
        int dotIndex = p.indexOf(".");
        List<String> matches = new LinkedList<>();
        matches.add("");


        return false;
    }




}
