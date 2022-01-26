package com.yt.demoguide.handler.pojo;

import com.yt.demoguide.bean.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler2 extends ChannelHandlerAdapter {


    @Override
    public void channelActive( ChannelHandlerContext ctx) { // (1)
        final ChannelFuture f = ctx.writeAndFlush(new UnixTime()); // (3)


        f.addListener(ChannelFutureListener.CLOSE);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}