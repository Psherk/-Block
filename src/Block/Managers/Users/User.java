package Block.Managers.Users;

import Block.Utils.events.Outgoing;
import Block.Network.Messages.ServerMessage;
import Block.Managers.Rooms.Room;
import Block.Network.ConnectionHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by luis on 23/07/14.
 */
public class User {
    private int Id;
    private int VirtualId = 0;
    private int Respects;
    private int MaxUserRespects;
    private int MaxPetsRespects;
    private int Rank;
    private int HomeRoomID;
    private int Credits;
    private int Activity_points;
    private int Diamonds;
    private int SpecialCoin;
    private int Score;
    private int X = 0;
    private int Y = 0;
    private double Z = 0;
    private int GoalX = 0;
    private int GoalY = 0;
    private int Rot = 0;
    private int RotHead = 0;
    private int DanceID = 0;
    private int DrinkID = 0;

    private String Username;
    private String Realname;
    private String Look;
    private String SSO;
    private String Mission;
    private String Gender;

    private Boolean HabboClub;
    private Boolean isTeleporting = false;
    private Boolean isIdle = false;

    private ConnectionHandler Connection;
    private Room CurrentRoom = null;
    private List<Room> FavouriteRoomIDs;
    private List<Room> LastRoomVisited;

    public User(int Id, int Respects, int MaxUserRespects, int MaxPetsRespects,  int Rank, int HomeRoomID, int Credits, int Diamonds, int Activity_points, int Score, String Username, String Realname, String Look, String SSO, String Mission, String Gender, Boolean HabboClub, ConnectionHandler Connection) {
        this.Id = Id;
        this.Respects = Respects;
        this.MaxUserRespects = MaxUserRespects;
        this.MaxPetsRespects = MaxPetsRespects;
        this.Rank = Rank;
        this.HomeRoomID = HomeRoomID;
        this.Credits = Credits;
        this.Diamonds = Diamonds;
        this.Activity_points = Activity_points;
        this.Score = Score;
        this.Username = Username;
        this.Realname = Realname;
        this.Look = Look;
        this.SSO = SSO;
        this.Mission = Mission;
        this.Gender = Gender;
        this.HabboClub = HabboClub;
        this.Connection = Connection;
        this.FavouriteRoomIDs = new ArrayList<Room>();
        this.LastRoomVisited=new LinkedList<Room>();
    }

    public void Credits() {
        ServerMessage Credits = new ServerMessage(Outgoing.Credits);
        Credits.writeUTF(String.valueOf(this.getCoins()).concat(".0"));
        Credits.Send(this.getConnection().getSocket());
    }

    public void Duckets() {
        ServerMessage Duckets = new ServerMessage(Outgoing.ActivityPoints);
        Duckets.writeInt(1); //Count
        Duckets.writeInt(0);
        Duckets.writeInt(this.getActivity_points()); //Duckets
        Duckets.Send(this.getConnection().getSocket());
    }

    public void Diamonds() {
    }

    public void setId(int userID) {
        this.Id = userID;
    }
    public void setVirtualID(int virtualID) {
        this.VirtualId = virtualID;
    }
    public void setRespects(int Respects) {
        this.Respects = Respects;
    }
    public void setMaxUserRespects(int maxUserRespects) {
        this.MaxUserRespects = maxUserRespects;
    }
    public void setMaxPetsRespects(int maxPetsRespects) {
        this.MaxPetsRespects = maxPetsRespects;
    }
    public void setRank(int Rank) {
        this.Rank = Rank;
    }
    public void setHomeRoomID(int HomeRoomID) {
        this.HomeRoomID = HomeRoomID;
    }
    public void setCoins(int Credits) {
        this.Credits = Credits;
    }
    public void setOtherCoin(int Diamonds) {
        this.Diamonds = Diamonds;
    }
    public void setScore(int Score) {
        this.Score = Score;
    }
    public void setuserName(String Username) {
        this.Username = Username;
    }
    public void setRealName(String Realname) {
        this.Realname = Realname;
    }
    public void setLook(String Look) {
        this.Look = Look;
    }
    public void setMission(String Mission) {
        this.Mission = Mission;
    }
    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    public void setIsHabboClub(Boolean HabboClub) {
        this.HabboClub = HabboClub;
    }
    public void setisTeleporting(Boolean isTeleporting) {
        this.isTeleporting = isTeleporting;
    }
    public void setX(int X) {
        this.X = X;
    }
    public void setY(int Y) {
        this.Y = Y;
    }
    public void setZ(int Z) {
        this.Z = Z;
    }
    public void setGoalX(int GoalX) {
        this.GoalX = GoalX;
    }
    public void setGoalY(int GoalY) {
        this.GoalY = GoalY;
    }
    public void setRot(int Rot) {
        this.Rot = Rot;
    }
    public void setRotHead(int RotHead) {
        this.RotHead = RotHead;
    }
    public void setSpecialCoin(int SpecialCoin) {
        this.SpecialCoin = SpecialCoin;
    }
    public void setDanceID(int DanceID) {
        this.DanceID = DanceID;
    }
    public void setDrinkID(int DrinkID) { this.DrinkID = DrinkID; }
    public void setCurrentRoom(Room CurrentRoom){ this.CurrentRoom = CurrentRoom; }
    public void setNewVisitedRoom(Room Room) {
        if(this.LastRoomVisited.size() >= 50)
            this.LastRoomVisited.clear();
        if(this.LastRoomVisited.contains(Room))
            this.LastRoomVisited.remove(Room);

        this.LastRoomVisited.add(0, Room);
    }

    public int getID() {return this.Id;}
    public int getVirtualID() {
        return this.VirtualId;
    }
    public int getRespects() {
        return this.Respects;
    }
    public int getMaxUserRespects() {
        return this.MaxUserRespects;
    }
    public int getMaxPetsRespects() {
        return this.MaxPetsRespects;
    }
    public int getRank() {
        return this.Rank;
    }
    public int getHomeRoomID() {
        return this.HomeRoomID;
    }
    public int getCoins() {
        return this.Credits;
    }
    public int getDiamonds() {
        return this.Diamonds;
    }
    public int getScore() {
        return this.Score;
    }
    public String getUsername() {
        return this.Username;
    }
    public String getRealname() {
        return this.Realname;
    }
    public String getLook() {
        return this.Look;
    }
    public String getMission() {
        return this.Mission;
    }
    public String getGender() {
        return this.Gender;
    }
    public Boolean getHabboClub() {
        return this.HabboClub;
    }
    public int getX() {
        return this.X;
    }
    public int getY() {
        return this.Y;
    }
    public double getZ() {
        return this.Z;
    }
    public int getGoalX() {
        return this.GoalX;
    }
    public int getGoalY() {
        return this.GoalY;
    }
    public int getRot() {
        return this.Rot;
    }
    public int getRotHead() {
        return this.RotHead;
    }
    public int getActivity_points() {
        return this.Activity_points;
    }
    public int getSpecialCoin() {
        return this.SpecialCoin;
    }
    public int getDanceID() {
        return this.DanceID;
    }
    public int getDrinkID() {
        return this.DrinkID;
    }
    public Boolean getisTeleporting() {
        return this.isTeleporting;
    }
    public Boolean getisIdle() {
        return this.isIdle;
    }
    public ConnectionHandler getConnection(){ return this.Connection; }
    public Room getCurrentRoom(){
        return this.CurrentRoom;
    }
    public List<Room> getLastRoomVisited() { return this.LastRoomVisited; }
}
