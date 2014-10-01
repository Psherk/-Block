package Block.Utils.habbo;

/**
 * Created by luis on 24/07/14.
 */

public class HabboEncoding
{
    public static byte[] EncodeInt16(int x)
    {
        return EncodeInt16((short)x);
    }
    public static byte[] EncodeInt16(short x)
    {
        return new byte[]{ (byte)(x >>> 8), (byte)x };
    }
    public static byte[] EncodeInt32(int x)
    {
        return new byte[]{ (byte)(x >>> 24), (byte)(x >>> 16), (byte)(x >>> 8), (byte)x };
    }
}
