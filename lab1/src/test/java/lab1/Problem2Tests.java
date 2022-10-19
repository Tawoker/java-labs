package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Problem2Tests {

    @Test
    void shouldReturnTrue1_ifSegregateEvenAndOddNumbers() {

        int[] array = {1, 2, 3, 4, 5};
        int[] resultArr = {2, 4, 1, 3, 5};

        assertArrayEquals(Problem2.segregateEvenAndOddNumbers(array), resultArr);
    }

    @Test
    void shouldReturnTrue2_ifSegregateEvenAndOddNumbers() {

        int[] array = {2, 2, 4, 4, 6};
        int[] resultArr = {2, 2, 4, 4, 6};

        assertArrayEquals(Problem2.segregateEvenAndOddNumbers(array), resultArr);
    }

    @Test
    void shouldReturnTrue3_ifSegregateEvenAndOddNumbers() {

        int[] array = {2, 4, 5, 3, 1};
        int[] resultArr = {2, 4, 5, 3, 1};

        assertArrayEquals(Problem2.segregateEvenAndOddNumbers(array), resultArr);
    }

}