package miniplc0java.analyser.o0;

import java.util.ArrayList;

public class GlobalDef {
    // 是否为常量？非零值视为真
    private byte is_const;// u8
    // 值
    private ArrayList<Byte> value;// u8

    public GlobalDef(byte is_const, ArrayList<Byte> value) {
        this.is_const = is_const;
        this.value = value;
    }

    public GlobalDef(byte is_const) {
        ArrayList<Byte> items = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            items.add((byte) 0);
        }
        this.is_const = is_const;
        this.value = items;
    }

    public GlobalDef(byte is_const, String str) {
        this.is_const = is_const;
        int size = str.length();
        ArrayList<Byte> values = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            values.add((byte) str.charAt(i));
        }
        this.value = values;
    }

    public byte getIs_const() {
        return is_const;
    }

    public ArrayList<Byte> getValue() {
        return value;
    }
}
