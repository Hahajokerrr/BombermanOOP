package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class AnimatedEntity extends Entity{
    protected int animate = 0;
    protected final int MAX_ANIMATE = 7500;

    public AnimatedEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public AnimatedEntity() {

    }

    public void setAnimate(int ani) {
        animate = ani;
    }
    protected void animate() {
        if(animate < MAX_ANIMATE) animate++; else animate = 0;
    }

    public abstract void update();
}
