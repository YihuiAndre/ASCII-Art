package Main;

import java.util.ArrayList;
import java.util.List;

import helper.Helper;

import java.awt.image.BufferedImage;

//an container class that store the image grayscale value in each pixel
public class GrayscaleImage implements Image{
    //store the 8 bit color value of grayscale
    private final List<List<Integer>> grayscale;
    private final int height;
    private final int width;

    public GrayscaleImage(BufferedImage image){
        this.grayscale = new ArrayList<>();
        width = image.getWidth();
        height = image.getHeight();
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                //append gray scale value into array list
                addGrayscale(y, Helper.getAverageColor(image, x, y));
            }
        }
    }

    @Override
    public List<List<Integer>> getColor(){
        return grayscale;
    }

    /**
     * return the gray scale value in specific coordinate
     * @param  x    stand for col
     * @param  y    stand for row
     * @return      the gray scale value
     */
    @Override
    public int getColor(int x, int y){
        return this.grayscale.get(y).get(x);
    }

    /**
     * return the list of the gray scale value in specific row
     * @param  y    stand for row
     * @return      the list of the gray scale
     */
    @Override
    public List<Integer> getColor(int y){
        return this.grayscale.get(y);
    }

    @Override
    public int getHeight(){
        return height;
    }
    @Override
    public int getWidth(){
        return width;
    }

    /**
     * append an grayscale value into the vector
     * @param  y    stand for row
     */
    public void addGrayscale(int y, int grayscaleVal){
        //may be should deal with the case when y is not equal to this.grayscale.size()
        
        //append a new row to the array list
        if(y == this.grayscale.size()) 
            this.grayscale.add(new ArrayList<>());
        this.grayscale.get(y).add(grayscaleVal);
    }

}
