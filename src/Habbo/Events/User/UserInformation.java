package Habbo.Events.User;

import Block.Network.ConnectionHandler;
import Block.Network.Messages.IMessage;
import Habbo.Composers.Parse;

/**
 * Created by Martha on 08/09/2014.
 */
public class UserInformation implements IMessage {
    @Override
    public void invoke(ConnectionHandler Connection)
    {
        Connection.Send(Parse.UserInfo(Connection.getHabbo()));
        Connection.Send(Parse.Allowances());
    }
}
