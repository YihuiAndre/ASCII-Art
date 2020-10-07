import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, NullPointerException, IOException, Exception {
        //ColorConvertor container = new ColorConvertor(16, "SpecialCharacters.txt");
        //TextImage a = new TextImage(new ColorConvertor(256, "characters/ASCII.txt"), "https://thebiem.com/wp-content/uploads/2018/12/New-Movie-Weather-With-You-feature-.jpg", true, 1);
        //TextImage a = new TextImage(new ColorConvertor(256, "characters/ASCII.txt"), "https://static.wikia.nocookie.net/darling-in-the-franxx/images/2/27/Kv04.jpg/revision/latest?cb=20171209072941", true, 2);
        //TextImage a = new TextImage(new ColorConvertor(256, "characters/ASCII.txt"), "img/image.jpg", false, 1);
        //a.toTextFile("img.txt");
        //a.RGBAToTextFile("RBG.txt");
        //a.toImgFile("output.png");
        //a.print();
        
        ColorConvertor translator = new ColorConvertor(256, "characters/ASCII.txt");
        int index = 0;
        File dir = new File("gif/anime/");
        File[] directoryListing = dir.listFiles();
        Arrays.sort(directoryListing);
        TextImage[] listOfImage = new TextImage[directoryListing.length];
        if (directoryListing != null) {
            for (File child : directoryListing) {
                // Do something with child
                System.out.println(child.getPath());
                listOfImage[index] = new TextImage(translator, child.getPath(), false);
                int num = Integer.parseInt(child.getName().replaceAll("[\\D]", ""));
                listOfImage[index].toImgFile("output/anime/" + num + ".png");
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
