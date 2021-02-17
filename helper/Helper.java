package helper;

import java.awt.Color;
import java.awt.image.BufferedImage;
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
        System.out.print("\r");
        int percentage = (int) ((finished / total) * 100);
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            if (i < percentage / 2)
                bar.append("\u2588");
            else
                bar.append(" ");
        }
        bar.append("  " + String.format("%02d", percentage) + "%\r");
        System.out.print(bar);
        if (percentage == 100) {
            System.out.println("\n" + description);
        }
    }

    /**
     * check if the given url is worked or not
     * 
     * @param url   An String represent the url
     * @return      the boolean result of validation
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
     * @param filePath  An String represent the directory path
     * @return          the boolean result of validation
     */
    public static boolean isDirectory(String filePath) {
        File file = new File(filePath);
        return file.isDirectory();
    }

    /**
     * check if the given filePath is existed or not
     * 
     * @param filePath  An String represent the file path
     * @return          the boolean result of validation
     */
    public static boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * check if the given filePath is a file or not
     * 
     * @param filePath  An String represent the file path
     * @return          the boolean result of validation
     */
    public static boolean isFile(String filePath) {
        File file = new File(filePath);
        return file.isFile();
    }

    /**
     * create a new directory in the given path
     * 
     * @param path  An String represent the path
     * @return      the boolean result if true when directory is created, otherwise false
     */
    public static boolean createDirectory(String path) {
        // Creating a File object
        File file = new File(path);
        // Creating the directory
        return file.mkdir();
    }

    /**
     * obtain first appeared number from left to right in the string and return it.
     * If not digit in the string return -1
     * 
     * @param s An given string
     * @return  the number extract from the string
     */
    public static int getNumber(String s) {
        int n = s.length();
        int i = 0;
        while(i < n && !Character.isDigit(s.charAt(i))){
            i++;
        }
        //if there is not digi, return -1
        if(i == s.length()) return -1;
        int j = i;
        while(j < n && Character.isDigit(s.charAt(j))){
            j++;
        }
        return Integer.parseInt(s.substring(i, j));
    }

    /**
     * return a set of random number from the range of start to end (exclusive) with
     * the length of L 
     * ex. start = 0, end = 10, L = 3 [1,2,3,4,5,6,7,8,9] -> [3,6,5,4,1,2,9,8,7] -> [3,5,6], 
     * start = 3, end = 9, L = 4 [3,4,5,6,7,8] -> [7,8,4,5,3,6] -> [7,8,4,5]
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

    /**
     * return the number of characters in the file
     * @param filePath file path to read
     * @return the number of characters
     */
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

    /**
     * return the average color of rgb of the image pixel in specific coordinate
     * @param image given image file
     * @param x pixel coordinate
     * @param y pixel coordinate
     * @return the average color of rgb
     */
    public static int getAverageColor(BufferedImage image, int x, int y){
        Color c = new Color(image.getRGB(x,y));
        return (c.getRed()+c.getGreen()+c.getBlue())/3;
    }

    /**
     * return the color
     * @param color color in string representation
     * @return the color 
     */
    public static Color getColor(String color){
        switch(color.toLowerCase()){
            case "red":
                return Color.RED;
            case "blue":
                return Color.BLUE;
            case "green":
                return Color.GREEN;
            case "orange":
                return Color.ORANGE;
            case "yellow":
                return Color.YELLOW;
        }
        return Color.BLACK;
    }
}
