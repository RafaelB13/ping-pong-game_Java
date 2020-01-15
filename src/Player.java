import java.awt.*;

public class Player {

    public boolean right, left;

    public int x, y;

    public int width, heith;


    Player(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 40;
        this.heith = 5;
    }

    public void tick(){

        if (right){
            x+=2;
        }else if (left){
            x-=2;
        }

        if (x + width > Game.WIDTH)
            x = Game.WIDTH - width;
        else if(x < 0)
            x = 0;

    }

    public void render(Graphics graphics){
        graphics.setColor(Color.BLUE);
        graphics.fillRect(x, y, width, heith);

    }
}
