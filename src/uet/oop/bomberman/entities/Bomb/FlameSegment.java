package uet.oop.bomberman.entities.Bomb;

import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.graphics.Sprite;

public class FlameSegment extends AnimatedEntity {

    protected boolean last;

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public FlameSegment(int x, int y, boolean last) {
        this.x = x;
        this.y = y;
        this.last = last;
    }

    public void update() {

    }

}
