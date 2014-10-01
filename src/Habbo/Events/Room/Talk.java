package Habbo.Events.Room;

import Block.Block;
import Block.Network.ConnectionHandler;
import Block.Network.Messages.IMessage;
import Habbo.Composers.Parse;

/**
 * Created by Martha on 14/09/2014.
 */
public class Talk implements IMessage {
    @Override
    public void invoke(ConnectionHandler Connection)
    {
        //Hablar
        String Mensaje = Connection.readString();
        int Color = Connection.readInt();
        int Num = Connection.readInt();
        Connection.Send(Parse.TalkRoom(Connection.getHabbo().getVirtualID(), Mensaje, Color, Num));
    }
}
