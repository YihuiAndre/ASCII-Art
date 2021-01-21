package helper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Helper {
    /**
     * print the two Dimension arraylist
     * 
     * @param twoDArr Two Dimension arraylist
     */
    public static <T> void printArr(List<List<T>> twoDArr) {
        for (List<T> array : twoDArr) {
            for (T val : array) {
                System.out.print(val + " ");
            }
            System.out.println("");
        }
    }

    /**
     * print the task bar in the same line to display if the work is done or not
     * 
     * @param finished    A double value that represent how much work is done which
     *                    finished < total
     * @param total       A double value that represent how much work need to be
     *                    done
     * @param description A successful message after the work is finished
     */
    public static void printTaskBar(double finished, double total, String description) {
        int percentage = (int) ((finished / total) * 100);
        StringBuilder bar = new StringBuilder();
        bar.append("|");
        for (int i = 0; i < 50; i++) {
            if (i < percentage / 2)
                bar.append("=");
            else
                bar.append(" ");
        }
        bar.append("|" + String.format("%02d", percentage) + "%\r");
        System.out.print(bar);
        if (percentage == 100) {
            System.out.println("\n" + description);
        }
    }

    /**
     * check if the given url is worked or not
     * 
     * @param url An String represent the url
     * @return the boolean result of validation
     */
    public static boolean isURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    /**
     * check if the given filePath is a directory or not
     * 
     * @param filePath An String represent the directory path
     * @return the boolean result of validation
     */
    public static boolean isDirectory(String filePath) {
        File file = new File(filePath);
        return file.isDirectory();
    }

    /**
     * check if the given filePath is a existed file or not
     * 
     * @param filePath An String represent the file path
     * @return the boolean result of validation
     */
    public static boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * check if the given filePath is a file or not
     * 
     * @param filePath An String represent the file path
     * @return the boolean result of validation
     */
    public static boolean isFile(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }

    /**
     * create a new directory in the given path
     * 
     * @param path An String represent the path
     * @return the boolean result if true when directory is created, otherwise false
     */
    public static boolean createDirectory(String path) {
        // Creating a File object
        File file = new File(path);
        // Creating the directory
        return file.mkdir();
    }

    /**
     * obtain number from the string (May want to add -1 as return if there is not
     * number in string)
     * 
     * @param s An given string
     * @return the number extract from the string
     */
    public static int getNumber(String s) {
        return Integer.parseInt(s.replaceAll("[\\D]", ""));
    }

    /**
     * return a set of random number from the range of start to end (exclusive) with
     * the length of L ex. start = 0, end = 10, L = 3 [3,6,5,4,1,2,9,8,7] -> [3,5,6]
     * this is the fianl result
     * 
     * @param start An integer value represent the starting number
     * @param end   An integer value represent the ending number
     * @param L     the size of the set
     * @return the set of random number from start to end with length of L
     */
    public static Set<Integer> randomNumberList(int start, int end, int L) {
        List<Integer> random = new ArrayList<Integer>();
        for (int i = start; i < end; i++) {
            random.add(i);
        }
        if (end - start == L)
            return new HashSet<>(random);
        Collections.shuffle(random);
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < L; i++) {
            res.add(random.get(i));
        }
        return res;
    }

    public static int getFileLength(String filePath) throws IOException {
        int fileLen = 0, data = 0;
        try(FileReader fileReader = new FileReader(filePath)){
            while(data != -1){
                data = fileReader.read();
                fileLen++;
            }
            fileLen--;
        }
        return fileLen;
    }
}
