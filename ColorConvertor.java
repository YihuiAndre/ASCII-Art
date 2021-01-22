import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import helper.Helper;


//A class use to communicate between the grayscale value and character
public class ColorConvertor {
    private final char[] charList;
    private final int length;

    /**
     * constructor, receive the source of characters file and use those characters to translate the 
     * grayscale value into character
     * @param  numOfChar  an integer value that represent the number of unique characters to represent the image
     * @param  filePath an string that receive the source of characters file
     * @throws FileNotFoundException 
     * @throws IOException
     * @throws Exception
     */
    public ColorConvertor(int numOfChar, String filePath) throws FileNotFoundException, IOException, Exception{
        this.length = numOfChar;
        this.charList = new char[this.length];
        try(FileReader fileReader = new FileReader(filePath)){
            if(this.length > 256 || !isValid(this.length)){
                throw new Exception("number of character input is not valid!!");
            }
            int fileLength = Helper.getFileLength(filePath);
            if(fileLength > 256) fileLength = 256;
            if(fileLength < numOfChar){
                throw new Exception("file length is less than characters length -> Length of file: " + fileLength);
            }
            //generate a list of random number from 0 to 256 with length of L
            Set<Integer> randomNumber = Helper.randomNumberList(0, fileLength, numOfChar);
            int data, index = 0;
            for(int i = 0; i < fileLength; i++){
                data = fileReader.read();
                //10 represent as \n
                while(data == 10 && data != -1){
                    data = fileReader.read();
                }
                //when read at the end of the file
                if(data == -1){
                    throw new Exception("file length is less than characters length -> Length of file: " + fileLength);
                }
                //get half of the number of character from at the beginning and half at the end
                //if(i < numOfChar/2 || i >= (fileLength-numOfChar/2)) this.charList[index++] = (char) data;
                if(randomNumber.contains(i)) this.charList[index++] = (char) data;
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

    /**
     * validate if the number of character is valid in 2, 4, 8, 16, .... until 256 as maximum
     * @param  number   An integer number that represent as the number of unique characters
     * @return          the boolean result of validation
     */
    private boolean isValid(int number){
        if(number > 256 || number%2 != 0) return false;
        if(number == 2) return true;
        return isValid(number/2);
    }

    /**
     * translate the grayscale into a character
     * The value of gray scale is from 0 to 256:
     * 1. 0 is darker since rbg of 0, 0, 0 is black
     * 2. 256 is lighter since rbg of 256, 256, 256 is white
     * @param  g    8 bit value of grayscale
     * @return      the character corresponding with the grayscale value
     */
    public char colorToChar(int g){
        int bit = this.length-1;
        return this.charList[bit-g/(256/this.length)];
    }

}
