import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import helper.Helper;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, NullPointerException, IOException, Exception {
        String inputPath = "", outputPath  = "", textFile = "characters/ASCII.txt";
        int numOfChar = 256, compressSize = 1;
        boolean isDirectory =  false;
        String help = "Operation: -d    Path of input directory.\n"
                    + "           -i    Path of an image.\n"
                    + "           -o    Path of output directory/image.\n"
                    + "           -t    Path of characters file. default value: characters/ASCII.txt\n"
                    + "           -n    Number of unique characters. default value: 256\n"
                    + "           -c    Size of matrix need to be compressed. default value: 1\n"
                    + "           -h    Help menus\n"
                    + "           Example: java Main -d gif/anime -o output/anime\n";
        
        for(int i = 0, len = args.length; i < len; i++){
            switch(args[i].charAt(1)){
                case 'd':
                    inputPath = args[i+1];
                    isDirectory = true;
                    break;
                case 'i':
                    inputPath = args[i+1];
                    break;
                case 'o':
                    outputPath = args[i+1];
                    break;
                case 't':
                    textFile = args[i+1];
                    break;
                case 'n':
                    numOfChar = Integer.parseInt(args[i+1]);
                    break;
                case 'c':
                    compressSize = Integer.parseInt(args[i+1]);
                    break;
                default:
                    System.out.print(help);
                    return;
            }
            i++;
        }
        System.out.println(inputPath + "--------------->" + outputPath);
        ColorConvertor translator = new ColorConvertor(numOfChar, textFile);
        if(!isDirectory){
            TextImage img = new TextImage(translator, inputPath, compressSize);
            img.toImgFile(outputPath);
            System.out.println("success import image to " + outputPath);
        }
        else{
            int index = 0;
            File dir = new File(inputPath);
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                TextImage[] listOfImage = new TextImage[directoryListing.length];
                for (File child : directoryListing) {
                    // Do something with child
                    listOfImage[index] = new TextImage(translator, child.getPath(), compressSize);
                    //remove all the contents in the file name except the number
                    int num = Integer.parseInt(child.getName().replaceAll("[\\D]", ""));
                    listOfImage[index].toImgFile(outputPath + "/" + num + ".png");
                    index++;
                    Helper.printTaskBar(index, directoryListing.length, "Finished processing all the images to \"" + outputPath + "\"");
                }
            }
            else{
                System.out.println("Please give a valid directory path");
            }
        }
    }
}
