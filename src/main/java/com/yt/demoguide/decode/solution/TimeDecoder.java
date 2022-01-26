package com.yt.demoguide.decode.solution;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 *
 *1. ByteToMessageDecoder is an implementation of ChannelInboundHandler which makes it easy to deal with the fragmentation issue.
 *2. ByteToMessageDecoder calls the decode() method with an internally maintained cumulative buffer whenever new data is received.
 *3. decode() can decide to add nothing to out when there is not enough data in the cumulative buffer. ByteToMessageDecoder will call decode() again when there is more data received.
 *4. If decode() adds an object to out, it means the decoder decoded a message successfully. ByteToMessageDecoder will discard the read part of the cumulative buffer. Please remember that you don't need to decode multiple messages. ByteToMessageDecoder will keep calling the decode() method until it adds nothing to out.
 * @author: YT
 * @date: 2022/1/25/025
 */

public class TimeDecoder extends ByteToMessageDecoder { // (1) 实现ChannelInboundHandler 并实现decode 方法 解码
    /**
     * 被循环调用
     *  while (in.isReadable()) {  # 可读 调用
     *                 int outSize = out.size();
     *                 int oldInputLength = in.readableBytes();
     *                 decode(ctx, in, out);
     *                 。。。
     *                 }
     * @param ctx
     * @param in
     * @param out
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) { // (2) call this method  when new message
        if (in.readableBytes() < 4) {
            return; // (3)
        }
        //读取指针
        System.out.println("readindex"+in.readerIndex());
        //写入指针
        System.out.println("writeIndex"+in.writerIndex());
        //not need to decode multiple messages.
        out.add(in.readBytes(4)); // (4) read the cumulative buffer.
    }
}