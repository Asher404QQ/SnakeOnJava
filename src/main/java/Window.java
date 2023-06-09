import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame implements Runnable {
    private static Window window = null;
    private boolean isRunning;
    public int currentState;
    public Scene currentScene;
    private KL keyListener = new KL();
    private ML mouseListener = new ML();
    private Window (int width, int height, String title) {
        setSize(width, height);
        setLocationRelativeTo(null);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        isRunning = true;
        changeState(0);
    }

    public static Window getWindow(){
        if(Window.window == null) {
            Window.window = new Window(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.SCREEN_TITLE);
        }
        return Window.window;
    }
    public void close() {
        isRunning = false;
    }
    public void changeState(int newState) {
        currentState = newState;
        switch (currentState) {
            case 0 -> currentScene = new MenuScene(mouseListener);
            case 1 -> currentScene = new GameScene(keyListener);
            case 3 -> currentScene = new GameOverScene(mouseListener);
            default -> {
                System.out.println("Unknown scene");
                currentScene = null;
            }
        }
    }

    public void update(double deltaTime) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        getGraphics().drawImage(dbImage, 0, 0, this);
        currentScene.update(deltaTime);
    }

    public void draw(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        currentScene.draw(graphics);
    }

    @Override
    public void run() {
        double lastFrameTime = 0;
        try {
            while(isRunning) {
                double time = Time.getTime();
                double deltaTime = time - lastFrameTime;
                lastFrameTime = time;

                update(deltaTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.dispose();
    }
}
