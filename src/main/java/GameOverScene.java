import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameOverScene extends Scene{
    private ML mouseListener;
    private BufferedImage title, menu, menuPressed, blurBackground, brokenHeart, score, restart, restartPressed;
    private BufferedImage menuCurrentImage, restartCurrentImage;
    private Rect menuRect, restartRect;

    public GameOverScene(ML mouseListener) {
        this.mouseListener = mouseListener;

        try {
            BufferedImage background = ImageIO.read(new File("\\Snake\\src\\main\\resources\\background.png"));
            BufferedImage bHeart = ImageIO.read(new File("\\Snake\\src\\main\\resources\\broken-heart-2.png"));
            BufferedImage spriteSheet = ImageIO.read(new File("\\Snake\\src\\main\\resources\\gameOverMenu1.png"));
            title = spriteSheet.getSubimage(11, 511, 614, 80);
            score = spriteSheet.getSubimage(15, 389, 154, 40);
            menu = spriteSheet.getSubimage(13, 441, 223, 60);
            menuPressed = spriteSheet.getSubimage(261, 431, 255, 69);
            restart = spriteSheet.getSubimage(16, 317, 343, 60);
            restartPressed = spriteSheet.getSubimage(371, 308, 390, 67);

            brokenHeart = bHeart;
            blurBackground = background;
        } catch (Exception e) {
            e.printStackTrace();
        }

        menuCurrentImage = menu;
        restartCurrentImage = restart;

        menuRect = new Rect(290, 340, 223, 60);
        restartRect = new Rect(232, 260, 343, 60);
    }
    @Override
    public void update(double dbt) {
        if (mouseListener.getX() >= menuRect.x && mouseListener.getX() <= menuRect.x + menuRect.width
                && mouseListener.getY() >= menuRect.y && mouseListener.getY() <= menuRect.y + menuRect.height) {
            menuCurrentImage = menuPressed;
            if(mouseListener.isPressed()){
                Window.getWindow().changeState(0);
            }
        } else {
            menuCurrentImage = menu;
        }
        if (mouseListener.getX() >= restartRect.x && mouseListener.getX() <= restartRect.x + restartRect.width
                && mouseListener.getY() >= restartRect.y && mouseListener.getY() <= restartRect.y + restartRect.height) {
            restartCurrentImage = restartPressed;
            if(mouseListener.isPressed()){
                Window.getWindow().changeState(1);
            }
        } else {
            restartCurrentImage = restart;
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        graphics.drawImage(blurBackground, 0, 0, null);
        graphics.drawImage(brokenHeart, 700, 180, 50, 50,  null);
        graphics.drawImage(title, 100, 90, 594, 80, null);
        graphics.drawImage(menuCurrentImage, 290, 360, 223, 60, null);
        graphics.drawImage(restartCurrentImage, 232, 260, 343, 60, null);
//        graphics.drawImage(score, 240, 250, 154, 40, null);
    }
}
