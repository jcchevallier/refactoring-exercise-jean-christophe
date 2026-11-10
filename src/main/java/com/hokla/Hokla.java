package com.hokla;

import java.util.List;

class Inventory {
    List<Drug> drugs;

    public Inventory(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public void updateEfficiency() {
        for (int i = 0; i < drugs.size(); i++) {
            if (!drugs.get(i).name.equals("Old bottle of wine")) {
                if (drugs.get(i).efficiency > 0) {
                    if (!drugs.get(i).name.equals("Granny recipe")) {
                        if (drugs.get(i).name.equals("Insulin vial")) {
                            drugs.get(i).efficiency = drugs.get(i).efficiency - 1;
                            if (drugs.get(i).useBefore < 31) {
                                if (drugs.get(i).efficiency > 0) {
                                    drugs.get(i).efficiency = drugs.get(i).efficiency - 1;
                                }
                            }
                            if (drugs.get(i).useBefore < 8) {
                                if (drugs.get(i).efficiency > 0) {
                                    drugs.get(i).efficiency = drugs.get(i).efficiency - 1;
                                }
                            }
                        } else {
                            drugs.get(i).efficiency = drugs.get(i).efficiency - 1;
                        }
                    }
                }
            } else {
                if (drugs.get(i).efficiency < 100) {
                    drugs.get(i).efficiency = drugs.get(i).efficiency + 1;
                }
            }

            if (!drugs.get(i).name.equals("Granny recipe")) {
                drugs.get(i).useBefore = drugs.get(i).useBefore - 1;
            }

            if (drugs.get(i).useBefore < 0) {
                if (!drugs.get(i).name.equals("Old bottle of wine")) {
                    if (!drugs.get(i).name.equals("Insulin vial")) {
                        if (drugs.get(i).efficiency > 0) {
                            if (!drugs.get(i).name.equals("Granny recipe")) {
                                drugs.get(i).efficiency = drugs.get(i).efficiency - 1;
                            }
                        }
                    } else {
                        drugs.get(i).efficiency = drugs.get(i).efficiency - drugs.get(i).efficiency;
                    }
                } else {
                    if (drugs.get(i).efficiency < 100) {
                        drugs.get(i).efficiency = drugs.get(i).efficiency + 1;
                    }
                }
            }
        }
    }
}