package Block.Network;

import Block.Block;
import Block.Managers.Users.User;
import Block.Network.Messages.ServerMessage;
import Block.Utils.habbo.HabboFilter;
import Block.Utils.logging.HeaderType;
import Block.Utils.logging.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

/**
 * Created by luis on 23/07/14.
 */
public class ConnectionHandler extends SimpleChannelUpstreamHandler{
    private Channel Socket;
    public static User Habbo;
    private ChannelBuffer Buffer;

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        this.Socket = e.getChannel();
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        if(this.Habbo != null){
            Logger.appendLine(this.getHabbo().getUsername(), HeaderType.DISCONNECT);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();

        if (buffer.readableBytes() < 6) return;

        if(buffer.getByte(0) == 60) {
            buffer.discardReadBytes();
            byte[] Bytes = "<?xml version=\"1.0\"?>\r\n"
                    .concat("<!DOCTYPE cross-domain-policy SYSTEM \"")
                    .concat("/xml/dtds/cross-domain-policy.dtd\">\r\n")
                    .concat("<cross-domain-policy>\r\n   ")
                    .concat("<allow-access-from domain=\"*\" to-ports=\"1-65535\" />\r\n")
                    .concat("</cross-domain-policy>\0").getBytes();
            ChannelBuffer buffer2 = ChannelBuffers.wrappedBuffer(Bytes);
            ChannelFuture future = Socket.write(buffer2);
            future.addListener(ChannelFutureListener.CLOSE);
            return;
        } else {
            while(buffer.readableBytes() != 0) {
                int length = buffer.readInt();
                this.readMessage(buffer.readBytes(length), e);
            }
        }
    }
    public void readMessage(ChannelBuffer Message, MessageEvent e) throws Exception {
        short Header = Message.readShort();
        this.Buffer = Message;

        if (Block.getGame().getRequestMessages().invokeMessage(Header, this))
            Logger.appendLine("[Conocido] [ID: ".concat(String.valueOf(Header)).concat("] : ").concat(getCharFilter(Message)), HeaderType.HABBO);
        else
            Logger.appendLine("[Desconocido] [ID: ".concat(String.valueOf(Header)).concat("] : ").concat(getCharFilter(Message)), HeaderType.HABBO);
    }

    public void Send(ServerMessage Event) {
        try {
            Event.Send(Socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCharFilter(ChannelBuffer Buffer) {
        return HabboFilter.getCharFilter(new String(Buffer.array()));
    }

    public String readString()	{
        byte[] tmp = new byte[this.Buffer.readShort()];
        this.Buffer.readBytes(tmp);
        String output = new String(tmp);
        tmp = null;

        return output;
    }
    public int readInt()
    {
        return this.Buffer.readInt();
    }
    public Boolean readBoolean()
    {
        return this.Buffer.readByte() == 1 ? true : false;
    }

    public Channel getSocket(){
        return this.Socket;
    }
    public User getHabbo() { return this.Habbo; }
    public void setHabbo(User Habbo){
        this.Habbo = Habbo;
    }
}
