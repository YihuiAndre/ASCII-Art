//this container class will store all the characters and it create function 
//which if there is a RBG value, it will return an character value correposnding it
//this is the class which take one parameter: number of chracters (size can be large than 256 and need to be in 2**n)

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;


public class ColorConvertor {
    private char[] charList;
    private int length;

    // constrcutor, it will initlize the array and read the file and store all characters into array
    public ColorConvertor(int numOfChar, String filePath) {
        this.length = numOfChar;
        this.charList = new char[this.length];
        try(FileReader fileReader = new FileReader(filePath)){
            if(this.length > 256 || !isVaild(this.length)){
                throw new Exception("number of character input is not valid!!");
            }
            int data;
            for(int i = 0; i < this.length; i++){
                data = fileReader.read();
                //10 represent as \n
                if(data == 10){
                    data = fileReader.read();
                }
                //when read at the end of the file
                else if(data == -1){
                    throw new Exception("file length is not match with characters length");
                }
                this.charList[i] = (char) data;
            }
        } 
        catch (Exception err) {
            err.printStackTrace();
        }
    }

    public ColorConvertor(int numOfChar) {
        this(numOfChar, "characters/ChineseCharacters.txt");
    }

    //validate if the number of character is vaild in 2, 4, 8, 16, ....
    private boolean isVaild(int number){
        switch (number){
            case 2:
            case 4:
            case 8:
            case 16:
            case 32:
            case 64:
            case 128:
            case 256:
                return true;
            default:
                return false;
        }
    }

    //input: 8 bit value of color
    //output: the character corresponding it
    public char RGBAToChar(int RGBAValue){
        return this.charList[RGBAValue/(256/this.length)];
    }

    public char RGBAToChar(int red, int green, int blue, int alpha){
        int eightBitColor = fromColor(new Color(red, green, blue, alpha));
        return this.charList[eightBitColor/(256/this.length)];
    }

    public char RGBAToChar(Color c){
        int eightBitColor = fromColor(c);
        return this.charList[eightBitColor/(256/this.length)];
    }

    //convert RGBA into 8 bit color format
    public static int fromColor(Color c){
        return((c.getRed() >> 6) << 6) + ((c.getGreen() >> 6) << 4)
            + ((c.getBlue() >> 6) << 2) + (c.getAlpha() >> 6);
    }
    public static int fromColor(int red, int green, int blue, int alpha){
        return((red >> 6) << 6) + ((green >> 6) << 4)
            + ((blue >> 6) << 2) + (alpha >> 6);
    }
}
