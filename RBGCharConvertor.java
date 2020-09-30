//this container class will store all the characters and it create function 
//which if there is a RBG value, it will return an character value correposnding it
//this is the class which take one parameter: number of chracters (size can be large than 256 and need to be in 2**n)

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class RBGCharConvertor {
    private char[] charList;
    private int length;

    // constrcutor, it will initlize the array and read the file and store all characters into array
    public RBGCharConvertor(int numOfChar, String filePath) {
        try {
            this.length = numOfChar;
            this.charList = new char[this.length];
            if(this.length > 256 || !isVaild(this.length)){
                throw new Exception("number of character input is not valid!!");
            }
            readFile(filePath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public RBGCharConvertor(int numOfChar) {
        this(numOfChar, "characters/ChineseCharacters.txt");
    }

    //helper function that read a text file and 
    private void readFile(String filePath) throws Exception, FileNotFoundException, IOException {
        try(FileReader fileReader = new FileReader(filePath)){
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

    //convert RBG value into char value
    //input: the sum of 
    public char RBGToChar(int RBGValue){
        return this.charList[RBGValue/(256/this.length)];
    }


    public char RBGToChar(int RValue, int BValue, int GValue){
        int avgColor = (RValue + BValue + GValue)/3;
        return this.charList[avgColor/(256/this.length)];
    }

}
