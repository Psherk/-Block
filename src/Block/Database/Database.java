package Block.Database;

import Block.Block;
import Block.Utils.logging.HeaderType;
import Block.Utils.logging.Logger;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.sql.Connection;

/**
 * Created by luis on 23/07/14.
 */
public class Database {
    private BoneCP DBManager;
    public Database() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl("jdbc:mysql://" + Block.getConfiguration().getValue("SQL_Host") + ":" + Block.getConfiguration().getValue("SQL_Port") + "/" + Block.getConfiguration().getValue("SQL_DB"));
            config.setUsername(Block.getConfiguration().getValue("SQL_User"));
            config.setPassword(Block.getConfiguration().getValue("SQL_Password"));
            config.setMinConnectionsPerPartition(15);
            config.setPartitionCount(4);
            config.setMaxConnectionsPerPartition(150);
            this.DBManager = new BoneCP(config);
            if (this.DBManager != null) {
                Logger.appendLine("Conexión con la base de datos realizada!", HeaderType.BlOCK);}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getFreeConnection() throws Exception{
        return this.DBManager.getConnection();
    }
}
