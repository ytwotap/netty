package com.yt.demoguide.encode.pojo;

import com.yt.demoguide.bean.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TimeEncoder extends MessageToByteEncoder<UnixTime> {
    /**
     * The last task left is to insert a TimeEncoder into the ChannelPipeline on the server side before the TimeServerHandler,
     *当  handler 调用这个的时候 会触发 ctx.writeAndFlush(new UnixTime()); encode 所以 一定要注意 handler 在pipeline 中的顺序 如果 在后面 就钓不到encoder
     * @param ctx
     * @param msg
     * @param out
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) {
        out.writeInt((int) msg.value());
    }
}