package com.example.groupproject_2.Classes;

public class Upgrade {

    private String name;
    private double currentCost;
    private double cost;
    private double level;
    private double multiplier;

    public Upgrade(String name, double cost, double multiplier, double level) {
        this.name = name;
        this.currentCost = cost;
        this.cost = cost;
        this.level = level;
        this.multiplier = multiplier;
    }

    public void purchase(){
        level++;
        currentCost = cost * Math.pow(multiplier,level);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(double currentCost) {
        this.currentCost = currentCost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public String toString() {
        return name + " - Cost: $" + String.format("%.0f", currentCost) +
                " (Level " + level + ")";
    }
    public static Upgrade autoClickPower = new Upgrade("auto click power",10,1.2,0.0);
    public static Upgrade clickPower = new Upgrade("click power",10,1.15,0.0);
}
