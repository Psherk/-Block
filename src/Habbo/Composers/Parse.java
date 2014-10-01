package Habbo.Composers;

import Block.Block;
import Block.Managers.Navigator.NavPublics;
import Block.Managers.Rooms.Room;
import Block.Managers.Rooms.RoomManager;
import Block.Managers.Rooms.SearchType;
import Block.Managers.Users.User;
import Block.Network.Messages.ServerMessage;
import Block.Utils.events.Outgoing;
import Habbo.Events.Navigator.NavPublic;
import com.sun.corba.se.spi.activation.Server;
import com.sun.javafx.sg.prism.NGShape;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martha on 08/09/2014.
 */
public class Parse {

    //
    public static ServerMessage ClientStyle()
    {
        ServerMessage Style = new ServerMessage(Outgoing.Style);
        Style.writeUTF("");
        Style.writeUTF("");
        return  Style;
    }
    public static ServerMessage ClubHC(User mUser)
    {
        ServerMessage ClubHC = new ServerMessage(Outgoing.ClubHC);
        ClubHC.writeUTF("club_habbo");
        if (mUser.getHabboClub() == true) {
            ClubHC.writeInt(0); //
            ClubHC.writeInt(2);
            ClubHC.writeInt(0); //
            ClubHC.writeInt(1);
            ClubHC.writeBoolean(true);
            ClubHC.writeBoolean(true);
            ClubHC.writeInt(0);
            ClubHC.writeInt(0);
            ClubHC.writeInt(0x1ef);
        } else {
            ClubHC.writeInt(0);
            ClubHC.writeInt(0);
            ClubHC.writeInt(0);
            ClubHC.writeInt(0);
            ClubHC.writeBoolean(false);
            ClubHC.writeBoolean(true);
            ClubHC.writeInt(0);
            ClubHC.writeInt(0);
            ClubHC.writeInt(0);
        }
        return ClubHC;
    }
    public static ServerMessage sendNotify(String m)
    {
        ServerMessage sendNotif = new ServerMessage(Outgoing.SendNotify);
        sendNotif.writeUTF(m);
        return sendNotif;
    }
    public static ServerMessage Latency(int a)
    {
        ServerMessage Latency = new ServerMessage(Outgoing.Latency);
        Latency.writeInt(a);
        return Latency;
    }

    //Monedas
    public static ServerMessage Credits(User mUser)
    {
        ServerMessage Credits = new ServerMessage(Outgoing.Credits);
        Credits.writeUTF(mUser.getCoins() + ".0");
        return Credits;
    }

    public static ServerMessage Duckets(User mUser)
    {
        ServerMessage Duckets = new ServerMessage(Outgoing.ActivityPoints);
        Duckets.writeInt(1); //Count
        Duckets.writeInt(0);
        Duckets.writeInt(mUser.getActivity_points()); //Duckets
        return Duckets;
    }

    //User
    public static ServerMessage UserInfo(User mUser)
    {
        ServerMessage User = new ServerMessage(Outgoing.HabboInfomation);
        User.writeInt(mUser.getID());
        User.writeUTF(mUser.getUsername());
        User.writeUTF(mUser.getLook());
        User.writeUTF(mUser.getGender());
        User.writeUTF(mUser.getMission());
        User.writeUTF(mUser.getRealname());
        User.writeBoolean(false);
        User.writeInt(mUser.getRespects());
        User.writeInt(mUser.getMaxUserRespects());
        User.writeInt(mUser.getMaxPetsRespects());
        User.writeBoolean(true);
        User.writeUTF("");
        User.writeBoolean(false);
        User.writeBoolean(false);
        return User;
    }
    public static ServerMessage Allowances()
    {
        ServerMessage Allow = new ServerMessage(Outgoing.Allowances);
        Allow.writeInt(12); //Count

        Allow.writeUTF("NAVIGATOR_PHASE_ONE_2014");
        Allow.writeUTF("");
        Allow.writeBoolean(true);

        Allow.writeUTF("CAMERA");
        Allow.writeUTF("");
        Allow.writeBoolean(true);

        Allow.writeUTF("EXPERIMENTAL_CHAT_BETA");
        Allow.writeUTF("");
        Allow.writeBoolean(true);

        Allow.writeUTF("CITIZEN");
        Allow.writeUTF("");
        Allow.writeBoolean(true);

        Allow.writeUTF("VOTE_IN_COMPETITIONS");
        Allow.writeUTF("requirement.unfulfilled.helper_level_2");
        Allow.writeBoolean(false);

        Allow.writeUTF("NEW_UI");
        Allow.writeUTF("");
        Allow.writeBoolean(true);

        Allow.writeUTF("USE_GUIDE_TOOL");
        Allow.writeUTF("requirement.unfulfilled.helper_level_4");
        Allow.writeBoolean(false);

        Allow.writeUTF("BUILDER_AT_WORK");
        Allow.writeUTF("");
        Allow.writeBoolean(true);

        Allow.writeUTF("HEIGHTMAP_EDITOR_BETA");
        Allow.writeUTF("");
        Allow.writeBoolean(true);

        Allow.writeUTF("EXPERIMENTAL_TOOLBAR");
        Allow.writeUTF("requirement.unfulfilled.group_membership");
        Allow.writeBoolean(false);

        Allow.writeUTF("CALL_ON_HELPERS");
        Allow.writeUTF("");
        Allow.writeBoolean(true);

        Allow.writeUTF("TRADE");
        Allow.writeUTF("");
        Allow.writeBoolean(true);
        return Allow;
    }
    public static ServerMessage RoomPricipal(User mUser)
    {
        ServerMessage Room = new ServerMessage(Outgoing.RoomPrincipal);
        Room.writeInt(mUser.getHomeRoomID()); //Id de la sala
        Room.writeInt(0);
        return Room;
    }

