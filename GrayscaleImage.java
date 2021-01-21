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

    //obtain an grayscale value in the specific coordinate
    public int getGrayscale(int x, int y){
        return this.grayscale.get(y).get(x);
    }

    public List<Integer> getGrayscale(int y){
        return this.grayscale.get(y);
    }

    public int getRowSize(){
        return this.grayscale.size();
    }

    public int getColSize(int row){
        return this.grayscale.get(row).size();
    }

    //append an grayscale value into the vector
    public void addGrayscale(int y, int grayscaleVal){
        if(y == this.grayscale.size()) this.grayscale.add(new ArrayList<>());
        this.grayscale.get(y).add(grayscaleVal);
    }

}
