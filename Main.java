

public class Main {
    public static void main(String[] args) {
        //RBGCharConvertor container = new RBGCharConvertor(16, "SpecialCharacters.txt");
        TextImage i = new TextImage(new RBGCharConvertor(2, "characters/Alphabet.txt"), "https://thebiem.com/wp-content/uploads/2018/12/New-Movie-Weather-With-You-feature-.jpg", true, 1);
        i.imgToTextFile("img.txt");;
        i.imgToImgFile("output.png");
    }
}
