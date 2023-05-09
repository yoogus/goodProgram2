package org.example;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MainHandler extends ChannelInboundHandlerAdapter {

    //Клиент подключился
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Клиент подключился");
    }

    //Когда прислали сообщение
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //В буфер приходят сообщения и после этого мы перегоняем сообщения в другой любой тип данных
        ByteBuf buf = (ByteBuf)msg;
        while (buf.readableBytes() > 0) {
            System.out.print((char)buf.readByte());
        }
        //После обработки буфера, обязательно релиз буфера
        buf.release();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
