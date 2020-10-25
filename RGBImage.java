import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//an container class that store the image RGB value in each pixel
public class RGBImage {
    //store the 8 bit color value of the image
    private final List<List<Integer>> colorVal;
    //store the ratio of the color 
    private final Map<Character, Integer> RGBRatio = new LinkedHashMap<>();
    
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

    public void setRGBRatio(double rRatio,double gRatio, double bRatio){
        int redBit = (int) Math.round(rRatio * 8);
        int greenBit = (int) Math.round(gRatio * 8);
        int blueBit = (int) Math.round(bRatio * 8);
        if(redBit + greenBit + blueBit != 8){
            if(rRatio > gRatio && gRatio > bRatio){
                blueBit += 8-(redBit + greenBit + blueBit);
            }
            else if(gRatio > bRatio && bRatio > rRatio){
                redBit += 8-(redBit + greenBit + blueBit);
            }
            else{
                greenBit += 8-(redBit + greenBit + blueBit);
            }
        }
        if(redBit + greenBit + blueBit != 8){
            System.err.println("more than eight!!!");
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put('r', redBit);
        map.put('g', greenBit);
        map.put('b', blueBit);
        List<Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());
        Collections.reverse(list);
        for (Entry<Character, Integer> entry : list) {
            this.RGBRatio.put(entry.getKey(), entry.getValue());
        }
    }
}
