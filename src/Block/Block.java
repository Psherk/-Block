package Block;

import Block.Network.Messages.RequestMessages;
import Habbo.Game;
import Block.Database.Database;
import Block.Network.ConnectionListener;
import Block.Utils.configuration.Configuration;
import Block.Utils.logging.HeaderType;
import Block.Utils.logging.Logger;

import java.io.IOException;

/**
 * Created by luis on 23/07/14.
 */
public class Block {

    private static Configuration Configuration;
    private static Database Database;
    private static Game Game;
    public static void main(String[] args){
        System.out.println("             ###### ");
        System.out.println("             #    # ");
        System.out.println("             #####  ");
        System.out.println("             #   ## ");
        System.out.println("             #    # ");
        System.out.println("             ###### ");
        System.out.println("\t    RELEASE63-201408201051-805547402 [20/Agosto]");
        System.out.println("\t    HabboHotel Public Stable Emulator based on SH4RP. Thanks to Bi0niC".concat(System.getProperty("line.separator")));
        long SystemStart = System.currentTimeMillis();

        try{
            Logger.appendLine("Iniciando ¡Block Emulator....", HeaderType.BlOCK);
            Configuration = new Configuration();

            Logger.appendLine("Iniciado conexión con la base de datos...", HeaderType.BlOCK);
            Database = new Database();

            Logger.appendLine("Loading HabboHotel Settings...", HeaderType.BlOCK);
            Game = new Game();

            Logger.appendLine("Iniciando Sockets...", HeaderType.BlOCK);
            ConnectionListener.listenClients();

            long SystemEnd = System.currentTimeMillis();
            Logger.appendLine("¡Block Emulator iniciado correctamente! (".concat(String.valueOf(SystemEnd - SystemStart)).concat("ms)"), HeaderType.BlOCK);

        } catch(IOException e) {
            System.err.println("Error al iniciar Block Emulator!");
            System.err.println(e.getMessage());
        } catch(Exception e){
            System.err.println("Error al iniciar Block Emulator!");
            System.err.println(e.getMessage());
        }
    }

    public static Configuration getConfiguration(){
        return Configuration;
    }
    public static Game getGame() { return Game; }
    public static Database getDatabaseManager() { return Database; }
}
