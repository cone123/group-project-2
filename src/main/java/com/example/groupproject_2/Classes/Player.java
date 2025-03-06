package com.example.groupproject_2.Classes;

public class Player {
    private double money;
    private int totalClicks; // keep track
    private double clickPower = 1; // damage
    private double autoClickRate = 0;
    private double goldMultiplier = 1;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getTotalClicks() {
        return totalClicks;
    }

    public void setTotalClicks(int totalClicks) {
        this.totalClicks = totalClicks;
    }

    public double getClickPower() {
        return clickPower;
    }

    public void setClickPower(double clickPower) {
        this.clickPower = clickPower;
    }

    public double getAutoClickRate() {
        return autoClickRate;
    }

    public void setAutoClickRate(double autoClickRate) {
        this.autoClickRate = autoClickRate;
    }

    public double getGoldMultiplier() {
        return goldMultiplier;
    }

    public void setGoldMultiplier(double goldMultiplier) {
        this.goldMultiplier = goldMultiplier;
    }

    @Override
    public String toString() {
        return "$" + money;
    }
}
