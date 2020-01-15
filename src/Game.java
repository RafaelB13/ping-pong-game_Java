import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {

    protected static int WIDTH = 160;
    protected static int HEIGHT = 120;
    protected static int SCALE = 3;

    private BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public static Player player;
    public static Enemy enemy;
    public static Ball ball;

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        this.addKeyListener(this);
        player = new Player(100, HEIGHT-5);
        enemy = new Enemy(100, 0);
        ball = new Ball(100, HEIGHT/2 - 1);
    }

    public static void main(String[] args) {

        Game game = new Game();

        JFrame frame = new JFrame();

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Ping Pong Game - Github.com/RafaelB13");
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(game).start();

    }


    public void tick(){
        player.tick();
        enemy.tick();
        ball.tick();
    }

    public void render (){
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = layer.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0, WIDTH, HEIGHT);
        player.render(graphics);
        enemy.render(graphics);
        ball.render(graphics);

        graphics = bufferStrategy.getDrawGraphics();
        graphics.drawImage(layer, 0,0, WIDTH*SCALE, HEIGHT*SCALE, null);

        bufferStrategy.show();
    }

    @Override
    public void run() {

        while (true){

            requestFocus();
            tick();
            render();

            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = true;
        }else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
            player.left = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = false;
        }else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
            player.left = false;
        }
    }
}
