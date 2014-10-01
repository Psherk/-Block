package Habbo.Composers.Handshake;

import Block.Network.Messages.ServerMessage;
import Block.Utils.events.Outgoing;

/**
 * Created by Martha on 08/09/2014.
 */
public class SecretKey {
    public static ServerMessage Init()
    {
        ServerMessage RC4 = new ServerMessage(Outgoing.SecretKey);
        RC4.writeUTF("");
        return RC4;
    }
}
