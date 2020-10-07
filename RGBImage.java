import java.util.ArrayList;
import java.util.List;

//an container class that store the image RGB value in each pixel
public class RGBImage {
    //store the 8 bit color value of the image
    private final List<List<Integer>> colorVal;
    //store the ratio of the color 
    private final double[] RGBRatio = {0.0,0.0,0.0};
    
    public RGBImage(){
        this.colorVal = new ArrayList<>();
    }

    public List<List<Integer>> getColorVal(){
        return colorVal;
    }

    //obtain an RGB value in the specific coordinate
    public int getRGB(int x, int y){
        return this.colorVal.get(y).get(x);
    }

    public List<Integer> getRGB(int y){
        return this.colorVal.get(y);
    }

    public int getRowSize(){
        return this.colorVal.size();
    }

    public int getColSize(int row){
        return this.colorVal.get(row).size();
    }

    //append an RGB value into the vector
    public void addRGB(int y, int RGB){
        //System.out.println(y);
        if(y == this.colorVal.size()) this.colorVal.add(new ArrayList<>());
        this.colorVal.get(y).add(RGB);
    }

    public double[] getRGBRatio(){
        return this.RGBRatio;
    }

    public void setRGBRatio(double rRatio,double gRatio, double bRatio){
        this.RGBRatio[0] = rRatio;
        this.RGBRatio[1] = gRatio;
        this.RGBRatio[2] = bRatio;
    }
}
