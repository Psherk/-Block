package Habbo.Events.Navigator;

import Block.Network.ConnectionHandler;
import Block.Network.Messages.IMessage;
import Habbo.Composers.Parse;

/**
 * Created by Martha on 09/09/2014.
 */
public class Search implements IMessage {
    @Override
    public void invoke(ConnectionHandler Connection)
    {
        String Nav = Connection.readString();

        Connection.Send(Parse.Search(Nav));
    }
}
