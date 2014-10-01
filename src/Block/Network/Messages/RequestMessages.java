package Block.Network.Messages;

import Block.Utils.events.Incoming;
import Block.Network.ConnectionHandler;
import Block.Utils.logging.HeaderType;
import Block.Utils.logging.Logger;
import Habbo.Events.Catalog.CatalogIndex;
import Habbo.Events.Handshake.*;
import Habbo.Events.Room.Shout;
import Habbo.Events.Room.Talk;
import Habbo.Events.User.*;
import Habbo.Events.Navigator.*;

import java.util.Hashtable;

public class RequestMessages {
    private Hashtable<Short, IMessage> MessageEvents;

    public RequestMessages(){
        this.MessageEvents = new Hashtable<Short, IMessage>();

        this.Requests();

        Logger.appendLine(String.valueOf(this.MessageEvents.size()).concat(" Requests iniciados!"), HeaderType.BlOCK);
    }

    private void Requests(){
        this.MessageEvents.put(Incoming.CheckRelease, new Release());
        this.MessageEvents.put(Incoming.Crypto, new Crypto());
        this.MessageEvents.put(Incoming.RC4, new RC4());
        this.MessageEvents.put(Incoming.ClientVars, new ClientVars());
        this.MessageEvents.put(Incoming.UniqueMachineId, new UniqueID());
        this.MessageEvents.put(Incoming.SSO, new TicketSSO());
        this.MessageEvents.put(Incoming.UserInformation, new UserInformation());
        this.MessageEvents.put(Incoming.StyleClient, new StyleClient());

        this.MessageEvents.put(Incoming.Hc, new ClubHC());
        this.MessageEvents.put(Incoming.LatencyTracker, new LatencyTracker());

        this.MessageEvents.put(Incoming.NavPublic, new NavPublic());
        this.MessageEvents.put(Incoming.NavRooms, new NavRooms());
        this.MessageEvents.put(Incoming.NavScore, new NavScore());
        this.MessageEvents.put(Incoming.NavMe, new NavMe());
        this.MessageEvents.put(Incoming.NavSearch, new Search());
        this.MessageEvents.put(Incoming.NavCreate, new NavCreate());
        this.MessageEvents.put(Incoming.NavNew, new NavCreateCan());

        this.MessageEvents.put(Incoming.CatalogIndex, new CatalogIndex());

        this.MessageEvents.put(Incoming.Pong, new Pong());
        this.MessageEvents.put(Incoming.Ping, new Ping());

        //User
        this.MessageEvents.put(Incoming.Talk, new Talk());
        this.MessageEvents.put(Incoming.Shout, new Shout());
        this.MessageEvents.put(Incoming.SaveLook, new SaveLook());

        //Cuando se desconecta
        this.MessageEvents.put(Incoming.Disconect, new Disconnect());
    }

    public Boolean invokeMessage(short Header, ConnectionHandler Connection) {
        if (this.MessageEvents.containsKey(Header)) {
            this.MessageEvents.get(Header).invoke(Connection);
            return true;
        }
        return false;
    }
}