    //Navigator
    public static void SerializeNavigator(ServerMessage Message, Room room)
    {
        Message.writeInt(room.getRoomid());
        Message.writeUTF(room.getName());
        Message.writeBoolean(true);
        Message.writeInt(room.getOwnerID());
        Message.writeUTF(room.getOwner());
        Message.writeInt(room.getState());
        Message.writeInt(room.getUsers() != null ? room.getUsers().size() : 0);
        Message.writeInt(room.getUsersmax());
        Message.writeUTF(room.getDescription());
        Message.writeInt(room.getCategory() == 52 ? 2 : 0);
        Message.writeInt(room.getCategory());
        Message.writeInt(room.getScore());
        Message.writeInt(0);
        Message.writeInt(622);
        if(room.getGroupI() > 0) {
            Message.writeInt(room.getGroupI()); // Group id
            Message.writeUTF(room.getGroupN()); // Group Name
            Message.writeUTF(""); // Badge of Group
        } else {
            Message.writeInt(0);
            Message.writeUTF("");
            Message.writeUTF("");
        }
        Message.writeUTF("");
            /*Search.writeInt(room.Tags.length); // Tags Size
            for(String Tag : room.Tags){
                Search.writeUTF(Tag); // Tag
            }*/
        Message.writeInt(0); //
        Message.writeUTF(""); //
        Message.writeInt(0);
        Message.writeInt(0);
        Message.writeInt(0);
        Message.writeBoolean(room.getAllowpets());
        Message.writeBoolean(room.getAlloweatpets());
        Message.writeUTF("");
        Message.writeUTF("");
        Message.writeInt(0);
        return;
    }
    public static ServerMessage NavPublics(ArrayList<NavPublics> room)
    {
        ServerMessage Public = new ServerMessage(Outgoing.SendPublic);
        Public.writeInt(room.size()); //Count
        for(NavPublics mRoom : room){
            Room Rroom = Block.getGame().getRoomManager().getRoomByID(mRoom.Link);

            Public.writeInt(mRoom.Link);
            Public.writeInt(0);
            Public.writeInt(0);
            Public.writeUTF(mRoom.Name);
            Public.writeUTF("officalrooms_hq/" + mRoom.Image);
            Public.writeInt(0);
            Public.writeInt(0);  // users in?
            Public.writeInt(2);
            SerializeNavigator(Public, Rroom);
        }
        Public.writeInt(0);
        Public.writeInt(0);
        return Public;
    }
    public static ServerMessage NavRoom(String u)
    {
        List<Room> Q = Block.getGame().getRoomManager().getNavigatorRoom(u, SearchType.CHARGED_ROOMS);
        ServerMessage Navrooms = new ServerMessage(Outgoing.SendMe);
        Navrooms.writeInt(1);
        Navrooms.writeUTF(""); // Category
        Navrooms.writeInt(Q.size());
        for(Room room : Q){
            SerializeNavigator(Navrooms, room);
        }
        Navrooms.writeBoolean(false);
        return Navrooms;
    }
    public static ServerMessage NavScore(String u)
    {
        List<Room> Q = Block.getGame().getRoomManager().getNavigatorRoom(u, SearchType.CHARGED_ROOMS);
        ServerMessage Navrooms = new ServerMessage(Outgoing.SendMe);
        Navrooms.writeInt(1);
        Navrooms.writeUTF(""); // Category
        Navrooms.writeInt(Q.size());
        for(Room room : Q){
            SerializeNavigator(Navrooms, room);
        }
        Navrooms.writeBoolean(false);
        return Navrooms;
    }
    public static ServerMessage MeRoom(String u)
    {
        List<Room> Q = Block.getGame().getRoomManager().getNavigatorRoom(u, SearchType.OWNER_NAME);
        ServerMessage MeRooms = new ServerMessage(Outgoing.SendMe);
        MeRooms.writeInt(5);
        MeRooms.writeUTF(""); // Category
        MeRooms.writeInt(Q.size());
        for(Room room : Q){
            SerializeNavigator(MeRooms, room);
        }
        MeRooms.writeBoolean(false);
        return MeRooms;
    }
    public static ServerMessage Search(String nav)
    {
        List<Room> Q = null;
        if(nav.startsWith("owner:")) {
            String S = nav.replace("owner:", "");
            Q = Block.getGame().getRoomManager().getNavigatorRoom(S, SearchType.OWNER_NAME);
        } else if(nav.startsWith("roomname:")) {
            String S = nav.replace("roomname:", "");
            Q = Block.getGame().getRoomManager().getNavigatorRoom(S, SearchType.NORMAL);
        }
        else if(nav.startsWith("tag:")) {
            String S = nav.replace("tag:", "");
            Q = Block.getGame().getRoomManager().getNavigatorRoom(S, SearchType.BY_TAG);
        }
        else if(nav.startsWith("group:")) {
            String S = nav.replace("group:", "");
            Q = Block.getGame().getRoomManager().getNavigatorRoom(S, SearchType.GROUP);
        }
        else {
            Q = Block.getGame().getRoomManager().getNavigatorRoom(nav, SearchType.NORMAL);
        }
        ServerMessage Search = new ServerMessage(Outgoing.SendSearch);
        Search.writeInt(8);
        Search.writeUTF(nav);
        Search.writeInt(Q.size()); //Count
        for(Room room : Q){
            SerializeNavigator(Search, room);
        }
        Search.writeBoolean(false);
        return Search;
    }
    public static ServerMessage CanCreate()
    {
        ServerMessage Can = new ServerMessage(Outgoing.SendCanC);
        Can.writeInt(0); //0 = si tiene menos de 75 salas & 1 = Si tiene tiene 75 salas
        Can.writeInt(75); //Máximo de salas
        return Can;
    }

