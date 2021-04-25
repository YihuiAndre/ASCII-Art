package Main;
import java.util.List;

public interface Image {
    public List<List<Integer>> getColor();
    public List<Integer> getColor(int y);
    public int getColor(int x, int y);
    public int getHeight();
    public int getWidth();
}
