package helper;

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
}
