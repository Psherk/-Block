package Habbo.Events.Handshake;

import Block.Network.ConnectionHandler;
import Block.Network.Messages.IMessage;
import Habbo.Composers.Handshake.SendBanner;

/**
 * Created by Martha on 08/09/2014.
 */
public class Crypto implements IMessage {
    @Override
    public void invoke(ConnectionHandler Connection)
    {
        Connection.Send(SendBanner.Init());
    }
}
