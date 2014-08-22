/**
 * Project Name:ExamplesOfNetty
 * File Name:TimeClientHandler.java
 * Package Name:com.popo.ex05_Netty
 * Date:2014年8月19日下午8:02:51
 * Copyright (c) 2014, liutianhao.pro@gmail.com All Rights Reserved.
 *
 */

package com.popo.ex05_Netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * ClassName:TimeClientHandler <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014年8月19日 下午8:02:51 <br/>
 * @author liutianhao.pro@gmail.com
 * @version
 * @since JDK 1.7
 * @see
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());
    
    private int counter;
    private byte[] req;
    
    public TimeClientHandler() {
        req = ("QUERY TIME ORDEY" + System.getProperty("line.separator")).getBytes();
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        int length = req.length;
        for (int i = 0; i < 100; ++i) {
            message = Unpooled.buffer(length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }

    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte [] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req, "UTF-8");
        String body = (String) msg;
        System.out.println("Now is : " + body + " ; the counter is : " + ++counter);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // 释放资源
        logger.warning("Unexpected exception from downstream : " + cause.getMessage());
        ctx.close();
    }
}

	