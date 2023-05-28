package com.example.tamagopersona;

import com.example.tamagopersona.Wallet;

public class Wallet {
    public static int money = 0;

    public Wallet(int money) {
        this.money = money;
    }

    public static int getMoney() {
        return money;
    }

    public static void addMoney(int sum) {
        money += sum;
    }

    public void lessMoney(int sum) {
        money -= sum;
    }

}
