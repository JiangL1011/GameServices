package com.jiangling.entity;

import com.jiangling.constant.DirectionConstant;
import com.jiangling.util.CommonUtil;

import static com.jiangling.constant.GameInterfaceSizeConstant.*;
import static com.jiangling.constant.OperationConstant.*;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018/11/25
 * version: v1.0
 */
public class Pattern {
    private int patternId;
    private Integer[] position; // 数组中的第一个数字是中心方块的位置
    private String op;
    private boolean opStatus;
    private int direction;

    public Pattern(int patternId) {
        this.patternId = patternId;
        this.op = "";
        Integer[][] directions = DirectionConstant.DIRECTION.get(patternId);
        this.direction = CommonUtil.getRandomInt(0, directions.length - 1);
        this.position = directions[direction];
        opStatus = true;
    }

    public Pattern(int patternId, Integer[] position, String op, int direction) {
        this.patternId = patternId;
        this.position = position;
        this.op = op;
        this.direction = direction;
    }

    public int getPatternId() {
        return patternId;
    }

    public void setPatternId(int patternId) {
        this.patternId = patternId;
    }

    public Integer[] getPosition() {
        return position;
    }

    public void setPosition(Integer[] position) {
        this.position = position;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public boolean isOpStatus() {
        return opStatus;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setOpStatus(boolean opStatus) {
        this.opStatus = opStatus;
    }

    public boolean operate() {
        if (op.equals(DOWN)) opStatus = moveDown();
        if (op.equals(LEFT)) opStatus = moveLeft();
        if (op.equals(RIGHT)) opStatus = moveRight();
        if (op.equals(ROTATE)) opStatus = rotate();
        return opStatus;
    }

    private boolean moveLeft() {
        Integer[] temp = new Integer[4];
        for (int i = 0; i < position.length; i++) {
            if ((position[i] - TOP_LEFT) % WIDTH == 0) {
                return false;
            }
            temp[i] = position[i] - 1;
        }
        position = temp;
        return true;
    }

    private boolean moveRight() {
        Integer[] temp = new Integer[4];
        for (int i = 0; i < position.length; i++) {
            if ((position[i] + 1 - TOP_LEFT) % WIDTH == 0) {
                return false;
            }
            temp[i] = position[i] + 1;
        }
        position = temp;
        return true;
    }

    private boolean moveDown() {
        Integer[] temp = new Integer[4];
        for (int i = 0; i < position.length; i++) {
            if ((position[i] + WIDTH) > (WIDTH * HEIGHT - (1 - TOP_LEFT))) {
                return false;
            }
            temp[i] = position[i] + 10;
        }
        position = temp;
        return true;
    }

    private boolean rotate() {
        Integer[][] allDirections = DirectionConstant.DIRECTION.get(patternId);
        Integer[] temp = new Integer[4];
        if (direction == allDirections.length - 1) {
            direction = 0;
        } else {
            direction++;
        }
        System.arraycopy(allDirections[direction], 0, temp, 0, 4);
        int offset = position[0] - temp[0];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = temp[i] + offset;
        }
        position = temp;
        return true;
    }
}
