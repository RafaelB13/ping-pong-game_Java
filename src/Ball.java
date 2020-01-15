import java.awt.*;
import java.util.Random;

public class Ball {


    double x, y;
    int widht, height;

    public double dx, dy;
    public double speed = 1.7;

    Ball (int x, int y) {
        this.x = x;
        this.y = y;
        this.widht = 4;
        this.height = 4;

        int angle = new Random().nextInt((120 - 45) + 45);

        dx = Math.cos(Math.toRadians(angle));
        dy = Math.sin(Math.toRadians(angle));
    }

    void tick(){

        if (x + (dx * speed) + widht >= Game.WIDTH){
            dx *= -1;
        }else if (x + (dx * speed) < 0){
            dx *= -1;
        }

        if (y >= Game.HEIGHT){
            //Enemy Point
            System.out.println("Enemy Point");
            new Game();
            return;

        }else if (y < 0){
            //Player Point
            System.out.println("Player Point");
            new Game();
            return;
        }

        Rectangle bounds = new Rectangle((int) (x + (dx*speed)), (int) (y + (dy*speed)), widht, height);

        Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.heith);
        Rectangle boundsEnemy = new Rectangle((int) Game.enemy.x, (int) Game.enemy.y,  Game.enemy.widht, Game.enemy.height);

        if (bounds.intersects(boundsPlayer)) {
            int angle = new Random().nextInt((120 - 45) + 45);
            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if(dy > 0)
                dy *= -1;
        }else if (bounds.intersects(boundsEnemy)) {
            int angle = new Random().nextInt((120 - 45) + 45);

            dx = Math.cos(Math.toRadians(angle));
            dy = Math.sin(Math.toRadians(angle));
            if(dy < 0)
                dy *= -1;
        }
        x += dx*speed;
        y += dy*speed;
    }

    void render(Graphics graphics){
        graphics.setColor(Color.YELLOW);
        graphics.fillRect( (int) x, (int) y, widht, height );
    }

}
