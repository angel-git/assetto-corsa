package com.ags.assetto.connector.utils;

import java.nio.charset.Charset;

/**
 * Utils to read the socket information
 * Created by Gavalda on 1/22/2015.
 */
public class ReaderUtils {

    public static int readUInt32LE(byte[] var0, int var1) {
        return var0[var1 + 3] << 24 | (var0[var1 + 2] & 255) << 16 | (var0[var1 + 1] & 255) << 8 | (var0[var1 + 0] & 255) << 0;
    }

    public static String readStringUTF16LE(byte[] var0, int var1, int var2) {
        int var3 = 0;

        for(int var4 = 0; var4 < var2; ++var4) {
            if(var0[var1 + var4] == 37) {
                var3 = var4;
                break;
            }
        }

        return new String(var0, var1, var3, Charset.forName("UTF-16LE"));
    }

    public static char readChar(byte var0) {
        return (char)(var0 & 255);
    }

    public static float readFloat32(byte[] var0, int var1) {
        return Float.intBitsToFloat(readUInt32LE(var0, var1));
    }


    public static boolean readBoolean(byte var0) {
        return var0 != 0;
    }

    public static float[] read3Float32(byte[] var0, int var1) {
        return new float[]{readFloat32(var0, var1 + 0), readFloat32(var0, var1 + 4), readFloat32(var0, var1 + 8)};
    }

    public static float[] read4Float32(byte[] var0, int var1) {
        return new float[]{readFloat32(var0, var1 + 0), readFloat32(var0, var1 + 4), readFloat32(var0, var1 + 8), readFloat32(var0, var1 + 12)};
    }
}
