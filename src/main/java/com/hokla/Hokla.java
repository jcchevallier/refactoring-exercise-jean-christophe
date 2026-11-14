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
       // for (int i = 0; i < drugs.size(); i++) {
        this.drugs.forEach(drug -> {
            updateEfficiencyWithoutConsideringUseBefore(drug);
            updateEfficiencyConsideringUseBefore(drug);
            decreaseUseBefore(drug);
        });
    }

    private void updateEfficiencyWithoutConsideringUseBefore(Drug drug) {
        if (!drug.getName().equals("Old bottle of wine")) {
            if (drug.getEfficiency() > 0) {
                if (!drug.getName().equals("Granny recipe")) {
                    if (drug.getName().equals("Insulin vial")) {
                        drug.updateEfficiency(-1);
                        if (drug.getUseBefore() < 31) {
                            if (drug.getEfficiency() > 0) {
                                drug.updateEfficiency(-1);
                            }
                        }
                        if (drug.getUseBefore() < 8) {
                            if (drug.getEfficiency() > 0) {
                                drug.updateEfficiency(-1);
                            }
                        }
                    } else {
                        drug.updateEfficiency(-1);
                    }
                }
            }
        } else {
            if (drug.getEfficiency() < 100) {
                drug.updateEfficiency(1);
            }
        }
    }

    private void updateEfficiencyConsideringUseBefore(Drug drug) {
        if (drug.getUseBefore() < 0) {
            if (!drug.getName().equals("Old bottle of wine")) {
                if (!drug.getName().equals("Insulin vial")) {
                    if (drug.getEfficiency() > 0) {
                        if (!drug.getName().equals("Granny recipe")) {
                            drug.updateEfficiency(-1);
                        }
                    }
                } else {
                    drug.updateEfficiency(-drug.getEfficiency());
                }
            } else {
                if (drug.getEfficiency() < 100) {
                   drug.updateEfficiency(1);
                }
            }
        }
    }

    private void decreaseUseBefore(Drug drug) {
        if (!drug.getName().equals("Granny recipe")) {
            drug.decreaseUseBefore();
        }
    }
}