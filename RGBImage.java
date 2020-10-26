import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//an container class that store the image RGB value in each pixel
public class RGBImage {
    //store the 8 bit color value of the image
    private final List<List<Integer>> colorVal;
    //store the ratio of the color 
    private final Map<Character, Integer> RGBRatio = new HashMap<>();
    
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

    public Map<Character, Integer> getRGBRatio(){
        return this.RGBRatio;
    }

    public void setRGBRatio(int averageRed,int averageGreen, int averageBlue){
        double sum = averageRed + averageGreen + averageBlue;
        double rPercent = averageRed/sum, gPercent = averageGreen/sum, bPercent = averageBlue/sum;
        int rRatio, gRatio, bRatio;
        if(rPercent > gPercent && rPercent > bPercent){
            rRatio = 3;
            gRatio = 3;
            bRatio = 2;
        }
        else if(bPercent > rPercent && bPercent > gPercent){
            rRatio = 3;
            gRatio = 2;
            bRatio = 3;
        }
        else{
            rRatio = 2;
            gRatio = 3;
            bRatio = 3;
        }
        //System.out.println(rRatio + " " + gRatio + " " + bRatio);
        this.RGBRatio.put('r', rRatio);
        this.RGBRatio.put('g', gRatio);
        this.RGBRatio.put('b', bRatio);
    }
}
