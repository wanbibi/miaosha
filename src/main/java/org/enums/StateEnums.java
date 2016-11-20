package org.enums;

/**
 * Created by Administrator on 16.11.17.
 */
public enum StateEnums {
    SUCCESS(1, "成功"),
    END(0, "结束"),
    ERROR(-1, "异常"),
    REPEATERROR(-2, "重复秒杀"),
    CLOSEERROR(-3, "关闭异常");

    private int state;
    private String stateInfo;

    StateEnums(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static StateEnums stateOf(int index) {
        for (StateEnums state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
