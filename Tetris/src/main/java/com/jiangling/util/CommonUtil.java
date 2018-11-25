package com.jiangling.util;

import java.util.*;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018/11/25
 * version: v1.0
 */
public class CommonUtil {
    private static Random random = new Random();

    /**
     * 左右均为闭区间
     */
    public static int getRandomInt(int from, int to) {
        return random.nextInt(to - from + 1) + from;
    }

    public static int generateRandomPatternId() {
        return getRandomInt(0, 6);
    }

    public static Map<String, List<Integer>> eliminate(Integer[] dropped) {
        Arrays.sort(dropped);
        System.out.println("dropped:" + Arrays.toString(dropped));
        // 要被消除的
        List<Integer> eliminated = new ArrayList<>();
        // 保留不消除的
        List<Integer> reserved = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        boolean b = true;
        for (Integer d : dropped) {
            if (b) {
                if (d % 10 == 0 && hasValue(dropped, d + 9)) {
                    temp.add(d);
                    b = false;
                    continue;
                } else {
                    reserved.add(d);
                }
            } else {
                temp.add(d);
                if (d - temp.get(0) >= 9) {
                    if (temp.size() == 10) {
                        eliminated.addAll(temp);
                    } else {
                        reserved.addAll(temp);
                    }
                    temp.clear();
                    b = true;
                }
            }
        }
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("eliminatedPatterns", eliminated);
        map.put("reservedPatterns", reserved);
        System.out.println(map);
        return map;
    }

    private static boolean hasValue(Integer[] arr, Integer value) {
        for (Integer integer : arr) {
            if (integer.equals(value)) return true;
        }
        return false;
    }

}
