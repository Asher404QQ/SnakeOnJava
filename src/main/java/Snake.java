import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Snake {
    private Rect[] body = new Rect[100];
    private double bodyWidth, bodyHeight;
    private int size, tail = 0, head = 0;

    private double ogWaitBetweenUpdates = 0.1;
    private double waitTimeLeft = ogWaitBetweenUpdates;

    private Directions direction = Directions.RIGHT;

    public Snake(int size, double startX, double startY, double bodyWidth, double bodyHeight) {
        this.size = size;
        this.bodyWidth = bodyWidth;
        this.bodyHeight = bodyHeight;
        for (int i = 0; i <= size; i++) {
            Rect bodyPiece = new Rect( startX + i * bodyWidth, startY, bodyWidth, bodyHeight);
            body[i] = bodyPiece;
            head++;
        }
        head--;
    }

    public void grow() {
        System.out.println("Growing");
    }

    public boolean intersectingWithSelf() {
        Rect headR = body[head];
        return intersectingWithRect(headR);
    }

    public boolean intersectingWithRect(Rect rect) {
        for (int i = tail; i != head; i = (i + 1) % body.length) {
            if (intersecting(rect, body[i])) return true;
        }
        return false;
    }
    public boolean intersecting(Rect r1, Rect r2) {
        return (r1.x >= r2.x && r1.x + r1.width <= r2.x + r2.width && r1.y >= r2.y && r1.y + r1.height <= r2.y + r2.height);
    }

    public void changeDirection(Directions newDirection) {
        if(direction == Directions.RIGHT && newDirection != Directions.LEFT) {
            direction = newDirection;
        } else if (direction == Directions.LEFT && newDirection != Directions.RIGHT) {
            direction = newDirection;
        } else if (direction == Directions.UP && newDirection != Directions.DOWN) {
            direction = newDirection;
        } else if (direction == Directions.DOWN && newDirection != Directions.UP) {
            direction = newDirection;
        }
    }

    public void draw(Graphics2D graphics2D){
        for (int i = tail; i != head; i = (i + 1) % body.length) {
            Rect piece = body[i];
            double subWidth = (piece.width - 6) / 2;
            double subHeight = (piece.height - 6) / 2;

            graphics2D.setColor(Color.WHITE);
            graphics2D.fill(new Rectangle2D.Double(piece.x + 2, piece.y + 2, subWidth, subHeight));
            graphics2D.fill(new Rectangle2D.Double(piece.x + 4 + subWidth, piece.y + 2, subWidth, subHeight));
            graphics2D.fill(new Rectangle2D.Double(piece.x + 2, piece.y + 4 + subHeight, subWidth, subHeight));
            graphics2D.fill(new Rectangle2D.Double(piece.x + 4 + subWidth, piece.y + 4 + subHeight, subWidth, subHeight));
        }
    }

    public void update(double dt) {
        if(waitTimeLeft > 0) {
            waitTimeLeft -= dt;
            return;
        }

        if (intersectingWithSelf()) {
            Window.getWindow().changeState(0);
        }

        waitTimeLeft = ogWaitBetweenUpdates;
        double newX = 0;
        double newY = 0;

        if(direction == Directions.RIGHT) {
            newX = body[head].x + bodyWidth;
            newY = body[head].y;
        } else if (direction == Directions.LEFT) {
            newX = body[head].x - bodyWidth;
            newY = body[head].y;
        } else if (direction == Directions.UP) {
            newX = body[head].x;
            newY = body[head].y - bodyHeight;
        } else if (direction == Directions.DOWN) {
            newX = body[head].x;
            newY = body[head].y + bodyHeight;
        }

        body[(head + 1) % body.length] = body[tail];
        body[tail] = null;
        head = (head + 1) % body.length;
        tail = (tail + 1) % body.length;

        body[head].x = newX;
        body[head].y = newY;
    }
}
