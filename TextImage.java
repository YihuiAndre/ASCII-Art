import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.FontMetrics;
import java.awt.Canvas;
import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.List;

//convert an image into text file format and able to output as text in terminal or store.
public class TextImage {
    //store the RGBA value of the image
    private final List<List<Integer>> RGBAArr;
    private ColorConvertor translator;

    //constructor which take three parameter: 
    //1. translator: the translator which translate the color into character
    //2. imgPath: the path of image which can be the path for image or URL link
    //3. isURL: the boolean value that indicate whatever the second parameter is a link
    //4. sizeOfCompress: the size of compress which require to compress the color in the image by nxn square
    //After the execution, store all the RGBA value into 2-dimensional arraylist
    public TextImage(ColorConvertor translator, String imgPath, boolean isURL, int sizeOfCompress)
            throws FileNotFoundException, IOException, NullPointerException{
        this.translator = translator;
        this.RGBAArr = new ArrayList<>();
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
            throw err;
        }
        catch(IOException err){
            throw err;
        }
        catch(NullPointerException err){
            throw err;
        }
        int w = image.getWidth(); 
        int h = image.getHeight();
        System.out.println("width: " + w + " height: " + h);
        int eightBitColor = 0, index = 0;
        //x and y coordinate is stand for the left and right corner coordinate of the square
        for(int y = 0; y < h; y+=sizeOfCompress){
            this.RGBAArr.add(new ArrayList<>());
            for(int x = 0; x < w; x+=sizeOfCompress){
                //prevent the case when it could not form a square by current x and y coordinate
                if(y + sizeOfCompress-1 >= h || x + sizeOfCompress-1 >= w){
                    continue;
                }
                eightBitColor = compressRGBA(x, y, sizeOfCompress, image);
                this.RGBAArr.get(index).add(eightBitColor);
            }
            index++;
        }
        System.out.println("Reading Complete");
    }

    //overload constructor which miss the size of compression
    public TextImage(ColorConvertor translator, String imgPath, boolean isURL)
            throws FileNotFoundException, NullPointerException, IOException {
        this(translator, imgPath, isURL, 1);
    }

    //compress mutiple RGBA values inside the nxn square into one value
    private int compressRGBA(int startX, int startY, int sizeOfCompress, BufferedImage image){
        int eightBitColor = 0, red = 0, green = 0, blue = 0, alpha = 0;
        Color c;
        for(int y = startY; y < startY+sizeOfCompress; y++){
            for(int x = startX; x < startX+sizeOfCompress; x++){
                c = new Color(image.getRGB(x,y));
                red += c.getRed();
                green += c.getGreen();
                blue += c.getBlue();
                alpha += c.getAlpha();
            }
        }
        int squareSize = sizeOfCompress*sizeOfCompress;
        eightBitColor = ColorConvertor.fromColor(red/squareSize, green/squareSize, blue/squareSize, alpha/squareSize);
        return eightBitColor;
    }

    //output the image in the textformat into text file
    public void toTextFile(String filePath){
        try(FileWriter writer = new FileWriter(filePath)){
            for(List<Integer> array : this.RGBAArr){
                for(Integer val : array){
                    writer.write(translator.RGBAToChar(val));
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

    //output the color RGBA as integer value into text file
    public void RGBAToTextFile(String filePath){
        try(FileWriter writer = new FileWriter(filePath)){
            for(List<Integer> array : this.RGBAArr){
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
        for(List<Integer> array : this.RGBAArr){
            for(Integer val : array){
                System.out.print(translator.RGBAToChar(val));
            }
            System.out.println("");
        }
    }

    private FontMetrics getFontMetrics(Font font){
        Canvas d = new Canvas();
        FontMetrics fm = d.getFontMetrics(font);
        return fm;
    }

    //Convert all the RGBA value into character and concatenta them into string
    private String getStringRepresentation(List<Integer> list){    
        StringBuilder builder = new StringBuilder(list.size());
        for(Integer RGBAValue: list){
            builder.append(translator.RGBAToChar(RGBAValue));
        }
        return builder.toString();
    }

    //take the text image and convert it into png image format
    public void toImgFile(String filePath){
        int fontSize = 10;
        FontMetrics fm = getFontMetrics(new Font(Font.MONOSPACED, Font.PLAIN, fontSize));
        int ascent = fm.getAscent(), descent = fm.getDescent();
        int width = ((fontSize/5)*3)*this.RGBAArr.get(0).size()+1, height = (ascent+descent)*this.RGBAArr.size()+1;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        //change the pen color
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        //set font as MONOSPACED becuase all characters have the same width
        g.setFont(fm.getFont());
        //render all the characters into image files
        for(int row = 0, rLen = this.RGBAArr.size(); row < rLen; row++){
            //System.out.println(g.getFontMetrics().charsWidth(getStringRepresentation(this.RGBAArr.get(row)).toCharArray(), 0, this.RGBAArr.get(row).size()));
            g.drawString(getStringRepresentation(this.RGBAArr.get(row)), 0, ascent + (ascent+descent)*row);
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
