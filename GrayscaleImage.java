import java.util.ArrayList;
import java.util.List;

//an container class that store the image grayscale value in each pixel
public class GrayscaleImage {
    //store the 8 bit color value of grayscale
    private final List<List<Integer>> grayscale;
    
    public GrayscaleImage(){
        this.grayscale = new ArrayList<>();
    }

    public List<List<Integer>> getGrayscale(){
        return grayscale;
    }

    /**
     * return the gray scale value in specific coordinate
     * @param  x    stand for col
     * @param  y    stand for row
     * @return      the gray scale value
     */
    public int getGrayscale(int x, int y){
        return this.grayscale.get(y).get(x);
    }

    /**
     * return the list of the gray scale value in specific row
     * @param  y    stand for row
     * @return      the list of the gray scale
     */
    public List<Integer> getGrayscale(int y){
        return this.grayscale.get(y);
    }

    public int getRowSize(){
        return this.grayscale.size();
    }

    public int getColSize(int row){
        return this.grayscale.get(row).size();
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
