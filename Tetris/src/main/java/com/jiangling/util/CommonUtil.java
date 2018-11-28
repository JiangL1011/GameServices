package com.jiangling.util;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;

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

    public static void arrayFullAdd(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] += num;
        }
    }

    public static boolean touchLeftSideBoundary(int[] grids) {
        for (int grid : grids) {
            if (grid % 10 == 0) return true;
        }
        return false;
    }

    public static boolean touchRightSideBoundary(int[] grids) {
        for (int grid : grids) {
            if ((grid + 1) % 10 == 0) return true;
        }
        return false;
    }

    public static boolean touchBottomBoundary(int[] grids) {
        for (int grid : grids) {
            if (grid / 10 == 15) return true;
        }
        return false;
    }

    public static boolean touchDroppedGridOnLeftSides(int[] droppingGrids, Set<Integer> droppedGrids) {
        for (int grid : droppingGrids) {
            if (droppedGrids.contains(grid - 1)) return true;
        }
        return false;
    }

    public static boolean touchDroppedGridOnRightSides(int[] droppingGrids, Set<Integer> droppedGrids) {
        for (int grid : droppingGrids) {
            if (droppedGrids.contains(grid + 1)) return true;
        }
        return false;
    }

    public static boolean touchDroppedGridOnBottom(int[] droppingGrids, Set<Integer> droppedGrids) {
        for (int grid : droppingGrids) {
            if (droppedGrids.contains(grid + 10)) return true;
        }
        return false;
    }

}
