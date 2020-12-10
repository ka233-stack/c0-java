package miniplc0java.analyser.o0;

import miniplc0java.instruction.Instruction;

import java.util.ArrayList;

public class FunctionDef {
    // 函数名称在全局变量中的位置
    public int name; // u32,
    // 返回值占据的 slot 数
    public int return_slots; // u32,
    // 参数占据的 slot 数
    public int param_slots; // u32,
    // 局部变量占据的 slot 数
    public int loc_slots; // u32,
    // 函数体
    public ArrayList<Instruction> body;

    public FunctionDef(int name, int return_slots, int param_slots, int loc_slots) {
        this.name = name;
        this.return_slots = return_slots;
        this.param_slots = param_slots;
        this.loc_slots = loc_slots;
        this.body = new ArrayList<>();
    }

    public ArrayList<Instruction> getBody() {
        return body;
    }

    public int getName() {
        return name;
    }

    public int getLoc_slots() {
        return loc_slots;
    }

    public int getParam_slots() {
        return param_slots;
    }

    public int getReturn_slots() {
        return return_slots;
    }

    public int addInstruction(Instruction instruction) {
        this.body.add(instruction);
        return this.body.size() - 1;
    }

    public Instruction getInstruction(int no) {
        return this.body.get(no);
    }

    public int getInsNum() {
        return this.body.size();
    }

    public void addLocVarNum() {
        this.loc_slots++;
    }

    public void addParamNum() {
        this.param_slots++;
    }

    public void setReturn_slots(int return_slots) {
        this.return_slots = return_slots;
    }
}
