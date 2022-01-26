package com.yt.demodoc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.jar.Attributes;

/**
 * @author: YT
 * @date: 2022/1/26/026
 */
@Slf4j
@ChannelHandler.Sharable
public class ContextTest extends ChannelInboundHandlerAdapter {
    private final AttributeKey<Boolean> auth =
            AttributeKey.valueOf("auth");
    private ByteBuf buf;

    //初始化方法 handler 被增加的时候
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        //all received data should be cumulated into `buf`.  分配初始容量 可以扩容
        buf = ctx.alloc().buffer(4); // (1)
        log.info("invoke handlerAdded ");
//        buf = ctx.alloc().buffer(4);
        ChannelPipeline pipeline = ctx.pipeline();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        buf.release(); // (1) 释放buffer
        buf = null;
        log.info("invoke handlerRemoved ");

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg;

        buf.writeBytes(m); // (2)
        m.release();

        if (buf.isReadable()) { // (3)
            long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

