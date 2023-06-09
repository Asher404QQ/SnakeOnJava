import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuScene extends Scene{
    private KL keyListener;
    private ML mouseListener;
    private BufferedImage title, play, playPressed, exit, exitPressed, hackedTitle;
    private Rect playRect, exitRect, titleRect;

    private BufferedImage playCurrentImage, exitCurrentImage, titleCurrentImage;

    public MenuScene(KL keyListener, ML mouseListener) {
        this.keyListener = keyListener;
        this.mouseListener = mouseListener;

        try {
            BufferedImage spriteSheet = ImageIO.read(new File("D:\\Snake\\src\\main\\resources\\menuSprite.png"));
            title = spriteSheet.getSubimage(9, 470, 487, 119);
            hackedTitle = spriteSheet.getSubimage(9, 155,404, 121);
            play = spriteSheet.getSubimage(14, 373, 239, 79);
            playPressed = spriteSheet.getSubimage(268, 372, 239, 79);
            exit = spriteSheet.getSubimage(13, 286, 201, 80);
            exitPressed = spriteSheet.getSubimage(231, 287, 201, 80);
        } catch (Exception e) {
            e.printStackTrace();
        }

        playCurrentImage = play;
        exitCurrentImage = exit;
        titleCurrentImage = title;

        titleRect = new Rect(150, 90, 487, 119);
        playRect = new Rect(280, 279, 239, 79);
        exitRect = new Rect(300, 408, 201, 80);
    }
    @Override
    public void update(double dbt) {
        // Play
        if (mouseListener.getX() >= playRect.x && mouseListener.getX() <= playRect.x + playRect.width
                && mouseListener.getY() >= playRect.y && mouseListener.getY() <= playRect.y + playRect.height) {
            playCurrentImage = playPressed;
            if(mouseListener.isPressed()) {
                Window.getWindow().changeState(1);
            }
        } else {
            playCurrentImage = play;
        }
        //Exit
        if (mouseListener.getX() >= exitRect.x && mouseListener.getX() <= exitRect.x + exitRect.width
                && mouseListener.getY() >= exitRect.y && mouseListener.getY() <= exitRect.y + exitRect.height) {
            exitCurrentImage = exitPressed;
            if(mouseListener.isPressed()){
                Window.getWindow().close();
            }
        } else {
            exitCurrentImage = exit;
        }
        //Title
        if (mouseListener.getX() >= titleRect.x && mouseListener.getX() <= titleRect.x + titleRect.width
                && mouseListener.getY() >= titleRect.y && mouseListener.getY() <= titleRect.y + titleRect.height) {
            titleCurrentImage = hackedTitle;
        } else {
            titleCurrentImage = title;
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        graphics.drawImage(titleCurrentImage, 150, 90, 487, 119, null);
        graphics.drawImage(playCurrentImage, 280, 279, 239, 79, null);
        graphics.drawImage(exitCurrentImage, 300, 408, 201, 80, null);
    }
}
