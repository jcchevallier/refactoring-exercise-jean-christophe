package com.hokla;

public class Drug {

    public String name;

    public int useBefore;

    public int efficiency;

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

    @Override
    public String toString() {
        return this.name + ", " + this.useBefore + ", " + this.efficiency;
    }

}
