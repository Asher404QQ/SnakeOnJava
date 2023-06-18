import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameOverScene extends Scene {
    private ML mouseListener;
    private BufferedImage title, menu, menuPressed, blurBackground, brokenHeart, score, bestScore, restart, restartPressed;
    private BufferedImage zero, one, two, three, four, five, six, seven, eight, nine;
    private BufferedImage menuCurrentImage, restartCurrentImage;
    private Rect menuRect, restartRect;

    private int intBestScore;
    private char[] scoreNum = String.valueOf(Food.score).toCharArray();
    private char[] charsBestScore;

    public GameOverScene(ML mouseListener) {
        this.mouseListener = mouseListener;

        try {
            BufferedImage background = ImageIO.read(new File("\\Snake\\src\\main\\resources\\background.png"));
            BufferedImage bHeart = ImageIO.read(new File("\\Snake\\src\\main\\resources\\broken-heart-2.png"));
            BufferedImage spriteSheet = ImageIO.read(new File("\\Snake\\src\\main\\resources\\gameOverMenu.png"));
            title = spriteSheet.getSubimage(11, 511, 614, 80);
            score = spriteSheet.getSubimage(15, 389, 184, 40);
            menu = spriteSheet.getSubimage(13, 441, 223, 60);
            menuPressed = spriteSheet.getSubimage(261, 431, 255, 69);
            restart = spriteSheet.getSubimage(16, 317, 343, 60);
            restartPressed = spriteSheet.getSubimage(371, 308, 390, 67);
            zero = spriteSheet.getSubimage(18, 273, 31, 40);
            one = spriteSheet.getSubimage(51, 273, 11, 40);
            two = spriteSheet.getSubimage(64, 273, 31, 40);
            three = spriteSheet.getSubimage(98, 273, 29, 40);
            four = spriteSheet.getSubimage(131, 273, 30, 40);
            five = spriteSheet.getSubimage(164, 273, 31, 40);
            six = spriteSheet.getSubimage(197, 273, 31, 40);
            seven = spriteSheet.getSubimage(231, 273, 30, 40);
            eight = spriteSheet.getSubimage(264, 273, 31, 40);
            nine = spriteSheet.getSubimage(297, 273, 31, 40);
            bestScore = spriteSheet.getSubimage(210, 389, 340, 40);

            brokenHeart = bHeart;
            blurBackground = background;

            String filePath = "\\Snake\\src\\main\\resources\\best-score.txt";
            List<String> list = new ArrayList<>();
            Files.lines(Paths.get(filePath)).forEach(list::add);
            intBestScore = Integer.valueOf(list.get(0)).intValue();

            if(intBestScore < Food.score) {
                intBestScore = Food.score;
                File file = new File(filePath);
                FileWriter fileWriter = new FileWriter(file, false);

                fileWriter.write("" + intBestScore + "");
                fileWriter.close();
            }
            
            charsBestScore = list.get(0).toCharArray();

        } catch (Exception e) {
            e.printStackTrace();
        }

        menuCurrentImage = menu;
        restartCurrentImage = restart;

        menuRect = new Rect(290, 360, 223, 60);
        restartRect = new Rect(232, 260, 343, 60);
    }

    @Override
    public void update(double dbt) {
        if (mouseListener.getX() >= menuRect.x && mouseListener.getX() <= menuRect.x + menuRect.width
                && mouseListener.getY() >= menuRect.y && mouseListener.getY() <= menuRect.y + menuRect.height) {
            menuCurrentImage = menuPressed;
            if (mouseListener.isPressed()) {
                Window.getWindow().changeState(0);
            }
        } else {
            menuCurrentImage = menu;
        }
        if (mouseListener.getX() >= restartRect.x && mouseListener.getX() <= restartRect.x + restartRect.width
                && mouseListener.getY() >= restartRect.y && mouseListener.getY() <= restartRect.y + restartRect.height) {
            restartCurrentImage = restartPressed;
            if (mouseListener.isPressed()) {
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
        graphics.drawImage(brokenHeart, 700, 180, 50, 50, null);
        graphics.drawImage(title, 100, 90, 594, 80, null);
        graphics.drawImage(menuCurrentImage, 290, 360, 223, 60, null);
        graphics.drawImage(restartCurrentImage, 232, 260, 343, 60, null);

        int y = 485;
        int x = 185;
        int xBestScore = 370;
        int scoreHeight = 40;
        graphics.drawImage(score, 20, y, 154, scoreHeight, null);
        graphics.drawImage(bestScore, 20, y + 50, 340, scoreHeight, null);

        for (int i = 0; i < scoreNum.length; i++) {
            if (scoreNum[i] == '0') {
                graphics.drawImage(zero, x, y, 31, scoreHeight, null);
                x += 34;
            }
            if (scoreNum[i] == '1') {
                graphics.drawImage(one, x, y, 11, scoreHeight, null);
                x += 14;
            }
            if (scoreNum[i] == '2') {
                graphics.drawImage(two, x, y, 31, scoreHeight, null);
                x += 34;
            }
            if (scoreNum[i] == '3') {
                graphics.drawImage(three, x, y, 29, scoreHeight, null);
                x += 32;
            }
            if (scoreNum[i] == '4') {
                graphics.drawImage(four, x, y, 30, scoreHeight, null);
                x += 33;
            }
            if (scoreNum[i] == '5') {
                graphics.drawImage(five, x, y, 31, scoreHeight, null);
                x += 34;
            }
            if (scoreNum[i] == '6') {
                graphics.drawImage(six, x, y, 31, scoreHeight, null);
                x += 34;
            }
            if (scoreNum[i] == '7') {
                graphics.drawImage(seven, x, y, 30, scoreHeight, null);
                x += 33;
            }
            if (scoreNum[i] == '8') {
                graphics.drawImage(eight, x, y, 31, scoreHeight, null);
                x += 34;
            }
            if (scoreNum[i] == '9') {
                graphics.drawImage(nine, x, y, 31, scoreHeight, null);
                x += 34;
            }
        }


        for (int i = 0; i < charsBestScore.length; i++) {
            if (charsBestScore[i] == '0') {
                graphics.drawImage(zero, xBestScore, y + 50, 31, scoreHeight, null);
                xBestScore += 34;
            }
            if (charsBestScore[i] == '1') {
                graphics.drawImage(one, xBestScore, y + 50, 11, scoreHeight, null);
                xBestScore += 14;
            }
            if (charsBestScore[i] == '2') {
                graphics.drawImage(two, xBestScore, y + 50, 31, scoreHeight, null);
                xBestScore += 34;
            }
            if (charsBestScore[i] == '3') {
                graphics.drawImage(three, xBestScore, y + 50, 29, scoreHeight, null);
                xBestScore += 32;
            }
            if (charsBestScore[i] == '4') {
                graphics.drawImage(four, xBestScore, y + 50, 30, scoreHeight, null);
                xBestScore += 33;
            }
            if (charsBestScore[i] == '5') {
                graphics.drawImage(five, xBestScore, y + 50, 31, scoreHeight, null);
                xBestScore += 34;
            }
            if (charsBestScore[i] == '6') {
                graphics.drawImage(six, xBestScore, y + 50, 31, scoreHeight, null);
                xBestScore += 34;
            }
            if (charsBestScore[i] == '7') {
                graphics.drawImage(seven, xBestScore, y + 50, 30, scoreHeight, null);
                xBestScore += 33;
            }
            if (charsBestScore[i] == '8') {
                graphics.drawImage(eight, xBestScore, y + 50, 31, scoreHeight, null);
                xBestScore += 34;
            }
            if (charsBestScore[i] == '9') {
                graphics.drawImage(nine, xBestScore, y + 50, 31, scoreHeight, null);
                xBestScore += 34;
            }
        }
    }
}
