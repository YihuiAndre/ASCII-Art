import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


//Convertor that communicate between the RBG value and character
public class ColorConvertor {
    private final char[] charList;
    private final int length;

    // constructor, it will initialize the array and read the file and store the number of characters from input into array
    public ColorConvertor(int numOfChar, String filePath) throws FileNotFoundException, IOException, Exception{
        this.length = numOfChar;
        this.charList = new char[this.length];
        try(FileReader fileReader = new FileReader(filePath)){
            if(this.length > 256 || !isValid(this.length)){
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
        catch (FileNotFoundException err){
            throw err;
        }
        catch (IOException err){
            throw err;
        }
        catch (Exception err) {
            throw err;
        }
    }

    public ColorConvertor(int numOfChar) throws FileNotFoundException, IOException, Exception{
        this(numOfChar, "characters/ASCII.txt");
    }

    //validate if the number of character is valid in 2, 4, 8, 16, ....
    private boolean isValid(int number){
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
    public char colorToChar(int colorValue){
        return this.charList[colorValue/(256/this.length)];
    }

    //convert RGB into 8 bit color format
    //This could be improve by Median cut algorithm
    public static int fromColor(int red, int green, int blue, Map<Character, Integer> colorRatio){
        return((red >> (8-colorRatio.get('r'))) << (8-colorRatio.get('r'))) + ((green >> (8-colorRatio.get('g'))) << (8-colorRatio.get('r')-colorRatio.get('g')))
            + (blue >> (8-colorRatio.get('b')));
    }
}
