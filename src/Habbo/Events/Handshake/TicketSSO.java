package Habbo.Events.Handshake;

import Block.Block;
import Block.Managers.Users.User;
import Block.Network.ConnectionHandler;
import Block.Network.Messages.IMessage;
import Block.Utils.logging.HeaderType;
import Block.Utils.logging.Logger;
import Habbo.Composers.Handshake.Authentication;
import Habbo.Composers.Parse;

/**
 * Created by Martha on 08/09/2014.
 */
public class TicketSSO implements IMessage {
    @Override
    public void invoke(ConnectionHandler Connection)
    {
        User mUser = Block.getGame().getUserManager().getUserFromSSO(Connection.readString(), Connection);

        if(mUser != null) {
            Connection.setHabbo(mUser);

            //Auth
            Connection.Send(Authentication.OK());

            //Mensaje de bienvenida
            Connection.Send(Parse.sendNotify("Welcome " + mUser.getUsername() + ", this hotel uses ¡BlockEmulator by Psherk, thanks to Bi0niC"));

            //Monedas &
            Connection.Send(Parse.RoomPricipal(mUser));
            Connection.Send(Parse.Credits(mUser));
            Connection.Send(Parse.Duckets(mUser));

            Block.getGame().getUserManager().insertUser(mUser);
            Logger.appendLine(mUser.getUsername(), HeaderType.CONNECT);
        } else {
            return;
        }
    }
}
