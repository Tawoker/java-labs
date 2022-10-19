package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Problem4Tests {

    @Test
    void shouldReturnTrue1_ifIsGeometricProgression() {
        String numbers = "1,2,4,8,16";

        assertTrue(Problem4.isGeometricProgression(numbers));
    }

    @Test
    void shouldReturnTrue2_ifIsGeometricProgression() {
        String numbers = "1,1.5,2.25";

        assertTrue(Problem4.isGeometricProgression(numbers));
    }

    @Test
    void shouldReturnTrue3_ifIsGeometricProgression() {
        String numbers = "90,10,30,270";

        assertTrue(Problem4.isGeometricProgression(numbers));
    }

    @Test
    void shouldReturnFalse_ifIsGeometricProgression() {
        String numbers = "2,2,4,8,16";

        assertFalse(Problem4.isGeometricProgression(numbers));
    }
}