package com.kasarisute.utils;

public abstract class NumberHandler {
    public static float parseMoney(Integer moneyNum) {
        return moneyNum / 1000;
    }

    public static int formatMoney(float money) {
        return (int) (money * 1000);
    }

}
