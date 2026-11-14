package com.hokla;

import java.util.List;

class Inventory {
    private List<Drug> drugs;

    public Inventory(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void updateEfficiency() {
        for (int i = 0; i < drugs.size(); i++) {
        //this.drugs.forEach({drug ->
            if (!drugs.get(i).getName().equals("Old bottle of wine")) {
                if (drugs.get(i).getEfficiency() > 0) {
                    if (!drugs.get(i).getName().equals("Granny recipe")) {
                        if (drugs.get(i).getName().equals("Insulin vial")) {
                            drugs.get(i).updateEfficiency(-1);
                            if (drugs.get(i).getUseBefore() < 31) {
                                if (drugs.get(i).getEfficiency() > 0) {
                                    drugs.get(i).updateEfficiency(-1);
                                }
                            }
                            if (drugs.get(i).getUseBefore() < 8) {
                                if (drugs.get(i).getEfficiency() > 0) {
                                    drugs.get(i).updateEfficiency(-1);
                                }
                            }
                        } else {
                            drugs.get(i).updateEfficiency(-1);
                        }
                    }
                }
            } else {
                if (drugs.get(i).getEfficiency() < 100) {
                    drugs.get(i).updateEfficiency(1);
                }
            }

            if (drugs.get(i).getUseBefore() < 0) {
                if (!drugs.get(i).getName().equals("Old bottle of wine")) {
                    if (!drugs.get(i).getName().equals("Insulin vial")) {
                        if (drugs.get(i).getEfficiency() > 0) {
                            if (!drugs.get(i).getName().equals("Granny recipe")) {
                                drugs.get(i).updateEfficiency(-1);
                            }
                        }
                    } else {
                        drugs.get(i).updateEfficiency(-drugs.get(i).getEfficiency());
                    }
                } else {
                    if (drugs.get(i).getEfficiency() < 100) {
                       drugs.get(i).updateEfficiency(1);
                    }
                }
            }

            decreaseUseBefore(i);
        // });
        }
    }

    private void decreaseUseBefore(int i) {
        if (!drugs.get(i).getName().equals("Granny recipe")) {
            drugs.get(i).decreaseUseBefore();
        }
    }
}