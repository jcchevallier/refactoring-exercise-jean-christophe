package com.hokla;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

class HoklaTest {

  private Inventory underTest;

  @BeforeEach
  void setUp() {
    List<Drug> drugs = new ArrayList<>();
    underTest = new Inventory(drugs);
  }

  @ParameterizedTest
  @CsvSource({"Normal Drug A,2,20,1",
          "Normal Drug B,26,28,25",
          "Old bottle of wine,35,50,34",
          "Insulin vial,12,120,11",
          "Normal Drug F,5,50,4"})
  void shouldDecreaseUseBeforeOf1ForAnyDrugsExceptGrannyRecipe(String name, int useBefore, int efficiency,
                                             int expectedUseBefore) {
    underTest.drugs.add(new Drug(name, useBefore, efficiency));

    underTest.updateEfficiency();

    assertThat(underTest.drugs.get(0).useBefore).isEqualTo(expectedUseBefore);
  }

  @ParameterizedTest
  @CsvSource({"Normal Drug A,2,20,19",
              "Normal Drug B,26,28,27",
              "Normal Drug C,35,50,49",
              "Normal Drug D,22,7,6",
              "Normal Drug E,12,120,119",
              "Normal Drug F,5,50,49"})
  void shouldDecreaseEfficiencyOf1PercentBeforePeremptionDateForNormalDrugs(String name, int useBefore,
                                                                            int efficiency,
                                                                            int expectedEfficiency) {
    underTest.drugs.add(new Drug(name, useBefore, efficiency));

    underTest.updateEfficiency();

    assertThat(underTest.drugs.get(0).efficiency).isEqualTo(expectedEfficiency);
  }

  @ParameterizedTest
  @CsvSource({"Normal Drug A,0,20,18",
          "Normal Drug B,0,28,26",
          "Normal Drug C,0,50,48",
          "Normal Drug D,0,7,5",
          "Normal Drug E,0,120,118",
          "Normal Drug F,0,50,48"})
  void shouldDecreaseEfficiencyOf2PercentWhenPeremptionDateReachedForNormalDrugs(String name, int useBefore,
                                                                            int efficiency,
                                                                            int expectedEfficiency) {
    underTest.drugs.add(new Drug(name, useBefore, efficiency));

    underTest.updateEfficiency();

    assertThat(underTest.drugs.get(0).efficiency).isEqualTo(expectedEfficiency);
  }

  @ParameterizedTest
  @CsvSource({"Insulin vial,31,20,19",
          "Insulin vial,35,28,27",
          "Insulin vial,45,50,49",
          "Insulin vial,58,7,6",
          "Insulin vial,36,120,119",
          "Insulin vial,86,50,49"})
  void shouldDecreaseEfficiencyOf1PercentForInsulinVialOver30DaysBeforePeremptionDate(String name, int useBefore, int efficiency,
                                                                                      int expectedEfficiency) {
    underTest.drugs.add(new Drug(name, useBefore, efficiency));

    underTest.updateEfficiency();

    assertThat(underTest.drugs.get(0).efficiency).isEqualTo(expectedEfficiency);
  }

  @ParameterizedTest
  @CsvSource({"Insulin vial,30,20,18",
          "Insulin vial,28,28,26",
          "Insulin vial,15,50,48",
          "Insulin vial,21,7,5",
          "Insulin vial,8,120,118",
          "Insulin vial,13,50,48"})
  void shouldDecreaseEfficiencyOf2PercentForInsulinVialLessThan30DaysBeforePeremptionDate(String name, int useBefore,
                                                                                          int efficiency,
                                                                                      int expectedEfficiency) {
    underTest.drugs.add(new Drug(name, useBefore, efficiency));

    underTest.updateEfficiency();

    assertThat(underTest.drugs.get(0).efficiency).isEqualTo(expectedEfficiency);
  }

  @ParameterizedTest
  @CsvSource({"Insulin vial,7,20,17",
          "Insulin vial,5,28,25",
          "Insulin vial,2,50,47",
          "Insulin vial,1,7,4",
          "Insulin vial,6,120,117",
          "Insulin vial,3,50,47"})
  void shouldDecreaseEfficiencyOf3PercentForInsulinVialLessThanDays7DaysBeforePeremptionDate(String name, int useBefore,
                                                                                          int efficiency,
                                                                                          int expectedEfficiency) {
    underTest.drugs.add(new Drug(name, useBefore, efficiency));

    underTest.updateEfficiency();

    assertThat(underTest.drugs.get(0).efficiency).isEqualTo(expectedEfficiency);
  }

