package Habbo.Composers.Handshake;

import Block.Network.Messages.ServerMessage;
import Block.Utils.events.Outgoing;

/**
 * Created by Martha on 08/09/2014.
 */
public class SendBanner {
    public static ServerMessage Init()
    {
        ServerMessage Banner = new ServerMessage(Outgoing.SendBannerMessage);
        Banner.writeUTF("");
        Banner.writeUTF("");
        return Banner;
    }
}
