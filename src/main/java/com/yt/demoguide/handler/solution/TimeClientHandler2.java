package com.yt.demoguide.handler.solution;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class TimeClientHandler2 extends ChannelInboundHandlerAdapter {
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
        ByteBuf m = (ByteBuf) msg;

//        buf.writeBytes(m); // (2)
//        m.release();
        
        if (m.readableBytes() >= 4) { // (3)
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
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