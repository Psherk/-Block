package Block.HabboCrypto.Util;

/**
 * Created by Martha on 26/09/2014.
 */
public class Converter {
    public static String BytesToHexString(byte[] bytes)
    {
        String hexstring = BitConverter.ToString(bytes);
        return hexstring.replace("-", "");
    }
    public static byte[] HexStringToBytes(String hexstring)
    {
        int NumberChars = hexstring.length();
        byte[] bytes = new byte[NumberChars / 2];
        for (int i = 0; i < NumberChars; i += 2)
        {
            bytes[i / 2] = Convert.ToByte(hexstring.substring(i, 2), 16);
        }
        return bytes;
    }
}
