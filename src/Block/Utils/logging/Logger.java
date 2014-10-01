package Block.Utils.logging;

/**
 * Created by luis on 23/07/14.
 */
public class Logger {
    public static void appendLine(String Txt, HeaderType Type){

        switch(Type){
            case BlOCK:
                System.out.print("[¡Block] -> ");
                break;

            case HABBO:
                System.out.print("[HABBO][Event] -> ");
                break;

            case COMPOSER:
                System.out.print("[HABBO][Composer] -> ");
                break;

            case CONNECT:
                System.out.print("[Connect] -> ");
                break;

            case DISCONNECT:
                System.out.print("[Disconnect] -> ");
                break;

            case ERROR:
                System.out.print("[ERROR] -> ");
                break;
        }
        System.out.println(Txt);
    }
}
