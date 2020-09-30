

public class Main {
    public static void main(String[] args) {
        //RBGCharConvertor container = new RBGCharConvertor(16, "SpecialCharacters.txt");
        TextImage i = new TextImage(new RBGCharConvertor(256), "img/example_1.jpg", 2);
        i.toTerminal();
    }
}
