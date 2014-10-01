package Block.Managers.Navigator;

import Block.Block;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Martha on 14/09/2014.
 */
public class NavPublicsManager {
    public static ArrayList<NavPublics> Public()
    {
        ArrayList<NavPublics> Publics = new ArrayList<NavPublics>();
        try{
            Connection Connection = Block.getDatabaseManager().getFreeConnection();
            PreparedStatement PrepStatement = Connection.prepareStatement("SELECT * from NavPublics");
            ResultSet Result = PrepStatement.executeQuery();

            while(Result.next()) {
                NavPublics NavPub = new NavPublics();
                NavPub.Id = Result.getInt("id");
                NavPub.Name = Result.getString("name");
                NavPub.Description = Result.getString("desc");
                NavPub.Image = Result.getString("image");
                NavPub.Link = Result.getInt("link");
                Publics.add(NavPub);
            }
            Connection.close();
            PrepStatement.close();
            Result.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return  Publics;
    }
}
