package Habbo.Events.User;

import Block.Block;
import Block.Managers.Users.UserManager;
import Block.Network.ConnectionHandler;
import Block.Network.Messages.IMessage;

import java.sql.PreparedStatement;

/**
 * Created by Martha on 09/09/2014.
 */
public class SaveLook implements IMessage {
    @Override
    public void invoke(ConnectionHandler Connection)
    {
        //User mUser = Block.getGame().getUserManager().getUserFromSSO(Connection.readString(), Connection);
        String Gender = Connection.readString();
        String Look = Connection.readString();
        Block.getGame().getUserManager().SaveLook(Gender, Look, Connection.getHabbo().getID());
    }
}
