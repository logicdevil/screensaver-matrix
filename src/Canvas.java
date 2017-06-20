import javax.swing.*;
import java.awt.*;

/**
 * Created by daymond on 12/28/16.
 */
public class Canvas extends JPanel {


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(Main.IMAGE, 0,0, null);
    }
}
