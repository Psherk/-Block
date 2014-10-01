package Block.Managers.Rooms;

import Block.Network.Messages.ServerMessage;
import Block.Managers.Users.User;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by luis on 27/07/14.
 */
public class Room {
    private String Name;
    private String Description;
    private String Owner;
    private String Wallpaper;
    private String Floor;
    private String Landscape;
    private String GroupN;
    private String Model;

    private int RoomID;
    private int State;
    private int Usersmax;
    private int Score;
    private int Category;
    private int OwnerID;
    private int Trade;
    private int GroupI;

    private Boolean Allowpets;
    private Boolean Alloweatpets;
    private Boolean isMuted = false;

    private HashSet<User> Users;

    public Room(String Name, String Description, String Owner, String Wallpaper, String Floor, String Landscape, String GroupN, String Model, int RoomID, int State, int Usersmax, int Score, int Category, int OwnerID, int GroupI, int Trade, Boolean Allowpets, Boolean Alloweatpets){
        this.Name = Name;
        this.Description = Description;
        this.Owner = Owner;
        this.Wallpaper = Wallpaper;
        this.Floor = Floor;
        this.Landscape = Landscape;
        this.GroupN = GroupN;
        this.Model = Model;
        this.RoomID = RoomID;
        this.State = State;
        this.Usersmax = Usersmax;
        this.Score = Score;
        this.Category = Category;
        this.OwnerID = OwnerID;
        this.GroupI = GroupI;
        this.Trade = Trade;
        this.Allowpets = Allowpets;
        this.Alloweatpets = Alloweatpets;
        this.Users = new HashSet<User>();
    }

    public void sendToRoomUsers(ServerMessage Message){
        Queue<User> UsersQueue = new LinkedList<User>();
        while(UsersQueue.size() != 0)
            Message.Send(UsersQueue.poll().getConnection().getSocket());
    }

    public void serialize(ServerMessage Message){
        Message.writeInt(this.getRoomid());
        Message.writeUTF(this.getName());
        Message.writeBoolean(true);
        Message.writeInt(this.getOwnerID());
        Message.writeUTF(this.getOwner());
        Message.writeInt(this.getState());
        Message.writeInt(this.getUsers() != null ? this.getUsers().size() : 0);
        Message.writeInt(this.getUsersmax());
        Message.writeUTF(this.getDescription());
        Message.writeInt(this.getCategory() == 52 ? 2 : 0);
        Message.writeInt(this.getScore());
        Message.writeInt(this.getCategory());
        Message.writeInt(0);
        Message.writeInt(0); // Grup id
        Message.writeUTF(""); // Grup Name
        Message.writeUTF(""); // Badge of Grup
        Message.writeUTF("");
        Message.writeInt(0); // Tags Size
        Message.writeInt(0);
        Message.writeInt(0);
        Message.writeInt(0);
        Message.writeBoolean(this.getAllowpets());
        Message.writeBoolean(this.getAlloweatpets());
        Message.writeUTF(""); // Promoted name
        Message.writeUTF(""); // Promoted desc
        Message.writeInt(0); // Promoted time
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public void setDescription(String Description) {
        this.Description = Description;
    }
    public void setOwner(String Owner) {
        this.Owner = Owner;
    }
    public void setWallpaper(String Wallpaper) {
        this.Wallpaper = Wallpaper;
    }
    public void setFloor(String Floor) {
        this.Floor = Floor;
    }
    public void setLandscape(String Landscape) {
        this.Landscape = Landscape;
    }
    public void setRoomid(int RoomID) {
        this.RoomID = RoomID;
    }
    public void setState(int State) {
        this.State = State;
    }
    public void setUsersmax(int Usersmax) {
        this.Usersmax = Usersmax;
    }
    public void setScore(int Score) {
        this.Score = Score;
    }
    public void setCategory(int Category) {
        this.Category = Category;
    }
    public void setOwnerID(int OwnerID) {
        this.OwnerID = OwnerID;
    }
    public void setAllowpets(Boolean Allowpets) {
        this.Allowpets = Allowpets;
    }
    public void setAlloweatpets(Boolean Alloweatpets) {
        this.Alloweatpets = Alloweatpets;
    }
    public void setisMuted(Boolean isMuted) {
        this.isMuted = isMuted;
    }

    public String getName() {
        return this.Name;
    }
    public String getDescription() {
        return this.Description;
    }
    public String getOwner() {
        return this.Owner;
    }
    public String getWallpaper() {
        return this.Wallpaper;
    }
    public String getFloor() {
        return this.Floor;
    }
    public String getLandscape() {
        return this.Landscape;
    }
    public String getGroupN() {
        return this.GroupN;
    }
    public String getModel() {
        return this.Model;
    }
    public int getRoomid() {
        return this.RoomID;
    }
    public int getState() {
        return this.State;
    }
    public int getUsersmax() {
        return this.Usersmax;
    }
    public int getScore() {
        return this.Score;
    }
    public int getCategory() {
        return this.Category;
    }
    public int getOwnerID() {
        return this.OwnerID;
    }
    public int getGroupI() {
        return this.GroupI;
    }
    public int getTrade() {
        return this.Trade;
    }
    public Boolean getAllowpets() {
        return this.Allowpets;
    }
    public Boolean getAlloweatpets() {
        return this.Alloweatpets;
    }
    public Boolean getisMuted() {
        return this.isMuted;
    }
    public HashSet<User> getUsers(){ return this.Users; }
}
