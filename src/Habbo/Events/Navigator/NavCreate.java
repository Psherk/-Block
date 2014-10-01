package Habbo.Events.Navigator;

import Block.Network.ConnectionHandler;
import Block.Network.Messages.IMessage;

/**
 * Created by Martha on 15/09/2014.
 */
public class NavCreate implements IMessage {
    @Override
    public void invoke(ConnectionHandler Connection)
    {
        String N = Connection.readString(); //Nombre
        String D = Connection.readString(); //Descripción
        int C = Connection.readInt(); //Categoría
        int T = Connection.readInt(); //Tradeos
        int U = Connection.readInt(); //Usuarios por sala
        String M = Connection.readString(); //Modelo de sala
    }
}
