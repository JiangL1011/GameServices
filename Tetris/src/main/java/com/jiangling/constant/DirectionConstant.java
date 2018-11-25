package com.jiangling.constant;

import java.util.HashMap;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018/11/25
 * version: v1.0
 */
public class DirectionConstant {
    private static final int LINE = 0;
    private static final int POSITIVE_L = 1;
    private static final int NEGATIVE_L = 2;
    private static final int POSITIVE_Z = 3;
    private static final int NEGATIVE_Z = 4;
    private static final int HILL = 5;
    private static final int CUBE = 6;

    public static final HashMap<Integer, Integer[][]> DIRECTION = new HashMap<>();

    static {
        DIRECTION.put(LINE, new Integer[][]{{3, 4, 5, 6}, {34, 24, 14, 4}});
        DIRECTION.put(POSITIVE_L, new Integer[][]{{4, 5, 6, 14}, {5, 4, 15, 25}, {16, 14, 15, 6}, {24, 14, 4, 25}});
        DIRECTION.put(NEGATIVE_L, new Integer[][]{{13, 3, 14, 15}, {14, 5, 4, 24}, {6, 5, 4, 16}, {25, 15, 24, 5}});
        DIRECTION.put(POSITIVE_Z, new Integer[][]{{15, 5, 4, 16}, {14, 5, 15, 24}});
        DIRECTION.put(NEGATIVE_Z, new Integer[][]{{15, 6, 14, 5}, {14, 4, 15, 25}});
        DIRECTION.put(HILL, new Integer[][]{{15, 14, 5, 16}, {14, 4, 15, 24}, {5, 4, 6, 15}, {15, 14, 5, 25}});
        DIRECTION.put(CUBE, new Integer[][]{{4, 5, 14, 15}});
    }
}
