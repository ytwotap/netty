package com.yt.demoguide.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
@Slf4j
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf buf;

    //初始化方法 handler 被增加的时候
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        //all received data should be cumulated into `buf`.
        buf = ctx.alloc().buffer(4); // (1)
        log.info("invoke handlerAdded ");
//        buf = ctx.alloc().buffer(4);
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
        
        if (buf.readableBytes() >= 4) { // (3)
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