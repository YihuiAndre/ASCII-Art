import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.List;

//convert an image into text file format and able to output as text in terminal or store.
public class TextImage {
    List<List<Integer>> imgRBGArr;
    RBGCharConvertor translator;

    //constructor which take three parameter: 
    //1. the translator which translate the color into character
    //2. the path of image which can be the path for image or URL link
    //3. the boolean value that indicate whatever the second parameter is a link
    //4. the size of compress which require to compress the color in the image by nxn square
    //After the execution, store all the RBG value into 2-dimensional arraylist

    //Q: how to import url link or file path
    public TextImage(RBGCharConvertor translator, String imgPath, boolean isURL, int sizeOfCompress){
        this.translator = translator;
        this.imgRBGArr = new ArrayList<>();
        BufferedImage image = null; 
        try{
            if(isURL){
                URL url = new URL(imgPath);
                image = ImageIO.read(url);
            }
            else{
                FileInputStream stream = new FileInputStream(imgPath);
                image = ImageIO.read(stream);
            }
        }
        catch(FileNotFoundException err){
            err.printStackTrace();
        }
        catch(IOException err){
            err.printStackTrace();
        }
        catch(NullPointerException err){
            err.printStackTrace();
        }
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

    //overload constructor which miss the size of compression
    public TextImage(RBGCharConvertor translator, String imgPath, boolean isURL){
        this(translator, imgPath, isURL, 1);
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
            err.printStackTrace();
        }
        catch(IOException err){
            err.printStackTrace();
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
            err.printStackTrace();
        }
        catch(IOException err){
            err.printStackTrace();
        }
    }

    //output the img to the terminal
    public void print(){
        for(List<Integer> array : this.imgRBGArr){
            for(Integer val : array){
                System.out.print(translator.RBGToChar(val));
            }
            System.out.println("");
        }
    }

    //take the text image and convert it into png image format
    public void imgToImgFile(String filePath){
        int width = 15*this.imgRBGArr.size(), height = 15*this.imgRBGArr.size();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        //change the pen color
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(18f));
        for(int row = 0, rLen = this.imgRBGArr.size(); row < rLen; row++){
            for(int col = 0, cLen = this.imgRBGArr.get(row).size(); col < cLen; col++){
                char character = translator.RBGToChar(this.imgRBGArr.get(row).get(col));
                g.drawString(String.valueOf(character), 10*col, 15+row*15);
            }
        }
        g.dispose();
        try {
            //write file, but only work for png
            ImageIO.write(image, "png", new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
