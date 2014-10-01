package Habbo.Composers.Handshake;

import Block.Network.Messages.ServerMessage;
import Block.Utils.events.Outgoing;

/**
 * Created by Martha on 08/09/2014.
 */
public class Ping {
    public static ServerMessage Init(int p)
    {
        ServerMessage Ping = new ServerMessage(Outgoing.Ping);
        Ping.writeInt(p);
        return Ping;
    }
}
