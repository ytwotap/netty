package com.yt.demoguide.handler.solution;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {


    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        //按照4字节读取
        ByteBuf buffer = ctx.alloc().buffer();
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        time.writeInt(22);
        time.writeInt(33);

        final ChannelFuture f = ctx.writeAndFlush(time); // (3)

        f.addListener( future->{
                assert f == future;
                ctx.close();
            }
        ); // (4)
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}