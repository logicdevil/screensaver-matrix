/**
 * Created by daymond on 12/28/16.
 */
public class Matrix {
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
        while(!Main.EXIT)
            Thread.yield();

        System.exit(0);
    }
}
