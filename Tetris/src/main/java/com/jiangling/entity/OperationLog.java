package com.jiangling.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月27日
 * version: v1.0
 */
public class OperationLog {
    // 操作前的位置
    private int[][] preOptDropping;
    // 操作后的位置
    private int[][] afterOptDropping;
    // 已落下的方块的坐标，key为纵坐标，即行索引，value为横坐标，key用string主要是为了转为JSON字符串后在前台读取时不报错
    private Map<String, HashSet<Integer>> droppedLocations = new HashMap<>();
    // 有变动的已下落的坐标
    private Set<Integer[]> updateDroppedLocations;
    // 该操作是否使方块移动或旋转
    private boolean optSuccess;
    // 该操作是否会使方块停止下落
    private boolean dropped = false;
    // 是否有方块消除
    private boolean hasEliminatedLines;

    public boolean isOptSuccess() {
        return optSuccess;
    }

    public void setOptSuccess(boolean optSuccess) {
        this.optSuccess = optSuccess;
    }

    public boolean isDropped() {
        return dropped;
    }

    public void setDropped(boolean dropped) {
        this.dropped = dropped;
    }

    public boolean isHasEliminatedLines() {
        return hasEliminatedLines;
    }

    public void setHasEliminatedLines(boolean hasEliminatedLines) {
        this.hasEliminatedLines = hasEliminatedLines;
    }

    public int[][] getPreOptDropping() {
        return preOptDropping;
    }

    public void setPreOptDropping(int[][] preOptDropping) {
        this.preOptDropping = preOptDropping;
    }

    public int[][] getAfterOptDropping() {
        return afterOptDropping;
    }

    public void setAfterOptDropping(int[][] afterOptDropping) {
        this.afterOptDropping = afterOptDropping;
    }

    public Map<String, HashSet<Integer>> getDroppedLocations() {
        return droppedLocations;
    }

    public void setDroppedLocations(Map<String, HashSet<Integer>> droppedLocations) {
        this.droppedLocations = droppedLocations;
    }

    public Set<Integer[]> getUpdateDroppedLocations() {
        return updateDroppedLocations;
    }

    public void setUpdateDroppedLocations(Set<Integer[]> updateDroppedLocations) {
        this.updateDroppedLocations = updateDroppedLocations;
    }
}
