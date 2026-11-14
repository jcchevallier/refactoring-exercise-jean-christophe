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
        this.drugs.forEach(drug -> {
            updateEfficiencyWithoutConsideringUseBefore(drug);
            updateEfficiencyConsideringUseBefore(drug);
            decreaseUseBefore(drug);
        });
    }

    private void updateEfficiencyWithoutConsideringUseBefore(Drug drug) {
        if (isGrannyRecipe(drug)) return ;
        if (isBottleOfWine(drug)) {
            if(drug.getEfficiency() < 100) drug.updateEfficiency(1);
            return ;
        }
        if (drug.getEfficiency() > 0 && !isInsulin(drug))
            drug.updateEfficiency(-1);

        if (drug.getEfficiency() > 0 && isInsulin(drug)) {
                drug.updateEfficiency(-1);
                if (drug.getUseBefore() < 31 && drug.getEfficiency() > 0)
                        drug.updateEfficiency(-1);
                if (drug.getUseBefore() < 8 && drug.getEfficiency() > 0)
                        drug.updateEfficiency(-1);

        }
    }

    private void updateEfficiencyConsideringUseBefore(Drug drug) {
        if (drug.getUseBefore() >= 0) return ;
        if (isBottleOfWine(drug) && drug.getEfficiency() < 100) {
            drug.updateEfficiency(1);
            return ;
        }
        if (isInsulin(drug)) {
            drug.updateEfficiency(-drug.getEfficiency());
            return ;
        }
        if (drug.getEfficiency() > 0 && !isGrannyRecipe(drug))
            drug.updateEfficiency(-1);
    }

    private void decreaseUseBefore(Drug drug) {
        if (!isGrannyRecipe(drug)) drug.decreaseUseBefore();

    }

    private boolean isInsulin(Drug drug) {
        return drug.getName().equals("Insulin vial");
    }

    private boolean isBottleOfWine(Drug drug) {
        return drug.getName().equals("Old bottle of wine");
    }

    private boolean isGrannyRecipe(Drug drug) {
        return drug.getName().equals("Granny recipe");
    }
}