  @ParameterizedTest
  @CsvSource({"Insulin vial,0,20,0",
          "Insulin vial,0,28,0",
          "Insulin vial,0,50,0",
          "Insulin vial,0,7,0",
          "Insulin vial,0,120,0",
          "Insulin vial,0,50,0"})
  void shouldNullifyEfficiencyForInsulinVialWhenPeremptionDateReached(String name, int useBefore,
                                                                       int efficiency, int expectedEfficiency) {
    underTest.drugs.add(new Drug(name, useBefore, efficiency));

    underTest.updateEfficiency();

    assertThat(underTest.drugs.get(0).efficiency).isEqualTo(expectedEfficiency);
  }

  @ParameterizedTest
  @CsvSource({"Granny recipe,2147483647, 2147483647,20,20",
          "Granny recipe,44,44,28,28",
          "Granny recipe,58,58,50,50",
          "Granny recipe,200,200,7,7",
          "Granny recipe,10,10,120,120",
          "Granny recipe,32,32,50,50"})
  void shouldNotUpdateEfficiencyAndUseBeforeForGrannyRecipe(String name, int useBefore, int expectedUseBefore,
                                               int efficiency, int expectedEfficiency) {
    underTest.drugs.add(new Drug(name, useBefore, efficiency));

    underTest.updateEfficiency();

    assertThat(underTest.drugs.get(0).efficiency).isEqualTo(expectedEfficiency);
    assertThat(underTest.drugs.get(0).useBefore).isEqualTo(expectedUseBefore);
  }

  @ParameterizedTest
  @CsvSource({"Old bottle of wine, 2147483647,20,21",
          "Old bottle of wine,44,28,29",
          "Old bottle of wine,58,50,51",
          "Old bottle of wine,200,7,8",
          "Old bottle of wine,10,66,67",
          "Old bottle of wine,32,50,51"})
  void shouldIncreaseEfficiencyBy1ForOldBottleOfWineIfEfficiencyLesserThan100(String name, int useBefore,
                                                            int efficiency,int expectedEfficiency) {
    underTest.drugs.add(new Drug(name, useBefore, efficiency));

    underTest.updateEfficiency();

    assertThat(underTest.drugs.get(0).efficiency).isEqualTo(expectedEfficiency);
  }

  @ParameterizedTest
  @CsvSource({"Old bottle of wine, 2147483647,20,21",
          "Old bottle of wine,101,128,128",
          "Old bottle of wine,150,150,150",
          "Old bottle of wine,200,117,117",
          "Old bottle of wine,10,166,166",
          "Old bottle of wine,32,250,250"})
  void shouldNotUpdateEfficiencyForOldBottleOfWineIfEfficiencyGreaterThan100(String name, int useBefore,
                                                                             int efficiency,int expectedEfficiency) {
    underTest.drugs.add(new Drug(name, useBefore, efficiency));

    underTest.updateEfficiency();

    assertThat(underTest.drugs.get(0).efficiency).isEqualTo(expectedEfficiency);
  }

  @Nested
  class ArnVaccin {

    @Disabled
    @ParameterizedTest
    @CsvSource({"ARN Vaccin A,2,20,1",
            "ARN Vaccin B,26,28,25",
            "ARN Vaccin C,35,50,34",
            "ARN Vaccin D,12,120,11",
            "ARN Vaccin E,5,50,4"})
    void shouldDecreaseUseBeforeOf1ForArnVaccin(String name, int useBefore, int efficiency,
                                                                 int expectedUseBefore) {
      underTest.drugs.add(new Drug(name, useBefore, efficiency));

      underTest.updateEfficiency();

      assertThat(underTest.drugs.get(0).useBefore).isEqualTo(expectedUseBefore);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({"Normal Drug A,2,20,18",
            "Normal Drug B,26,28,26",
            "Normal Drug C,35,50,48",
            "Normal Drug D,22,7,5",
            "Normal Drug E,12,120,118",
            "Normal Drug F,5,50,48"})
    void shouldDecreaseEfficiencyOf2PercentBeforePeremptionDateForArnVaccin(String name, int useBefore,
                                                                              int efficiency,
                                                                              int expectedEfficiency) {
      underTest.drugs.add(new Drug(name, useBefore, efficiency));

      underTest.updateEfficiency();

      assertThat(underTest.drugs.get(0).efficiency).isEqualTo(expectedEfficiency);
    }
  }

}
