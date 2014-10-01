package Habbo.Events.Navigator;

import Block.Block;
import Block.Managers.Navigator.NavPublicsManager;
import Block.Network.ConnectionHandler;
import Block.Network.Messages.IMessage;
import Habbo.Composers.Parse;

/**
 * Created by Martha on 14/09/2014.
 */
public class NavPublic implements IMessage {
    @Override
    public void invoke(ConnectionHandler Connection)
    {
        //Navigator, publics rooms
        Connection.Send(Parse.NavPublics(NavPublicsManager.Public()));
    }
}
