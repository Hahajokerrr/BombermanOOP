package uet.oop.bomberman.entities.Tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Portal extends Entity {
    private Brick brick;

    public Portal(int x, int y, Image img, Brick brick) {
        super(x, y, img);
        this.brick = brick;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        brick.render(gc);
    }

    public void update() {

    }
}
