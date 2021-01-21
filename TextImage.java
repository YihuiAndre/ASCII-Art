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
import java.util.List;

//convert an image into text file format and able to output as text in terminal or store.
public class TextImage {
    //store the grayscale value of the image
    private final GrayscaleImage imgColor;
    private ColorConvertor translator;

    //constructor which take three parameter: 
    //1. translator: the translator which translate the color into character
    //2. imgPath: the path of image which can be the path for image or URL link
    //3. sizeOfCompress: the size of compress which require to compress the color in the image by nxn square
    //After the execution, store all the color value into 2-dimensional array list
    public TextImage(ColorConvertor translator, String imgPath, int sizeOfCompress)
            throws FileNotFoundException, IOException, NullPointerException, Exception{
        this.translator = translator;
        this.imgColor = new GrayscaleImage();
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
        //x and y coordinate is stand for the left and right corner coordinate of the square
        for(int y = 0; y < h; y+=sizeOfCompress){
            for(int x = 0; x < w; x+=sizeOfCompress){
                //prevent the case when it could not form a square by current x and y coordinate
                if(y + sizeOfCompress-1 >= h || x + sizeOfCompress-1 >= w){
                    continue;
                }
                this.imgColor.addGrayscale(y/sizeOfCompress, compressRBGToGrayscale(x, y, sizeOfCompress, image));
            }
        }
    }

    //overload constructor which miss the size of compression
    public TextImage(ColorConvertor translator, String imgPath)
            throws FileNotFoundException, NullPointerException, IOException, Exception{
        this(translator, imgPath, 1);
    }

    //compress multiple color values inside the nxn square into one value
    private int compressRBGToGrayscale(int startX, int startY, int sizeOfCompress, BufferedImage image){
        int red = 0, green = 0, blue = 0;
        for(int y = startY; y < startY+sizeOfCompress; y++){
            for(int x = startX; x < startX+sizeOfCompress; x++){
                Color c = new Color(image.getRGB(x,y));
                red += c.getRed();
                green += c.getGreen();
                blue += c.getBlue();
            }
        }
        int squareSize = sizeOfCompress*sizeOfCompress;
        red /= squareSize;
        green /= squareSize;
        blue /= squareSize;
        return (red+green+blue)/3;
    }

    //output the image in the text format into text file
    public void toTextFile(String filePath){
        try(FileWriter writer = new FileWriter(filePath)){
            for(List<Integer> array : this.imgColor.getGrayscale()){
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
            for(List<Integer> array : this.imgColor.getGrayscale()){
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
        for(List<Integer> array : this.imgColor.getGrayscale()){
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

    private void drawImg(Graphics g, int ascent, int descent){
        //render all the characters into image files
        for(int row = 0, rLen = this.imgColor.getRowSize(); row < rLen; row++){
            g.drawString(getStringRepresentation(this.imgColor.getGrayscale(row)), 0, ascent + (ascent+descent)*row);
        }
        g.dispose();
    }

    //take the text image and convert it into png image format
    public void toImgFile(String filePath, Color c){
        int fontSize = 10;
        FontMetrics fm = getFontMetrics(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));
        int ascent = fm.getAscent(), descent = fm.getDescent();
        int width = ((fontSize/5)*3)*this.imgColor.getColSize(0)+1, height = (ascent+descent)*this.imgColor.getRowSize()+1;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics graph = image.getGraphics();
        //set font as MONOSPACED because all characters have the same width
        graph.setFont(fm.getFont());
        //change the background
        graph.setColor(Color.WHITE);
        graph.fillRect(0, 0, width, height);
        graph.setColor(c);
        drawImg(graph, ascent, descent);
        try {
            //write file, but only work for png
            ImageIO.write(image, "png", new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
