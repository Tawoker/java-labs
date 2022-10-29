package lab1;

public class Problem2 {

    /**
     * Метод segregateEvenAndOddNumbers разделяет четные и нечетные числа в массиве array, т.у. возвращает массив,
     * в котором сперва записаны все четные числа массива array в порядке их следования, а затем все нечетные числа
     * массива array в порядке их следования.
     *
     * @param array массив положительных чисел
     * @return массив с разделенными четными и нечетными числами
     *
     * ПРИМЕР:
     * Вход: array = [2, 1, 5, 6, 8]
     * Выход: [2, 6, 8, 1, 5]
     */
    public static int[] segregateEvenAndOddNumbers(int[] array) {
        int[] result = new int[array.length];
        int counterEven = 0, indexEven = 0, indexOdd;

        for(int i = 0; i < array.length; i++){
            if(array[i] % 2 == 0)
                counterEven++;
        }
        indexOdd = counterEven;
        for(int i = 0; i < array.length; i++){
            if(array[i] % 2 == 0){
                result[indexEven] = array[i];
                indexEven++;
            } else {
                result[indexOdd] = array[i];
                indexOdd++;
            }
        }
        return result;
    }

}
