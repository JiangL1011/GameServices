package com.jiangling;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiangling.entity.OperationLog;
import com.jiangling.entity.RealTimeInfo;
import com.jiangling.util.CommonUtil;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void test1() {
        int[] a = {2, 3, 5, 1, 6, 4};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void test2() {
        System.out.println(29 / 10);
    }

    @Test
    public void test3() {
        int[] a = {1, 2, 3, 4};
        CommonUtil.arrayFullAdd(a, 3);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void test4() {
        Map<String, Object> map = new HashMap<>();
//        map.put("patternId", 1);
        RealTimeInfo realTimeInfo = ((JSONObject) JSON.toJSON(map)).toJavaObject(RealTimeInfo.class);
        System.out.println(JSON.toJSONString(realTimeInfo));

    }

    @Test
    public void test5() {
        String json = "{\"patternId\":\"\",\"op\":\"\",\"direction\":\"\",\"droppingGrids\":\"\",\"droppedGrids\":\"\"}";
        JSONObject jsonObject = JSON.parseObject(json);
        RealTimeInfo realTimeInfo = jsonObject.toJavaObject(RealTimeInfo.class);
        System.out.println("FastJson生成的对象为：" + realTimeInfo);
    }

    @Test
    public void test7() {
        Set<Integer> droppedGrids = new HashSet<>();
        droppedGrids.add(1);
        droppedGrids.add(2);
        droppedGrids.add(3);
        droppedGrids.add(4);
        droppedGrids.add(5);
        droppedGrids.add(6);
        droppedGrids.add(7);
        droppedGrids.add(8);
        droppedGrids.add(9);
        droppedGrids.add(10);
        droppedGrids.add(11);
        droppedGrids.add(12);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (Integer grid : droppedGrids) {
            int key = grid / 10;
            List<Integer> list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
                map.put(key, list);
            }
            list.add(grid);
        }
        System.out.println(map);
    }

    @Test
    public void test8() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.entrySet().removeIf(next -> next.getValue() > 3);
        System.out.println(map);
    }

    @Test
    public void test9() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 3);
        map.put(1, 1);
        map.put(2, 2);
        map.put(5, 5);
        map.put(4, 4);
        for (Integer integer : map.keySet()) {
            System.out.println(integer + " " + map.get(integer));
        }
    }

    @Test
    public void test10() {
        Map<Integer, Integer> map = new TreeMap<>((o1, o2) -> -o1.compareTo(o2));
        map.put(0, 3);
        map.put(1, 1);
        map.put(2, 2);
        map.put(5, 5);
        map.put(4, 4);
        System.out.println(map);
    }


    @Test
    public void test12() {
        int[][] arrs = new int[16][10];
        for (int[] arr : arrs) {
            System.out.println(Arrays.toString(arr));
        }
//        CommonUtil.fill2DArrBy0(arrs);
    }

    @Test
    public void test13() {
        for (int i = 0; i < 16; i++) {
            System.out.print("[ ");
            for (int j = 0; j < 10; j++) {
                if (i < 10) System.out.print(" ");
                System.out.print(i + "," + j + " ");
            }
            System.out.println("]");
        }
    }

    @Test
    public void test14() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(4);
        list.add(5);
        System.out.println(list);
    }

    @Test
    public void test16() {
        Map<Integer, HashSet<Integer>> map = null;
        System.out.println(map);
        map = new HashMap<>();
        map.put(1, null);
        System.out.println(map);
        HashSet<Integer> set = map.get(1);
        if (set == null) set = new HashSet<>(Arrays.asList(1, 2));
        set.add(3);
        System.out.println(map);
    }

    @Test
    public void test17() {
        OperationLog operationLog = new OperationLog();
        System.out.println(JSON.toJSONString(operationLog));
        operationLog.isDropped = true;
        System.out.println(JSON.toJSONString(operationLog));
    }

    @Test
    public void test18() {
        Set<int[]> set = new HashSet<>();
        set.add(new int[]{1, 2});
        set.add(new int[]{2, 1});
        System.out.println(set);
        System.out.println(set.add(new int[]{1, 2}));
    }

    @Test
    public void test19() {
        Map<Integer, int[]> map = new HashMap<>();
        map.put(1, new int[]{1, 2, 3});
        System.out.println(JSON.toJSONString(map));
        int[] ints = map.get(1);
        ints[0] = 4;
        System.out.println(JSON.toJSONString(map));

    }

    @Test
    public void test20() {
        int[] a = {1, 2, 3};
        a[0] -= 1;
        System.out.println(Arrays.toString(a));
    }

}
