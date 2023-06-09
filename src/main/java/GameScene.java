import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameScene extends Scene {
    private Rect background, foreground;
    private Snake snake;
    private KL keyListener;
    private Food food;

    public GameScene(KL keyListener) {
        background = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        foreground = new Rect(24, 42, Constants.TILE_WIDTH * 31, Constants.TILE_WIDTH * 22);
        snake = new Snake(4, 48, 65, Constants.TILE_WIDTH, Constants.TILE_WIDTH);
        this.keyListener = keyListener;
        food = new Food(foreground, snake, 12, 12, new Color(49, 87, 39));
        food.spawn();
    }

    @Override
    public void update(double dt) {
        if (keyListener.isKeyPressed(KeyEvent.VK_UP) || keyListener.isKeyPressed(KeyEvent.VK_W)) {
            snake.changeDirection(Directions.UP);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN) || keyListener.isKeyPressed(KeyEvent.VK_S)) {
            snake.changeDirection(Directions.DOWN);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT) || keyListener.isKeyPressed(KeyEvent.VK_D)) {
            snake.changeDirection(Directions.RIGHT);
        } else if (keyListener.isKeyPressed(KeyEvent.VK_LEFT) || keyListener.isKeyPressed(KeyEvent.VK_A)) {
            snake.changeDirection(Directions.LEFT);
        }

        if(!food.isSpawned) food.spawn();

        snake.update(dt);
        food.update(dt);
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(new Color(45, 50, 89));
        graphics2D.fill(new Rectangle2D.Double(background.x, background.y, background.width, background.height));

        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(new Rectangle2D.Double(foreground.x, foreground.y, foreground.width, foreground.height));

        snake.draw(graphics2D);
        food.draw(graphics2D);
    }
}
