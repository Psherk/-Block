package Habbo;

import Block.Managers.Navigator.NavPublicsManager;
import Block.Network.Messages.RequestMessages;
import Block.Managers.Rooms.RoomManager;
import Block.Managers.Users.UserManager;

/**
 * Created by luis on 23/07/14.
 */
public class Game {

    private RequestMessages RequestMessages;
    private UserManager UserManager;
    private RoomManager RoomManager;
    public Game(){
        this.RequestMessages = new RequestMessages();
        this.UserManager = new UserManager();
        this.RoomManager = new RoomManager();
    }

    public RequestMessages getRequestMessages(){
        return this.RequestMessages;
    }
    public UserManager getUserManager(){ return this.UserManager; }
    public RoomManager getRoomManager(){ return this.RoomManager; }
}
