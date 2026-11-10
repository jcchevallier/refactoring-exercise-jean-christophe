package com.hokla;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

class HoklaTest {

  private Inventory underTest;

  @BeforeEach
  void setUp() {
    List<Drug> drugs = List.of(
            new Drug("Normal Drug", 10, 20),
            new Drug("Old bottle of wine", 2, 0),
            new Drug("Normal Drug 2", 5, 7),
            new Drug("Granny recipe", 0, 150),
            new Drug("Granny recipe", -1, 80),
            new Drug("Insulin vial", 15, 20),
            new Drug("Insulin vial", 10, 49),
            new Drug("Insulin vial", 5, 49),
            // this ARN Vaccine Drug does not work properly yet
            new Drug("ARN Vaccine", 3, 6));
    underTest = new Inventory(drugs);
  }

  @Test
  void foo() {
    underTest.updateEfficiency();
   // assertEquals("fixme", app.drugs.get(0).name);
    assertThat(underTest.drugs.get(0).name).isEqualTo("Normal Drug");
  }
}
