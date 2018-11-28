package com.jiangling.constant;

import java.util.HashMap;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018/11/25
 * version: v1.0
 */
public class PatternConstant {
    private static final int LINE = 0;
    private static final int POSITIVE_L = 1;
    private static final int NEGATIVE_L = 2;
    private static final int POSITIVE_Z = 3;
    private static final int NEGATIVE_Z = 4;
    private static final int HILL = 5;
    private static final int CUBE = 6;

    public static final HashMap<Integer, int[][][]> ALL = new HashMap<>();

    static {
        /*
         * DIRECTION.get(i).length 可变形数量
         * DIRECTION.get(i)[x][lastIndex] 用于旋转后定位的基准坐标
         * DIRECTION.get(i)[x][y!=lastIndex] 该图案实际占用的位置
         */
        ALL.put(LINE, new int[][][]{
                {{0,3},{0,4},{0,5},{0,6},{0,3}},
                {{0,4},{1,4},{2,4},{3,4},{0,4}}
        });
        ALL.put(POSITIVE_L, new int[][][]{
                {{0,4},{1,4},{2,4},{2,5},{1,5}},
                {{0,4},{0,5},{0,6},{1,4},{1,5}},
                {{0,5},{1,5},{2,5},{0,4},{1,5}},
                {{1,5},{2,5},{2,4},{2,3},{1,5}}
        });
        ALL.put(NEGATIVE_L, new int[][][]{
                {{0,5},{1,5},{2,5},{2,4},{1,4}},
                {{1,4},{2,4},{2,5},{2,6},{1,4}},
                {{0,4},{0,5},{1,4},{2,4},{1,4}},
                {{0,3},{0,4},{0,5},{1,5},{1,4}}
        });
        ALL.put(POSITIVE_Z, new int[][][]{
                {{0,3},{0,4},{1,4},{1,5},{1,4}},
                {{0,5},{1,5},{1,4},{2,4},{1,4}}
        });
        ALL.put(NEGATIVE_Z, new int[][][]{
                {{0,5},{0,4},{1,4},{1,3},{1,4}},
                {{0,4},{1,4},{1,5},{2,5},{1,5}}
        });
        ALL.put(HILL, new int[][][]{
                {{0,4},{1,3},{1,4},{1,5},{1,4}},
                {{0,4},{1,4},{2,4},{1,5},{1,4}},
                {{1,3},{1,4},{1,5},{2,4},{1,4}},
                {{0,4},{1,4},{2,4},{1,3},{1,4}}
        });
        ALL.put(CUBE, new int[][][]{
                {{0,4},{0,5},{1,4},{1,5},{0,4}}
        });
    }
}
