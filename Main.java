import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import helper.Helper;

public class Main {

    //read a input image file and output to output path
    public static boolean readImgToOutputImg(ColorConvertor translator, String inputPath, String outputPath, Color c)
            throws FileNotFoundException, NullPointerException, IOException, Exception {
        
        String fileExtension = "";
        if(!outputPath.equals("")){ 
            fileExtension = outputPath.substring(outputPath.length() - 3);
            //validate the file type, only allow output file as png image or txt file
            if(!fileExtension.equals("png") && !fileExtension.equals("txt")){
                System.out.println("Please make sure the output image file extension is ending by png or txt");
                return false;
            }
            //validate if the output file path is a directory, if so return false
            else if(Helper.isDirectory(outputPath)){
                System.out.println("The output file could not be a directory!");
                return false;
            }
        }

        TextImage img = new TextImage(translator, inputPath);
        if(fileExtension.equals("png")) img.toImgFile(outputPath, c);
        else if(fileExtension.equals("txt")) img.toTextFile(outputPath);
        else img.toTerminal();

        return true;
    }

    //read a input directory and output to output directory
    public static boolean readImgDirectoryToOutputDirectory(ColorConvertor translator, String inputDirectory, String outputDirectory, Color c)
            throws FileNotFoundException, NullPointerException, IOException, Exception {
        //if output path is a file, then print the error
        if(Helper.isFile(outputDirectory)){
            System.out.println("Please give a valid output directory path");
            return false;
        }
        File[] directoryListing = new File(inputDirectory).listFiles();
        if (directoryListing != null) {
            int index = 0;
            TextImage[] listOfImage = new TextImage[directoryListing.length];
            for (File child : directoryListing) {
                listOfImage[index] = new TextImage(translator, child.getPath());
                //remove all the contents in the file name except the number
                int num = Helper.getNumber(child.getName());
                if(!Helper.isFileExist(outputDirectory)){
                    boolean isSuccessful = Helper.createDirectory(outputDirectory);
                    if(!isSuccessful){
                        System.out.println("Couldn't create directory!");
                        return false;
                    }
                }
                listOfImage[index].toImgFile(outputDirectory + "/" + num + ".png", c);
                index++;
                Helper.printTaskBar(index, directoryListing.length, "Finished processing all the images to \"" + outputDirectory + "\"");
            }
        }
        else{
            System.out.println("The directory is empty!!");
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException, NullPointerException, IOException, Exception {
        String inputPath = "", outputPath  = "", textFile = "ASCII/Template1.txt";
        int numOfChar = 256;
        Color color = Color.BLACK;
        String help = "Operations: -i    Path of input directory/image/image url. (First operation)\n"
                    + "            -o    Path of output directory/image/txt which can be NULL if no output file. (Second operation)\n"
                    + "            -t    Path of characters file. default value: ASCII/Template1.txt (third operation)\n"
                    + "            -n    Number of unique characters between 0 and 256 (**note that the input has to be in 2^n form). default value: 256 (fourth operation)\n"
                    + "            -c    Color of the ASCII Art. (red, blue, green, orange, yellow, black) default value: black (fifth operation)\n"
                    + "            -h    Help menus\n"
                    + "            Example: bash execute.sh Sample/img/example_5.jpg Sample_Output/Output.png ASCII/Template1.txt 256 black\n";
        
        for(int i = 0, len = args.length; i < len; i++){
            switch(args[i].charAt(1)){
                case 'i':
                    inputPath = args[i+1];
                    break;
                case 'o':
                    outputPath = args[i+1].equals("NULL") ? "" : args[i+1];
                    break;
                case 't':
                    textFile = args[i+1];
                    break;
                case 'n':
                    numOfChar = Integer.parseInt(args[i+1]);
                    break;
                case 'c':
                    color = Helper.getColor(args[i+1]);
                    break;
                default:
                    System.out.print(help);
                    return;
            }
            i++;
        }

        boolean isSuccessful;
        String message;
        //handle case when the input file is not exist and not link
        if(!Helper.isFileExist(inputPath) && !Helper.isURL(inputPath)){
            System.out.println("Please give a valid input path");
            return;
        }
        System.out.println(inputPath + " =============> " + outputPath);
        ColorConvertor translator = new ColorConvertor(numOfChar, textFile);
        if(!Helper.isDirectory(inputPath)){
            isSuccessful = readImgToOutputImg(translator, inputPath, outputPath, color);
        }
        else{
            isSuccessful = readImgDirectoryToOutputDirectory(translator, inputPath, outputPath, color);
        }
        message = (isSuccessful ? "success import image to ": "Some errors occur when import image to ");
        System.out.println(message + (outputPath.equals("") ? "terminal" : outputPath));
    }
}
