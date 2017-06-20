import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

/**
 * Created by daymond on 12/28/16.
 */
public class Main {

    static  final BufferedImage SCREEN = createScreen();
    static  BufferedImage IMAGE;
    static int HEIGHT;
    static int WIDTH;
    static int FONT_SIZE = 30;
    static int FONT_WIDTH = (int)(0.5667*FONT_SIZE);
    static int FONT_HEIGHT = (int)(0.6667*FONT_SIZE);
    static int NUM_OF_LETT_HORIZONTAL = WIDTH/(FONT_WIDTH+FONT_WIDTH/2);
    static int NUM_OF_LETT_VERTICAL = HEIGHT/(FONT_HEIGHT+FONT_HEIGHT/3);
    static int INDENT_HORIZONTAL = (WIDTH%(FONT_WIDTH+FONT_WIDTH/2)+FONT_WIDTH/2)/2;
    static int INDENT_VERTICAL = (HEIGHT%(FONT_HEIGHT+FONT_HEIGHT/3)+FONT_HEIGHT/3)/2;
    static boolean SYNHRO = false;
    static boolean EXIT = false;
    static int TACT = 50;
    JFrame frame;
    Canvas canvas;
    MyImage image;




     void start() {
        frame = new JFrame();
        frame.setTitle("Matrix");
        frame.setResizable(false);

         /**                   Trying to set fullscreen                         */
        if(!frame.isDisplayable()) {
            frame.dispose();
            frame.setUndecorated(true);
        }

        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        try {
            graphicsDevice.setFullScreenWindow(frame);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        /************************************************************************/

         canvas = new Canvas();
         canvas.setBackground(Color.white);
         frame.getContentPane().add(BorderLayout.CENTER, canvas);
         image = new MyImage(SCREEN);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode()==27)
                EXIT = true;
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });

         canvas.setDoubleBuffered(true);
         frame.setVisible(true);

         Thread thread1 = new Thread() {
             @Override
             public void run() {
                 super.run();
                 while(!EXIT) {
                         Main.SYNHRO = true;
                         IMAGE = image.updateImage();
                         Main.SYNHRO = false;
                     try {
                         Thread.sleep(TACT);
                    }catch (Exception e) {e.printStackTrace();}
                 }
             }
         };
         Thread thread2 = new Thread() {
             @Override
             public void run() {
                 super.run();
                 while(!EXIT) {
                     while(Main.SYNHRO) {Thread.yield();}
                     canvas.repaint();
                     try {
                         Thread.sleep(TACT/2);
                     }catch (Exception e) {e.printStackTrace();}
                 }

             }
         };
         thread1.start();
         thread2.start();
    }
















    static BufferedImage createScreen() {
        try {
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            HEIGHT = rectangle.height;
            WIDTH = rectangle.width;
            BufferedImage img = new Robot().createScreenCapture(rectangle);
BufferedImage imgAlpha = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
            RescaleOp changeAlpha = new RescaleOp(0.4f, 30.0f, null);
            changeAlpha.filter(img, imgAlpha);
            return imgAlpha;
        } catch (SecurityException e) {}
        catch (AWTException e){}
        return null;


    }

}
