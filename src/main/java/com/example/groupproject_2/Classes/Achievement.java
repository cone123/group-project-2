package com.example.groupproject_2.Classes;

public class Achievement {
    private boolean unlocked = false;
    private String name;

    public Achievement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    @Override
    public String toString() {
        return ""+name;
    }

    public static Achievement achievement1 = new Achievement("you clicked 100 times");


}
