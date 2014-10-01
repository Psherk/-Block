package Block.Managers.Rooms;

import Block.Block;
import Block.Utils.logging.HeaderType;
import Block.Utils.logging.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * Created by luis on 27/07/14.
 */
public class RoomManager {
    public Map<Integer, Room> CachedRooms;

    public RoomManager() {
        this.CachedRooms = new HashMap<Integer, Room>();
    }

    public Room getRoomByID(int RoomID) {
        if (this.getCachedRooms().containsKey(RoomID))
            return this.getCachedRooms().get(RoomID);

        try {
            Connection Connection = Block.getDatabaseManager().getFreeConnection();
            PreparedStatement PrepStatement = Connection.prepareStatement("SELECT * from Rooms where roomid = ?");
            PrepStatement.setInt(1, RoomID);
            ResultSet Result = PrepStatement.executeQuery();

            if (Result.next()) {
                Room room = new Room(
                        Result.getString("name"),
                        Result.getString("description"),
                        Result.getString("owner"),
                        null,
                        null,
                        null,
                        Result.getString("group"),
                        Result.getString("model"),
                        RoomID,
                        Result.getInt("state"),
                        Result.getInt("usersmax"),
                        Result.getInt("score"),
                        Result.getInt("category"),
                        Result.getInt("ownerid"),
                        Result.getInt("groupId"),
                        Result.getInt("trade"),
                        Result.getInt("allowpets") == 0 ? false : true,
                        Result.getInt("alloweatpets") == 0 ? false : true);

                Connection.close();
                PrepStatement.close();
                Result.close();

                this.getCachedRooms().put(room.getRoomid(), room);
                Logger.appendLine("New Room Cached. (".concat(String.valueOf(this.getCachedRooms().size())).concat(" Cached Rooms!)."), HeaderType.BlOCK);
                return room;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Room getNavigatorRoom(int RoomID) {
        if(this.getCachedRooms().containsKey(RoomID))
            return this.getCachedRooms().get(RoomID);

        try {
            Connection Connection = Block.getDatabaseManager().getFreeConnection();
            PreparedStatement PrepStatement = Connection.prepareStatement("SELECT * FROM Rooms WHERE roomid = ?");
            PrepStatement.setInt(1, RoomID);
            ResultSet Result = PrepStatement.executeQuery();

            if(Result.next()){
                Room room = new Room(
                        Result.getString("name"),
                        Result.getString("description"),
                        Result.getString("owner"),
                        null,
                        null,
                        null,
                        Result.getString("group"),
                        Result.getString("model"),
                        RoomID,
                        Result.getInt("state"),
                        Result.getInt("usersmax"),
                        Result.getInt("score"),
                        Result.getInt("category"),
                        Result.getInt("ownerid"),
                        Result.getInt("groupId"),
                        Result.getInt("trade"),
                        Result.getInt("allowpets") == 0 ? false : true,
                        Result.getInt("alloweatpets") == 0 ? false : true);

                Connection.close();
                PrepStatement.close();
                Result.close();

                return room;
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<Room> getNavigatorRoom(Object Param, SearchType Type) {
        List<Room> RoomList = new LinkedList<Room>();

        switch (Type) {
            case CHARGED_ROOMS:
                RoomList.addAll(this.getCachedRooms().values());
                break;

            case POPULAR_ROOMS:
                RoomList.addAll(this.getRoomsByQuery("SELECT * FROM rooms ORDER BY score DESC LIMIT 50", null));
                break;

            case OWNER_NAME:
                RoomList.addAll(this.getRoomsByQuery("SELECT * FROM rooms WHERE owner = ?", Param));
                break;

            case NORMAL:
                RoomList.addAll(this.getRoomsByQuery("SELECT * FROM rooms WHERE name = ?", Param));
                break;

            case GROUP:
                RoomList.addAll(this.getRoomsByQuery("SELECT * FROM rooms WHERE group = ?", Param));
                break;

            case BY_PARAMS:
                RoomList.addAll(this.getRoomsByQuery("SELECT * FROM rooms WHERE name like '%".concat((String)Param).concat(" %'"), null));
                break;

            case BY_TAG:
                RoomList.addAll(this.getRoomsByQuery("SELECT * from rooms WHERE tags like '%".concat((String)Param).concat("%'"), Param));
                break;
        }

        // Order Rooms!
        Collections.sort(RoomList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (((Room) o1).getUsers().size() > ((Room) o2).getUsers().size()) {
                    return 1;
                } else if (((Room) o1).getUsers().size() < ((Room) o2).getUsers().size()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return RoomList;
    }

    private List<Room> getRoomsByQuery(String Query, Object Param) {
        List<Room> Rooms = new ArrayList<Room>();
        try {
            Connection Connection = Block.getDatabaseManager().getFreeConnection();
            PreparedStatement PrepStatement = Connection.prepareStatement(Query);

            // Param Filters
            if (Param != null)
                PrepStatement.setString(1, (String)Param);

            ResultSet Result = PrepStatement.executeQuery();

            while (Result.next()) {
                int RoomID = Result.getInt("roomid");
                if (this.getCachedRooms().containsKey(RoomID))
                    Rooms.add(this.getCachedRooms().get(RoomID));
                else {
                    Room room = new Room(
                            Result.getString("name"),
                            Result.getString("description"),
                            Result.getString("owner"),
                            null,
                            null,
                            null,
                            Result.getString("group"),
                            Result.getString("model"),
                            RoomID,
                            Result.getInt("state"),
                            Result.getInt("usersmax"),
                            Result.getInt("score"),
                            Result.getInt("category"),
                            Result.getInt("ownerid"),
                            Result.getInt("groupId"),
                            Result.getInt("trade"),
                            Result.getInt("allowpets") == 0 ? false : true,
                            Result.getInt("alloweatpets") == 0 ? false : true);
                    Rooms.add(room);
                }
            }
            Connection.close();
            PrepStatement.close();
            Result.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return Rooms;
    }

    public List<String> getPopularTags() {
        List<String> PopularTagsList = new ArrayList<String>();
        try {
            Connection Connection = Block.getDatabaseManager().getFreeConnection();
            PreparedStatement PrepStatement = Connection.prepareStatement("SELECT tags FROM Rooms ORDER BY score DESC LIMIT 50");
            ResultSet Result = PrepStatement.executeQuery();

            while (Result.next()) {
                String BadTags = Result.getString("tags");
                for (String Tag : BadTags.split(" "))
                    PopularTagsList.add(Tag);
            }
            Connection.close();
            PrepStatement.close();
            Result.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return PopularTagsList;
    }

    public static Room RoomCreate(String N, String D, int C, int T, int U, String M)
    {
        try {
           /* Room mRoom = new Room(
                    mRoom.getName = "";
            );*/
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public Map<Integer, Room> getCachedRooms(){ return this.CachedRooms; }
}
