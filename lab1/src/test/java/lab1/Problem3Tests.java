package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Problem3Tests {

    @Test
    void shouldReturnTrue1_ifMatrixFlatten() {

        int[][] matrix = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        int[] resultArr = {1, 4, 7, 2, 5, 8, 3, 6, 9};

        assertArrayEquals(Problem3.flattenMatrix(matrix), resultArr);
    }

    @Test
    void shouldReturnTrue2_ifMatrixFlatten() {

        int[][] matrix = {{3, 2, 1},
                {3, 2, 1},
                {3, 2, 1}};
        int[] resultArr = {3, 3, 3, 2, 2, 2, 1, 1, 1};

        assertArrayEquals(Problem3.flattenMatrix(matrix), resultArr);
    }

    @Test
    void shouldReturnTrue3_ifMatrixFlatten() {

        int[][] matrix = new int[0][0];
        int[] resultArr = new int[0];

        assertArrayEquals(Problem3.flattenMatrix(matrix), resultArr);
    }
}