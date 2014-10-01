package Habbo.Events.Room;

import Block.Network.ConnectionHandler;
import Block.Network.Messages.IMessage;
import Habbo.Composers.Parse;

/**
 * Created by Martha on 14/09/2014.
 */
public class Shout implements IMessage {
    @Override
    public void invoke(ConnectionHandler Connection)
    {
        //Gritar
        String Mensaje = Connection.readString();
        int Color = Connection.readInt();
        Connection.Send(Parse.TalkRoom(Connection.getHabbo().getVirtualID(), Mensaje, 0, Color));
    }
}
