package Habbo.Events.Handshake;

import Block.Network.ConnectionHandler;
import Block.Network.Messages.IMessage;

/**
 * Created by Martha on 08/09/2014.
 */
public class Ping implements IMessage {
    @Override
    public void invoke(ConnectionHandler Connection)
    {
        Connection.Send(Habbo.Composers.Handshake.Ping.Init(Connection.readInt()));
    }
}
