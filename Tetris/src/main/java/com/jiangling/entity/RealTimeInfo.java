package com.jiangling.entity;

import com.jiangling.constant.PatternConstant;
import com.jiangling.util.CommonUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月26日
 * version: v1.0
 */
public class RealTimeInfo {
    private static final int HEIGHT = 16;
    private static final int WIDTH = 10;
    private int patternId;
    private int direction;
    // 基准点坐标，用于旋转的定位
    private int[] mark = new int[2];
    // 记录操作记录
    private OperationLog optLog;

    public RealTimeInfo() {
        randomNew();
    }

    public OperationLog getOptLog() {
        return optLog;
    }

    public void moveOrRotate(String command) {
        optLog.setHasEliminatedLines(false);
        switch (command) {
            case "rotate":
                doRotate();
                break;
            case "left":
                moveLeft();
                break;
            case "right":
                moveRight();
                break;
            case "down":
                moveDown();
                break;
        }
    }

    private void moveLeft() {
        moveLeftOrRight(-1);
    }

    private void moveRight() {
        moveLeftOrRight(1);
    }

    private void moveDown() {
        boolean opt = true;
        // 上次操作完成后的坐标即为当次操作完成前的坐标
        int[][] preLocations = optLog.getAfterOptDropping();
        // 临时坐标，用于保存操作后的坐标
        int[][] temp = new int[preLocations.length][2];
        for (int i = 0; i < preLocations.length; i++) {
            int x = preLocations[i][1];
            int y = preLocations[i][0];
            if (y < HEIGHT - 1 && !existDropped(x, y + 1)) {
                temp[i][0] = y + 1;
                temp[i][1] = x;
            } else {
                opt = false;
                break;
            }
        }
        optLog.setOptSuccess(opt);
        optLog.setPreOptDropping(preLocations);
        if (opt) {
            optLog.setAfterOptDropping(temp);
            optLog.setDropped(false);
            mark[0] += 1;
        } else {
            Map<String, HashSet<Integer>> droppedLocations = optLog.getDroppedLocations();
            Set<Integer[]> updateDroppedLocations = new HashSet<>();
            if (droppedLocations == null) droppedLocations = new HashMap<>();
            for (int[] t : preLocations) {
                int y = t[0];
                int x = t[1];
                HashSet<Integer> xLocations = droppedLocations.get(y + "");
                if (xLocations == null) xLocations = new HashSet<>();
                xLocations.add(x);
                droppedLocations.put(y + "", xLocations);
                updateDroppedLocations.add(new Integer[]{y, x});
            }
            optLog.setDroppedLocations(droppedLocations);
            optLog.setUpdateDroppedLocations(updateDroppedLocations);
            randomNew();
            optLog.setDropped(true);
            optLog.setHasEliminatedLines(eliminate());
        }
    }

    private void doRotate() {
        boolean opt = true;
        // 上次操作完成后的坐标即为当次操作完成前的坐标
        int[][] preLocations = optLog.getAfterOptDropping();
        int[][][] allDirectionPatterns = PatternConstant.ALL.get(patternId);
        int nextDirection = direction + 1 == allDirectionPatterns.length ? 0 : direction + 1;
        int[][] nextDirectionPattern = allDirectionPatterns[nextDirection];
        int yOffset = mark[0] - nextDirectionPattern[nextDirectionPattern.length - 1][0];
        int xOffset = mark[1] - nextDirectionPattern[nextDirectionPattern.length - 1][1];
        int[][] temp = new int[4][2];
        // 最后一个是基准坐标，因此不用遍历到最后一个
        for (int i = 0; i < nextDirectionPattern.length - 1; i++) {
            int y = nextDirectionPattern[i][0] + yOffset;
            int x = nextDirectionPattern[i][1] + xOffset;
            if (y >= HEIGHT || y < 0 || x >= WIDTH || x < 0 || existDropped(x, y)) {
                opt = false;
                break;
            } else {
                temp[i][0] = y;
                temp[i][1] = x;
            }
        }
        optLog.setOptSuccess(opt);
        if (opt) {
            optLog.setPreOptDropping(preLocations);
            optLog.setAfterOptDropping(temp);
            direction = nextDirection;
        }
        optLog.setDropped(false);

    }

    private boolean existDropped(int x, int y) {
        HashSet<Integer> xLocations = optLog.getDroppedLocations().get(y + "");
        if (xLocations == null) return false;
        return xLocations.contains(x);
    }

    private void randomNew() {
        if (optLog == null) optLog = new OperationLog();
        optLog.setGameOver(false);

        patternId = CommonUtil.getRandomInt(0, 6);
        int[][][] randomPatternWithAllDirections = PatternConstant.ALL.get(patternId);
        int directionCount = randomPatternWithAllDirections.length;

        direction = CommonUtil.getRandomInt(0, directionCount - 1);
        int[][] randomDirectionPattern = randomPatternWithAllDirections[direction];
        // 最后一组坐标是基准点坐标，因此无需遍历到最后一个
        int[][] temp = new int[randomDirectionPattern.length - 1][2];
        for (int i = 0; i < randomDirectionPattern.length - 1; i++) {
            int[] location = randomDirectionPattern[i];

            if (existDropped(location[1], location[0])) optLog.setGameOver(true);

            System.arraycopy(location, 0, temp[i], 0, 2);
        }

        mark[0] = randomDirectionPattern[randomDirectionPattern.length - 1][0];
        mark[1] = randomDirectionPattern[randomDirectionPattern.length - 1][1];

        optLog.setPreOptDropping(null);

        optLog.setAfterOptDropping(temp);

        optLog.setOptSuccess(true);

        optLog.setDropped(false);


    }

    private boolean eliminate() {
        Map<String, HashSet<Integer>> droppedLocations = optLog.getDroppedLocations();
        if (droppedLocations == null || droppedLocations.size() == 0) return false;
        int eliminateLines = 0;
        int checkLines = droppedLocations.size();
        for (int i = HEIGHT - 1; i >= HEIGHT - checkLines; i--) {
            HashSet<Integer> xValues = droppedLocations.get(i + "");
            if (xValues.size() != WIDTH) {
                if (eliminateLines != 0) {
                    droppedLocations.put(i + eliminateLines + "", xValues);
                    droppedLocations.remove(i + "");
                }
            } else {
                droppedLocations.remove(i + "");
                eliminateLines++;
            }
        }
        if (eliminateLines != 0) optLog.getUpdateDroppedLocations().clear();
        return eliminateLines != 0;
    }

    private void moveLeftOrRight(int leftOrRight) {
        boolean opt = true;
        // 上次操作完成后的坐标即为当次操作完成前的坐标
        int[][] preLocations = optLog.getAfterOptDropping();
        if (preLocations != null) {
            // 临时坐标，用于保存操作后的坐标
            int[][] temp = new int[preLocations.length][2];
            for (int i = 0; i < preLocations.length; i++) {
                int x = preLocations[i][1];
                int y = preLocations[i][0];
                if ((leftOrRight < 0 ? (x - 1 >= 0) : (x + 1 <= WIDTH - 1)) && !existDropped(x + leftOrRight, y)) {
                    temp[i][0] = y;
                    temp[i][1] = x + leftOrRight;
                } else {
                    opt = false;
                    break;
                }
            }
            optLog.setOptSuccess(opt);
            if (opt) {
                optLog.setPreOptDropping(preLocations);
                optLog.setAfterOptDropping(temp);
                mark[1] += leftOrRight;
            }
            optLog.setDropped(false);
        }
    }
}
