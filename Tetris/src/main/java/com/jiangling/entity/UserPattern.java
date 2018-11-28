package com.jiangling.entity;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月26日
 * version: v1.0
 */
public class UserPattern {
    public static Map<ChannelHandlerContext, RealTimeInfo> getMap = new ConcurrentHashMap<>();
}
