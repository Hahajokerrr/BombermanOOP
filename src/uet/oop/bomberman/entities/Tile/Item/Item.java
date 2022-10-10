package uet.oop.bomberman.entities.Tile.Item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.graphics.Sprite;

import java.time.chrono.Era;

public abstract class Item extends Entity {

    public Item(int x, int y, Image img, Brick brick) {
        super(x,y,img);
        this.brick = brick;
    }
    private boolean isConsumed = false;
    protected Brick brick;

    public void setBrick(Brick brick) {
        this.brick = brick;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        brick.render(gc);
    }

    public void update() {
        if(isConsumed) {
            Erase();
        }
    }
}
