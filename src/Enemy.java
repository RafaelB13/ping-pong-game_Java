import java.awt.*;

public class Enemy {


    double x, y;
    int widht, height;

    Enemy (int x, int y) {
        this.x = x;
        this.y = y;
        this.widht = 40;
        this.height = 5;
    }

    void tick(){
        x += (Game.ball.x - x - 6) * 0.06;

    }

    void render(Graphics graphics){
        graphics.setColor(Color.RED);
        graphics.fillRect( (int) x, (int) y, widht, height );
    }

}
