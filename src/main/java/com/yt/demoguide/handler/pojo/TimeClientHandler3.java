package com.yt.demoguide.handler.pojo;

import com.yt.demoguide.bean.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class TimeClientHandler3 extends ChannelInboundHandlerAdapter {
    private ByteBuf buf;


    /**
     * 循环阅读消息 次数来自decode 中 list[object] 长度
     *  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
     *                 callDecode(ctx, cumulation, out);
     *                 ...
     *                 for (int i = 0; i < size; i ++) { //循环调用 次数来源于 decode的大小
     *                     ctx.fireChannelRead(out.get(i));
     *                 }
     *                 ...
     *             }
     * @param ctx
     * @param msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        UnixTime msg1 = (UnixTime) msg;
        System.out.println(msg1.toString());
        ctx.close();
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}