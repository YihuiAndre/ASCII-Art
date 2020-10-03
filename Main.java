import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, NullPointerException, IOException, Exception {
        //ColorConvertor container = new ColorConvertor(16, "SpecialCharacters.txt");
        TextImage a = new TextImage(new ColorConvertor(64, "characters/ASCII.txt"), "https://thebiem.com/wp-content/uploads/2018/12/New-Movie-Weather-With-You-feature-.jpg", true, 1);
        //a.toTextFile("img.txt");
        //a.RGBAToTextFile("RBG.txt");
        a.toImgFile("output.png");
        //a.print();

        TextImage[] listOfImage = new TextImage[10];
        ColorConvertor translator = new ColorConvertor(32, "characters/ASCII.txt");
        int index = 0;
        File dir = new File("gif/");
        File[] directoryListing = dir.listFiles();
        Arrays.sort(directoryListing);
        if (directoryListing != null) {
            for (File child : directoryListing) {
                // Do something with child
                System.out.println(child.getPath());
                listOfImage[index] = new TextImage(translator, child.getPath(), false);
                listOfImage[index].toImgFile("output/" + index + ".png");
                index++;
            }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }
        
    }
}
