import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by daymond on 12/28/16.
 */
public class MyImage {

    BufferedImage image;
    ArrayList<String> array;
    public MyImage(BufferedImage image) {
        this.image = image;
        this.array = new ArrayList<>();
        newArray();
    }

    public BufferedImage updateImage() {
        BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D)img.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, Main.FONT_SIZE));
        moveArray();
        for(int j = 0; j < Main.NUM_OF_LETT_VERTICAL; j++)
            for(int i = 0; i < Main.NUM_OF_LETT_HORIZONTAL; i++) {
                g.setColor(new Color((int)(20 + Math.random()*(101-20)), (int)(150 + Math.random()*(256-150)), (int)(20 + Math.random()*(101-20)), (int)(100 + Math.random()*(201-100))));
                g.drawString(array.get(i + Main.NUM_OF_LETT_VERTICAL * j), Main.INDENT_HORIZONTAL + (Main.FONT_WIDTH / 2 + Main.FONT_WIDTH) * i, Main.INDENT_VERTICAL + Main.FONT_HEIGHT + (Main.FONT_HEIGHT / 3 + Main.FONT_HEIGHT) * j);
        }
        g.dispose();
        return img;
    }
    public  void newArray() {
        for(int i = 0; i < Main.NUM_OF_LETT_VERTICAL*Main.NUM_OF_LETT_HORIZONTAL; i++) {
            char[] chr = {(char)((int)(97 + Math.random()*(123-97)))};
            array.add(new String(chr));
        }
    }
    public void moveArray(){
        for(int i = 0; i < Main.NUM_OF_LETT_VERTICAL; i++) {
            char[] chr = {(char)((int)(97 + Math.random()*(123-97)))};
            array.add(0, new String(chr));
            array.remove(array.size()-1);
        }
    }
}
