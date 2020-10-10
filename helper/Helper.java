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

    //helper function
    public static int sum(int[] arr){
        int sum = 0;
        for(int i = 0, len = arr.length; i < len; i++){
            sum += arr[i];
        }
        return sum;
    }

    //print the task bar to display if the work is done or not
    public static void printTaskBar(double finished, double total, String description){
        int percentage = (int) ((finished / total) * 100);
        StringBuilder bar = new StringBuilder();
        bar.append("|");
        for(int i = 0; i < 50; i++){
            if(i < percentage/2) bar.append("=");
            else bar.append(" ");
        }
        bar.append("|" + String.format("%02d", percentage) + "%\r");
        System.out.print(bar);
        if(percentage == 100){
            System.out.println("\n" + description);
        }
    }
}
