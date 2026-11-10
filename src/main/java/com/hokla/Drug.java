package com.hokla;

public class Drug {

    private final String name;

    private int useBefore;

    private int efficiency;

    public String getName() {
        return name;
    }

    public int getUseBefore() {
        return useBefore;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public Drug(String name, int useBefore, int efficiency) {
        this.name = name;
        this.useBefore = useBefore;
        this.efficiency = efficiency;
    }

    public static Drug create(String name, int useBefore, int efficiency) {
        return new Drug(name, useBefore, efficiency);
    }

    public void decreaseUseBefore() {
        this.useBefore = useBefore - 1;
    }

    public void updateEfficiency(int efficiencyIncrement) {
            this.efficiency = efficiency + efficiencyIncrement;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.useBefore + ", " + this.efficiency;
    }

}
