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
    // 记录操作记录
    private OperationLog optLog;

    public RealTimeInfo() {
        randomNew();
    }

    public OperationLog getOptLog() {
        return optLog;
    }

    public void setOptLog(OperationLog optLog) {
        this.optLog = optLog;
    }

    public int getPatternId() {
        return patternId;
    }

    public void setPatternId(int patternId) {
        this.patternId = patternId;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void moveLeft() {
        moveLeftOrRight(-1);
    }

    public void moveRight() {
        moveLeftOrRight(1);
    }

    public void moveDown() {
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
        optLog.optSuccess = opt;
        optLog.setPreOptDropping(preLocations);
        if (opt) {
            optLog.setAfterOptDropping(temp);
            optLog.isDropped = false;
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
            optLog.isDropped = true;
        }
    }

    public void doRotate() {

    }

    private boolean existDropped(int x, int y) {
        HashSet<Integer> xLocations = optLog.getDroppedLocations().get(y + "");
        if (xLocations == null) return false;
        return xLocations.contains(x);
    }

    private void randomNew() {
        if (optLog == null) optLog = new OperationLog();

        patternId = CommonUtil.getRandomInt(0, 6);
        int[][][] randomPatternWithAllDirections = PatternConstant.ALL.get(patternId);
        int directionCount = randomPatternWithAllDirections.length;

        direction = CommonUtil.getRandomInt(0, directionCount - 1);
        int[][] randomDirectionPattern = randomPatternWithAllDirections[direction];
        // 最后一组坐标是基准点坐标，因此无需遍历到最后一个
        int[][] temp = new int[randomDirectionPattern.length - 1][2];
        for (int i = 0; i < randomDirectionPattern.length - 1; i++) {
            int[] location = randomDirectionPattern[i];
            System.arraycopy(location, 0, temp[i], 0, 2);
        }

        optLog.setPreOptDropping(null);

        optLog.setAfterOptDropping(temp);

        optLog.optSuccess = true;

        optLog.isDropped = false;
    }

    // 消除
    private void eliminate() {
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
            optLog.optSuccess = opt;
            if (opt) {
                optLog.setPreOptDropping(preLocations);
                optLog.setAfterOptDropping(temp);
            }
            optLog.isDropped = false;
        }
    }
}
