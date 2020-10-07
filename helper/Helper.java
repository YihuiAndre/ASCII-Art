package helper;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static <T> void printArr(List<List<T>> twoDArr) {
        for(List<T> array : twoDArr){
            for(T val : array){
                System.out.print(val + " ");
            }
            System.out.println("");
        }
    }

    //helper function
    public static int sum(int[] arr){
        int sum = 0;
        for(int i = 0, len = arr.length; i < len; i++){
            sum += arr[i];
        }
        return sum;
    }
}
