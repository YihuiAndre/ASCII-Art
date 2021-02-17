package Main;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import com.squareup.gifencoder.GifEncoder;
import com.squareup.gifencoder.ImageOptions;

//A class implement the external library "gifencoder"
public class Gif {
    private ImageOptions options;
    private GifEncoder gif;
    private OutputStream outputStream;

    /**
     * Constructors, construct a gif from list of images
     * @param w             Width of the gif
     * @param h             Height of the gif
     * @param outputFile    Output file
     * @param delay         delay of second between each frame
     */
    public Gif(int w, int h, String outputFile, long delay) throws IOException {
        outputStream = new FileOutputStream(outputFile);
        this.options = new ImageOptions();
        options.setDelay(delay, TimeUnit.SECONDS);
        gif = new GifEncoder(outputStream, w, h, 0);
    }

    public Gif(BufferedImage[] images, int w, int h, String outputFile, long delay) throws IOException {
        this(w, h, outputFile, delay);
        for(BufferedImage image : images){
            gif.addImage(getRBG(image), options);
        }
    }

    public Gif(int[][][] images, int w, int h, String outputFile, long delay) throws IOException {
        this(w, h, outputFile, delay);
        for(int[][] rgbData : images){
            gif.addImage(rgbData, options);
        }
    }

    /**
     * Transform an image from bufferedImage to double array representation of rgb data
     * @param image Given an image in bufferedImage data type
     * @return      The representation of rgb data
     */
    private int[][] getRBG(BufferedImage image){
        int height = image.getHeight(), width = image.getWidth();
        int[][] rgbData = new int[height][width];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                rgbData[y][x] = image.getRGB(x, y);
            }
        }
        return rgbData;
    }

    /**
     * Append an image into gif file
     * @param image Given an image in bufferedImage data type
     */
    public void addImage(BufferedImage image) throws IOException {
        gif.addImage(getRBG(image), options);
    }

    public void addImage(int[][] image) throws IOException {
        gif.addImage(image, options);
    }

    /**
     * Finish writing the gif and close the gif file
     */
    public void finish() throws IOException {
        gif.finishEncoding();
        outputStream.close();
    }
}
