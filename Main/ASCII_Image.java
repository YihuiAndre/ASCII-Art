package Main;

import java.io.File;
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

public class ASCII_Image {
    //store the grayscale value of the image
    private final GrayscaleImage imgColor;
    private ColorConvertor translator;

    /**
     * After the initialize the object, store all the color value into 2-dimensional array list
     * @param translator    the translator which translate the color into character
     * @param imgPath       the path of image which can be the path for image or URL link
     */
    public ASCII_Image(ColorConvertor translator, String imgPath)
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
                image = ImageIO.read(new File(imgPath));
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
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                //append gray scale value into array list
                this.imgColor.addGrayscale(y, Helper.getAverageColor(image, x, y));
            }
        }
    }

    /**
     * convert an image file into text file
     * @param filePath  given output text file location
     */
    public void toTextFile(String filePath){
        try(FileWriter writer = new FileWriter(filePath)){
            for(List<Integer> array : this.imgColor.getGrayscale()){
                for(Integer val : array){
                    //write down the character representation of the gray scale value into text file
                    writer.write(translator.colorToChar(val));
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

    /**
     * Output an image file into terminal
     */
    public void toTerminal(){
        for(List<Integer> array : this.imgColor.getGrayscale()){
            for(Integer val : array){
                System.out.print(translator.colorToChar(val));
            }
            System.out.println("");
        }
    }

    /**
     * Obtain the font metrics with given font
     * @param font  Given font
     * @return      FontMetrics datatype corresponding with the given font
     */
    private FontMetrics getFontMetrics(Font font){
        Canvas d = new Canvas();
        FontMetrics fm = d.getFontMetrics(font);
        return fm;
    }

    /**
     * Convert all the color value into corresponding character and concatenate them into a string
     * @param colorList     List of color value
     * @return              String concatenation of list of characters
     */
    private String getStringRepresentation(List<Integer> colorList){    
        StringBuilder builder = new StringBuilder(colorList.size());
        for(Integer colorValue: colorList){
            builder.append(translator.colorToChar(colorValue));
        }
        return builder.toString();
    }

    /**
     * draw image into the Given Graphics g
     * @param g         Graphics g
     * @param ascent    font ascent
     * @param descent   font descent
     */
    private void drawImg(Graphics g, int ascent, int descent){
        //render all the characters into image files
        for(int row = 0, rLen = this.imgColor.getRowSize(); row < rLen; row++){
            g.drawString(getStringRepresentation(this.imgColor.getGrayscale(row)), 0, ascent + (ascent+descent)*row);
        }
        g.dispose();
    }

    /**
     * return an BufferedImage
     * @param c         color of the pen to draw the character
     */
    public BufferedImage getBufferedImage(Color c){
        int fontSize = 10;
        FontMetrics fm = getFontMetrics(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));
        int ascent = fm.getAscent(), descent = fm.getDescent();
        //set up width and height of the image
        int width = ((fontSize/5)*3)*this.imgColor.getColSize(0)+1, height = (ascent+descent)*this.imgColor.getRowSize()+1;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics graph = image.getGraphics();
        //set font as MONOSPACED because all characters have the same width
        graph.setFont(fm.getFont());
        //change the background into white
        graph.setColor(Color.WHITE);
        graph.fillRect(0, 0, width, height);
        graph.setColor(c);
        drawImg(graph, ascent, descent);
        return image;
    }

    /**
     * Output the image as png format file
     * @param filePath  Given output file location
     * @param c         color of the pen to draw the character
     */
    public void toImgFile(String filePath, Color c){
        try {
            //output a png image file
            ImageIO.write(getBufferedImage(c), "png", new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
