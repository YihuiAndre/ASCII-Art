import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import helper.Helper;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, NullPointerException, IOException, Exception {
        String inputPath = "", outputPath  = "", textFile = "characters/ASCII.txt";
        int numOfChar = 256, compressSize = 1;
        String help = "Operation: -i    Path of input directory/image/image url.\n"
                    + "           -o    Path of output directory/image.\n"
                    + "           -t    Path of characters file. default value: characters/ASCII.txt\n"
                    + "           -n    Number of unique characters between 0 and 256 (**note that the input has to be in 2^n form). default value: 256\n"
                    + "           -c    Size of matrix need to be compressed. default value: 1\n"
                    + "           -h    Help menus\n"
                    + "           Example: java Main -d gif/anime -o output/anime\n";
        
        for(int i = 0, len = args.length; i < len; i++){
            switch(args[i].charAt(1)){
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
        File input = new File(inputPath);
        File output = new File(outputPath);
        //handle case when input and output file is not valid
        if(!(input.exists()) && !Helper.isURL(inputPath)){
            System.out.println("Please give a valid input path");
            return;
        }
        else if(!(output.exists())){
            System.out.println("Please give a valid output path");
            return;
        }
        System.out.println(inputPath + "--------------->" + outputPath);
        ColorConvertor translator = new ColorConvertor(numOfChar, textFile);
        if(!input.isDirectory()){
            //if output path is not a file path, print error message
            if(!(output.isFile())){
                System.out.println("Please give a valid output image path");
                return;
            }
            //validate the file type, only allow png image
            else if(!outputPath.substring(outputPath.length() - 3).equals("png")){
                System.out.println("Please make sure the image file extension is ending by png");
                return;
            }
            TextImage img = new TextImage(translator, inputPath, compressSize);
            img.toImgFile(outputPath);
            System.out.println("success import image to " + outputPath);
        }
        else{
            //if output path is not a directory path, print error message
            if(!(output.isDirectory())){
                System.out.println("Please give a valid output directory path");
                return;
            }
            int index = 0;
            File[] directoryListing = input.listFiles();
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
