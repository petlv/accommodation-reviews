package org.softuni.accommodationreviews;

import org.junit.Test;
import org.softuni.accommodationreviews.services.TestCalculationService;

import static junit.framework.TestCase.assertEquals;


public class CalculationServiceTests {

    @Test
    public void testSum_positiveIntegers_expectSumToBeTrue() {
        // arrange
        TestCalculationService calculationService = new TestCalculationService();

        // act
        long sum = calculationService.sum(100, 250);

        // assert
        assertEquals("Sum mismatch", 350, sum);
    }

    @Test
    public void testSum_positiveIntegersUpperBound_expectSumToBeTrue() {
        // arrange
        TestCalculationService calculationService = new TestCalculationService();

        // act
        // 2147483647+500
        // 2147484147
        // - 5
        // 2147484142
        long sum = calculationService.sum(Integer.MAX_VALUE - 5, 500);

        // assert
        assertEquals("Sum mismatch", 2147484142L, sum);
    }
}
