import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ML extends MouseAdapter implements MouseListener {
    private boolean isPressed = false;
    private double x = 0, y = 0;

    @Override
    public void mousePressed(MouseEvent e) {
        isPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isPressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    public boolean isPressed() {
        return isPressed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
