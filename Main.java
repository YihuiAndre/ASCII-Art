

public class Main {
    public static void main(String[] args) {
        //ColorConvertor container = new ColorConvertor(16, "SpecialCharacters.txt");
        TextImage i = new TextImage(new ColorConvertor(256, "characters/SpecialCharacters.txt"), "https://thebiem.com/wp-content/uploads/2018/12/New-Movie-Weather-With-You-feature-.jpg", true, 1);
        i.imgToTextFile("img.txt");
        i.RGBAToTextFile("RBG.txt");
        i.imgToImgFile("output.png");
    }
}
