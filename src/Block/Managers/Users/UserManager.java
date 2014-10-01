package Block.Managers.Users;

import Block.Block;
import Block.Network.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by luis on 25/07/14.
 */
public class UserManager {

    private Hashtable<Integer, User> UsersPlaying;
    public UserManager(){
        this.UsersPlaying = new Hashtable<Integer, User>();
    }

    public void insertUser(User user){
        if(!this.UsersPlaying.contains(user))
            this.UsersPlaying.put(user.getID(), user);
    }

    public void deleteUser(User user){
        if(this.UsersPlaying.contains(user))
            this.UsersPlaying.remove(user.getID());
    }

    public User getUserFromSSO(String SSO, ConnectionHandler Session){
        try {
            Connection Connection = Block.getDatabaseManager().getFreeConnection();
            PreparedStatement PrepStat = Connection.prepareStatement("SELECT * FROM users WHERE auth_ticket = ? LIMIT 1");
            PrepStat.setString(1, SSO);
            ResultSet Result = PrepStat.executeQuery();

            while(Result.next()) {
                User user = new User(
                        Result.getInt("id"),
                        Result.getInt("respect"),
                        Result.getInt("daily_respect_points"),
                        Result.getInt("daily_pet_respect_points"),
                        Result.getInt("rank"),
                        Result.getInt("home_room"),
                        Result.getInt("credits"),
                        Result.getInt("crystals"),
                        Result.getInt("activity_points"),
                        Result.getInt("achievement_points"),
                        Result.getString("username"),
                        Result.getString("real_name"),
                        Result.getString("look"),
                        SSO,
                        Result.getString("motto"),
                        Result.getString("gender"),
                        Result.getInt("habboclub") == 1 ? true : false,
                        Session
                );
                Connection.close();
                PrepStat.close();
                Result.close();

                return user;
            }
        } catch(Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public User SaveLook(String G, String L, int I) {
        try {
            Connection Connection = Block.getDatabaseManager().getFreeConnection();
            PreparedStatement PrepStat = Connection.prepareStatement("UPDATE users SET gender = ?, look = ? WHERE id = ?");
            PrepStat.setString(1, G);
            PrepStat.setString(2, L);
            PrepStat.setInt(3, I);
            int Result = PrepStat.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void sendBroadcastMessage(String Message){
        Queue<User> QueueUsers = new LinkedList(this.UsersPlaying.values());

        //while(QueueUsers.size() != 0)
            //QueueUsers.poll().Parse(Message);
    }
}