    //Catalog
    public static ServerMessage CatalogIndex()
    {
        ServerMessage Catalog = new ServerMessage(Outgoing.Categories);
        return Catalog;
    }

    //Rooms
    public static ServerMessage LoadHeightMap()
    {
        ServerMessage HeightMap = new ServerMessage(Outgoing.LoadHeighmap);
        return HeightMap;
    }
    public static ServerMessage RelativeMap(Room mRoom)
    {
        ServerMessage RelativeMap = new ServerMessage(Outgoing.RelativeMap);
        RelativeMap.writeBoolean(false);
        RelativeMap.writeInt(-1); //New
        RelativeMap.writeUTF(""); //
        return RelativeMap;
    }
    public static ServerMessage EnterInRoom()
    {
        ServerMessage Enter = new ServerMessage(Outgoing.SendEnter);
        return Enter;
    }
    public static ServerMessage RoomModel(Room mRoom)
    {
        ServerMessage Model = new ServerMessage(Outgoing.SendModel);
        Model.writeUTF(mRoom.getFloor());
        Model.writeInt(mRoom.getRoomid());
        return Model;
    }
    public static ServerMessage RoomRight()
    {
        ServerMessage Right = new ServerMessage(Outgoing.RoomRight);
        Right.writeInt(1); //4
        return Right;
    }
    public static ServerMessage RoomPoints(Room mRoom)
    {
        ServerMessage Point = new ServerMessage(Outgoing.RoomPoint);
        Point.writeInt(mRoom.getScore());
        Point.writeBoolean(false);
        return Point;
    }
    public static int Emoticones(String E)
    {
        if(E.contains(":)") || E.contains("=)") || E.contains(">:)"))
            return 1;
        else if(E.contains(":@") || E.contains(">:("))
            return 2;
        else if(E.contains(":o"))
            return 3;
        else if(E.contains(":(") || E.contains("=(") || E.contains(">:("))
            return 4;
        return 0;
    }
    public static ServerMessage TalkRoom(int I, String M, int C, int N)
    {
        ServerMessage Talk = new ServerMessage(Outgoing.Talk);
        Talk.writeInt(I);
        Talk.writeUTF(M); //Mensaje
        Talk.writeInt(Emoticones(M)); //emoticon
        if(C > 0)
            Talk.writeInt(C);
        else
            Talk.writeInt(0);
        Talk.writeInt(0);
        Talk.writeInt(N); //Tipo (hablar o gritar)
        return Talk;
    }
}
