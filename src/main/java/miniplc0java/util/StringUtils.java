package miniplc0java.util;

import java.util.ArrayList;

public class StringUtils {
    public static String intToBin(int size, int number) {
        return String.format("%" + size + "s", Integer.toBinaryString(number)).replace(" ", "0");
    }

    public static String intToHex(int size, int number) {
        return String.format("%" + size + "s", Integer.toHexString(number)).replace(" ", "0");
    }

    public static String bytesStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(b).append(" ");
        return sb.toString();
    }

    public static String bytesHexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(String.format("%2s", Integer.toHexString(b)).replace(" ", "0")).append(" ");
        return sb.toString();
    }

    public static String bytesHexStr(ArrayList<Byte> bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(Integer.toHexString(b)).append(" ");
        return sb.toString();
    }

    public static byte[] u32Bytes(int val) {
        byte[] b = new byte[4];
        b[3] = (byte) (val & 0xff);
        b[2] = (byte) ((val >> 8) & 0xff);
        b[1] = (byte) ((val >> 16) & 0xff);
        b[0] = (byte) ((val >> 24) & 0xff);
        return b;
    }

    public static byte[] u64Bytes(long val) {
        byte[] b = new byte[8];
        b[7] = (byte) (val & 0xff);
        b[6] = (byte) ((val >> 8) & 0xff);
        b[5] = (byte) ((val >> 16) & 0xff);
        b[4] = (byte) ((val >> 24) & 0xff);
        b[3] = (byte) ((val >> 32) & 0xff);
        b[2] = (byte) ((val >> 40) & 0xff);
        b[1] = (byte) ((val >> 48) & 0xff);
        b[0] = (byte) ((val >> 56) & 0xff);
        return b;
    }
}
