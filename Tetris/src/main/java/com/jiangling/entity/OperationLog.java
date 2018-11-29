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
    // 已落下的方块的坐标，key为纵坐标，即行索引，value为横坐标
    private Map<String, HashSet<Integer>> droppedLocations = new HashMap<>();
    // 有变动的已下落的坐标
    private Set<Integer[]> updateDroppedLocations;
    // 该操作是否使方块移动或旋转
    public boolean optSuccess;
    // 该操作是否会使方块停止下落
    public boolean isDropped = false;


    public int[][] getPreOptDropping() {
        return preOptDropping;
    }

    void setPreOptDropping(int[][] preOptDropping) {
        this.preOptDropping = preOptDropping;
    }

    int[][] getAfterOptDropping() {
        return afterOptDropping;
    }

    void setAfterOptDropping(int[][] afterOptDropping) {
        this.afterOptDropping = afterOptDropping;
    }

    Map<String, HashSet<Integer>> getDroppedLocations() {
        return droppedLocations;
    }

    void setDroppedLocations(Map<String, HashSet<Integer>> droppedLocations) {
        this.droppedLocations = droppedLocations;
    }

    public Set<Integer[]> getUpdateDroppedLocations() {
        return updateDroppedLocations;
    }

    void setUpdateDroppedLocations(Set<Integer[]> updateDroppedLocations) {
        this.updateDroppedLocations = updateDroppedLocations;
    }
}
