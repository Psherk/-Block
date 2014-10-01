package Habbo.Composers.Handshake;

import Block.Network.Messages.ServerMessage;
import Block.Utils.events.Outgoing;

/**
 * Created by Martha on 08/09/2014.
 */
public class Authentication {
    public static ServerMessage OK()
    {
        ServerMessage Ok = new ServerMessage(Outgoing.AuthenticationOK);
        return Ok;
    }
}
