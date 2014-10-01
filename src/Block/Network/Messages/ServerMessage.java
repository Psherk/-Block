package Block.Network.Messages;

import Block.Network.ConnectionHandler;
import Block.Utils.habbo.HabboEncoding;
import Block.Utils.logging.HeaderType;
import Block.Utils.logging.Logger;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by luis on 24/07/14.
 */
public class ServerMessage {
    private int Header;
    private ByteArrayOutputStream byteArray;

    public ServerMessage() {
        this.byteArray = new ByteArrayOutputStream();
    }

    public ServerMessage(int header) {
        this.byteArray = new ByteArrayOutputStream();
        this.Header = header;
    }

    public void writeUTF(String UTF)
    {
        try {
            this.byteArray.write(HabboEncoding.EncodeInt16(UTF.length()));
            this.byteArray.write(UTF.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeInt(int Int) {
        try {
            this.byteArray.write(HabboEncoding.EncodeInt32(Int));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writeShort(int Short) {
        try {
            this.byteArray.write(HabboEncoding.EncodeInt16(Short));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

public void writeLength() {
    int Length = this.byteArray.size();
    ServerMessage Array = new ServerMessage();
    Array.writeInt(Length);
    Array.writeByteArray(this);
    this.byteArray = Array.getByteArray();
}
    public void writeByteArray(ServerMessage ByteArray) {
        try {
            this.byteArray.write(ByteArray.getBytes());
        } catch (Exception e) {

        }
    }

    public void writeBoolean(Boolean Boolean) {
/* 70 */     this.byteArray.write(Boolean.booleanValue() ? 1 : 0);
/*    */   }
    public byte[] getBytes()
/*    */   {
/* 75 */     return this.byteArray.toByteArray();
/*    */   }
    public ByteArrayOutputStream getByteArray()
/*    */   {
/* 80 */     return this.byteArray;
/*    */   }

    public void finalize() throws Throwable{
        Clear();
        super.finalize();
    }
    private void Clear() {
/* 90 */     this.byteArray.reset();
/*    */   }
    public void Send(Channel Session) {
        ServerMessage ToSend = new ServerMessage();
        ToSend.writeShort(Header);
        ToSend.writeByteArray(this);
        ToSend.writeLength();
        Session.write(ChannelBuffers.wrappedBuffer(ToSend.getBytes()));

        //Mientras
        Logger.appendLine("[Conocido] [ID: " + Header + "] : " + ConnectionHandler.getCharFilter(ChannelBuffers.wrappedBuffer(ToSend.getBytes())), HeaderType.COMPOSER);
    }
}
