package Habbo.Events.User;

import Block.Network.ConnectionHandler;
import Block.Network.Messages.IMessage;
import Habbo.Composers.Parse;

/**
 * Created by Martha on 09/09/2014.
 */
public class LatencyTracker implements IMessage {
    @Override
    public void invoke(ConnectionHandler Connection)
    {
        Connection.Send(Parse.Latency(Connection.readInt()));
    }
}
