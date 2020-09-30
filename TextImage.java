import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import helper.Helper;

import java.util.ArrayList;
import java.util.List;

//convert an image into text file format and able to output as text in terminal or store.
public class TextImage {
    List<List<Integer>> imgRBGArr;
    RBGCharConvertor translator;

    //constructor which take three parameter: 
    //1. the translator which translate the color into character
    //2. the filepath which is the path for image
    //3. the size of compress which require to compress the color in the image by nxn square
    //After the execution, store all the RBG value into 2-dimensional arraylist
    public TextImage(RBGCharConvertor translator, String filepath, int sizeOfCompress){
        this.translator = translator;
        this.imgRBGArr = new ArrayList<>();
        BufferedImage image = null; 
        try(FileInputStream stream = new FileInputStream(filepath)){
            image = ImageIO.read(stream);
            int w = image.getWidth(); 
            int h = image.getHeight();
            System.out.println("width: " + w + " height: " + h);
            //store the value into arraylist
            int avgColor = 0, index = 0;
            //x and y coordinate is stand for the left and right corner coordinate of the square
            for(int y = 0; y < h; y+=sizeOfCompress){
                this.imgRBGArr.add(new ArrayList<>());
                for(int x = 0; x < w; x+=sizeOfCompress){
                    //prevent the case when it could not form a square by current x and y coordinate
                    if(y + sizeOfCompress-1 >= h || x + sizeOfCompress-1 >= w){
                        break;
                    }
                    avgColor = compressRBG(x, y, sizeOfCompress, image);
                    this.imgRBGArr.get(index).add(avgColor);
                }
                index++;
            }
            System.out.println("Reading Complete");
        }
        catch(FileNotFoundException err){

        }
        catch(IOException err){

        }
    }

    public TextImage(RBGCharConvertor translator, String filepath){
        this(translator, filepath, 1);
    }

    //compress mutiple RBG values inside the square into one value
    private int compressRBG(int startX, int startY, int sizeOfCompress, BufferedImage image){
        int avgColor = 0, red, green, blue;
        Color c;
        for(int y = startY; y < startY+sizeOfCompress; y++){
            for(int x = startX; x < startX+sizeOfCompress; x++){
                c = new Color(image.getRGB(x,y));
                red = c.getRed();
                green = c.getGreen();
                blue = c.getBlue();
                avgColor += (red+green+blue)/3;
            }
        }
        avgColor /= sizeOfCompress*sizeOfCompress;
        return avgColor;
    }

    //output the image into text file
    public void imgToTextFile(String filePath){
        try(FileWriter writer = new FileWriter(filePath)){
            for(List<Integer> array : this.imgRBGArr){
                for(Integer val : array){
                    writer.write(translator.RBGToChar(val));
                }
                writer.write("\n");
            }
        }
        catch(FileNotFoundException err){

        }
        catch(IOException err){

        }
    }

    //output the color into text file
    public void RBGToTextFile(String filePath){
        try(FileWriter writer = new FileWriter(filePath)){
            for(List<Integer> array : this.imgRBGArr){
                for(Integer val : array){
                    writer.write(val + " ");
                }
                writer.write("\n");
            }
        }
        catch(FileNotFoundException err){

        }
        catch(IOException err){

        }
    }

    //output the img to the terminal
    public void toTerminal(){
        for(List<Integer> array : this.imgRBGArr){
            for(Integer val : array){
                System.out.print(translator.RBGToChar(val));
            }
            System.out.println("");
        }
    }
}
