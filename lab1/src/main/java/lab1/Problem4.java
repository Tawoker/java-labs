package lab1;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Problem4 {

    /**
     * Метод isGeometricProgression определяет, является ли данная последовательность чисел numbers геометрической
     * прогрессией (возможно, при перестановке элементов)
     *
     * @param numbers строка, содержащая n положительных целых чисел, разделенных запятой
     * @return true, если последовательность является геометрической прогрессией
     *         false, если последовательность не является геометрической прогрессией
     *
     * ПРИМЕР1:
     * Вход: numbers = "1,2,4,8,16"
     * Выход: true
     *
     * ПРИМЕР2:
     * Вход: numbers = "16,2,8,1,4"
     * Выход: true (так как в результате перестановки элементов можно получить геометрическую прогрессию [1,2,4,8,16])
     *
     * ПРИМЕР3:
     * Вход: numbers = "2,3,5"
     * Выход: false
     */
    public static boolean isGeometricProgression(String numbers) {
        String[] strNumbers = numbers.split(",");
        float[] floatNumbers = new float[strNumbers.length];
        for (int i = 0; i < strNumbers.length; i++){
            floatNumbers[i] = Float.parseFloat(strNumbers[i]);
        }
        Arrays.sort(floatNumbers);
        float denominator = floatNumbers[0]/floatNumbers[1];
        for(int i = 1; i < floatNumbers.length - 1; i++){
            if (floatNumbers[i]/floatNumbers[i+1] != denominator)
                return false;
        }

        return true;
    }

}
