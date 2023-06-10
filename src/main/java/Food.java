import java.awt.*;

public class Food {
    private Rect background, rect;
    private Snake snake;
    private int width, height;
    private Color color;
    private int xPadding;
    public boolean isSpawned;
    private int count = -1;
    public static int score;

    public Food(Rect background, Snake snake, int width, int height, Color color) {
        this.background = background;
        this.snake = snake;
        this.width = width;
        this.height = height;
        this.color = color;
        rect = new Rect(0, 0, width, height);
        xPadding = (int)((Constants.TILE_WIDTH - width) * 0.5);
    }

    public void spawn() {
        do {
            double randX = (int) (Math.random() * (int)(background.width / Constants.TILE_WIDTH)) * Constants.TILE_WIDTH + background.x;
            double randY = (int) (Math.random() * (int)(background.height / Constants.TILE_WIDTH)) * Constants.TILE_WIDTH + background.y;
            this.rect.x = randX;
            this.rect.y = randY;
            count++;
            score = count;

            System.out.println("Score = " + score);
            System.out.println("Count = " + count);

        } while (snake.intersectingWithRect(this.rect));
        isSpawned = true;
    }
    public void update(double dt) {
        if(snake.intersectingWithRect(rect)) {
            snake.grow();
            rect.x = -100;
            rect.y = -100;
            isSpawned = false;
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fillRect((int)this.rect.x + xPadding, (int)this.rect.y + xPadding, width, height);
    }
}
