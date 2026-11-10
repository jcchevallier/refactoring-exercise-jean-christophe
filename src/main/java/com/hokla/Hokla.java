package com.hokla;

import java.util.List;

class Inventory {
    List<Drug> drugs;

    public Inventory(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public void updateEfficiency() {
        for (int i = 0; i < drugs.size(); i++) {
            if (!drugs.get(i).getName().equals("Old bottle of wine")) {
                if (drugs.get(i).getEfficiency() > 0) {
                    if (!drugs.get(i).getName().equals("Granny recipe")) {
                        if (drugs.get(i).getName().equals("Insulin vial")) {
                       //     drugs.get(i).getEfficiency() = drugs.get(i).getEfficiency() - 1;
                            drugs.get(i).updateEfficiency(-1);
                            if (drugs.get(i).getUseBefore() < 31) {
                                if (drugs.get(i).getEfficiency() > 0) {
                            //        drugs.get(i).getEfficiency() = drugs.get(i).getEfficiency() - 1;
                                    drugs.get(i).updateEfficiency(-1);
                                }
                            }
                            if (drugs.get(i).getUseBefore() < 8) {
                                if (drugs.get(i).getEfficiency() > 0) {
                                    // drugs.get(i).getEfficiency() = drugs.get(i).getEfficiency() - 1;
                                    drugs.get(i).updateEfficiency(-1);
                                }
                            }
                        } else {
                            // drugs.get(i).getEfficiency() = drugs.get(i).getEfficiency() - 1;
                            drugs.get(i).updateEfficiency(-1);
                        }
                    }
                }
            } else {
                if (drugs.get(i).getEfficiency() < 100) {
                   // drugs.get(i).getEfficiency() = drugs.get(i).getEfficiency() + 1;
                    drugs.get(i).updateEfficiency(1);
                }
            }

            if (!drugs.get(i).getName().equals("Granny recipe")) {
                // drugs.get(i).getUseBefore() = drugs.get(i).getUseBefore() - 1;
                drugs.get(i).decreaseUseBefore();
            }

            if (drugs.get(i).getUseBefore() < 0) {
                if (!drugs.get(i).getName().equals("Old bottle of wine")) {
                    if (!drugs.get(i).getName().equals("Insulin vial")) {
                        if (drugs.get(i).getEfficiency() > 0) {
                            if (!drugs.get(i).getName().equals("Granny recipe")) {
                                // drugs.get(i).getEfficiency() = drugs.get(i).getEfficiency() - 1;
                                drugs.get(i).updateEfficiency(-1);
                            }
                        }
                    } else {
                       // drugs.get(i).getEfficiency() = drugs.get(i).getEfficiency() - drugs.get(i).getEfficiency();
                        drugs.get(i).updateEfficiency(-drugs.get(i).getEfficiency());
                    }
                } else {
                    if (drugs.get(i).getEfficiency() < 100) {
                      //  drugs.get(i).getEfficiency() = drugs.get(i).getEfficiency() + 1;
                       drugs.get(i).updateEfficiency(1);
                    }
                }
            }
        }
    }
}