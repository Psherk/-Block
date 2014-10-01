package Habbo.Events.Navigator;

import Block.Network.ConnectionHandler;
import Block.Network.Messages.IMessage;
import Habbo.Composers.Parse;

/**
 * Created by Martha on 15/09/2014.
 */
public class NavRooms implements IMessage {
    @Override
    public void invoke(ConnectionHandler Connection)
    {
        //
        Connection.Send(Parse.NavRoom(""));
    }
}
