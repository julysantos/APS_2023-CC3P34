package Objects;
import javax.swing.*;
import java.awt.*;
public abstract class  MoverReciclavel extends JFrame {
    private int x = 0;
    private int y = 0;


    @Override
    public void paint(Graphics c) {
        super.paint(c);
        c.setColor(Color.yellow);
        c.fillOval(this.getX(), 0 + this.getY(), 50, 50);
    }

    @Override
    public int getX() {
        return x;
    }


    @Override
    public int getY() {
        return y;
    }
}
