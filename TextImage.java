import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.FontMetrics;
import java.awt.Canvas;
import javax.imageio.ImageIO;

import helper.Helper;

import java.util.ArrayList;
import java.util.List;

//convert an image into text file format and able to output as text in terminal or store.
public class TextImage {
    //store the color value of the image
    private final RGBImage imgColor;
    private ColorConvertor translator;

    //constructor which take three parameter: 
    //1. translator: the translator which translate the color into character
    //2. imgPath: the path of image which can be the path for image or URL link
    //3. sizeOfCompress: the size of compress which require to compress the color in the image by nxn square
    //After the execution, store all the color value into 2-dimensional array list
    public TextImage(ColorConvertor translator, String imgPath, int sizeOfCompress)
            throws FileNotFoundException, IOException, NullPointerException, Exception{
        this.translator = translator;
        this.imgColor = new RGBImage();
        BufferedImage image = null; 
        try{
            if(Helper.isURL(imgPath)){
                URL url = new URL(imgPath);
                image = ImageIO.read(url);
            }
            else{
                FileInputStream stream = new FileInputStream(imgPath);
                image = ImageIO.read(stream);
            }
        }
        catch(FileNotFoundException err){
            throw err;
        }
        catch(IOException err){
            throw err;
        }
        catch(NullPointerException err){
            throw err;
        }
        int w = image.getWidth(), h = image.getHeight();
        //store the RGB value
        List<List<int[]>> RGB = new ArrayList<>();
        int[] RGBVal;
        double rRatio = 1/3.0, gRatio = 1/3.0, bRatio = 1/3.0;
        //store the sum of color
        int sum;
        //System.out.println("Image width: " + w + " and height: " + h);
        //x and y coordinate is stand for the left and right corner coordinate of the square
        for(int y = 0; y < h; y+=sizeOfCompress){
            RGB.add(new ArrayList<>());
            for(int x = 0; x < w; x+=sizeOfCompress){
                //prevent the case when it could not form a square by current x and y coordinate
                if(y + sizeOfCompress-1 >= h || x + sizeOfCompress-1 >= w){
                    continue;
                }
                RGBVal = compressColor(x, y, sizeOfCompress, image);
                RGB.get(y/sizeOfCompress).add(RGBVal);
                //prevent the case when red, green and blue are 0
                sum = (Helper.sum(RGBVal) == 0 ? 1 : Helper.sum(RGBVal));
                rRatio = (rRatio+ (double) RGBVal[0]/sum)/2;
                gRatio = (gRatio+ (double) RGBVal[1]/sum)/2;
                bRatio = (bRatio+ (double) RGBVal[2]/sum)/2;
                //Helper.printTaskBar(y*(w/sizeOfCompress) + (x+1), (h/sizeOfCompress)*(w/sizeOfCompress), "Finished calculating the ratio of color");
            }
        }

        this.imgColor.setRGBRatio(rRatio, gRatio, bRatio);
        //convert all the RGB color into 8 bit and store inside the RGBImage object
        for(int y = 0, yLen = RGB.size(); y < yLen; y++){
            for(int x = 0, xLen = RGB.get(y).size(); x < xLen; x++){
                RGBVal = RGB.get(y).get(x);
                this.imgColor.addRGB(y, ColorConvertor.fromColor(RGBVal[0], RGBVal[1], RGBVal[2], this.imgColor.getRGBRatio()));
            }
        }
        //System.out.println("Reading Complete " + (isURL ? "Link": "File") + "---------------");
    }

    //overload constructor which miss the size of compression
    public TextImage(ColorConvertor translator, String imgPath)
            throws FileNotFoundException, NullPointerException, IOException, Exception{
        this(translator, imgPath, 1);
    }

    //compress multiple color values inside the nxn square into one value
    private int[] compressColor(int startX, int startY, int sizeOfCompress, BufferedImage image){
        int red = 0, green = 0, blue = 0;
        Color c = null;
        for(int y = startY; y < startY+sizeOfCompress; y++){
            for(int x = startX; x < startX+sizeOfCompress; x++){
                c = new Color(image.getRGB(x,y));
                red += c.getRed();
                green += c.getGreen();
                blue += c.getBlue();
            }
        }
        int squareSize = sizeOfCompress*sizeOfCompress;
        red /= squareSize;
        green /= squareSize;
        blue /= squareSize;
        return new int[]{red, green, blue};
    }

    //output the image in the text format into text file
    public void toTextFile(String filePath){
        try(FileWriter writer = new FileWriter(filePath)){
            for(List<Integer> array : this.imgColor.getColorVal()){
                for(Integer val : array){
                    writer.write(translator.colorToChar(val));
                }
                writer.write("\n");
            }
            //System.out.println("Finished importing image into text file: " + filePath);
        }
        catch(FileNotFoundException err){
            err.printStackTrace();
        }
        catch(IOException err){
            err.printStackTrace();
        }
    }

    //output the color color as integer value into text file
    public void colorToTextFile(String filePath){
        try(FileWriter writer = new FileWriter(filePath)){
            for(List<Integer> array : this.imgColor.getColorVal()){
                for(Integer val : array){
                    writer.write(val + " ");
                }
                writer.write("\n");
            }
            //System.out.println("Finished importing image RGB value into text file: " + filePath);
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
        for(List<Integer> array : this.imgColor.getColorVal()){
            for(Integer val : array){
                System.out.print(translator.colorToChar(val));
            }
            System.out.println("");
        }
    }

    private FontMetrics getFontMetrics(Font font){
        Canvas d = new Canvas();
        FontMetrics fm = d.getFontMetrics(font);
        return fm;
    }

    //Convert all the color value into character and concatenate them into string
    private String getStringRepresentation(List<Integer> list){    
        StringBuilder builder = new StringBuilder(list.size());
        for(Integer colorValue: list){
            builder.append(translator.colorToChar(colorValue));
        }
        return builder.toString();
    }

    //take the text image and convert it into png image format
    public void toImgFile(String filePath){
        int fontSize = 10;
        FontMetrics fm = getFontMetrics(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));
        int ascent = fm.getAscent(), descent = fm.getDescent();
        int width = ((fontSize/5)*3)*this.imgColor.getColSize(0)+1, height = (ascent+descent)*this.imgColor.getRowSize()+1;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        //change the pen color
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        //set font as MONOSPACED because all characters have the same width
        g.setFont(fm.getFont());
        //render all the characters into image files
        for(int row = 0, rLen = this.imgColor.getRowSize(); row < rLen; row++){
            //System.out.println(g.getFontMetrics().charsWidth(getStringRepresentation(this.colorVal.get(row)).toCharArray(), 0, this.colorVal.get(row).size()));
            g.drawString(getStringRepresentation(this.imgColor.getRGB(row)), 0, ascent + (ascent+descent)*row);
        }
        g.dispose();
        //System.out.println("Finished importing image into image file: " + filePath);
        try {
            //write file, but only work for png
            ImageIO.write(image, "png", new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
