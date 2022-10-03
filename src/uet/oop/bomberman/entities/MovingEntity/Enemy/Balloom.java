package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Bomb.FlameSegment;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.entities.MovingEntity.State;
import uet.oop.bomberman.entities.Tile.Brick;
import uet.oop.bomberman.entities.Tile.Wall;
import uet.oop.bomberman.graphics.Sprite;

import javax.xml.parsers.SAXParser;

public class Balloom extends Enemy {
    public Balloom(int x, int y, Image img) {
        super(x, y, img);
        setState(State.LEFT);
    }

    public void ContactStillObject() {
        for (int i = 0; i < stillObjects.size(); i++) {
            if ((stillObjects.get(i) instanceof Wall || stillObjects.get(i) instanceof Brick) && this.collision(stillObjects.get(i))) {
                RandomDirection();
            }
        }
    }

    public void ContactEntities() {
        for(int i=0; i<entities.size(); i++) {
            if (entities.get(i) instanceof Bomber && this.collision(entities.get(i))) {
                ((Bomber) entities.get(i)).setAlive(false);
            }
            if (entities.get(i) instanceof Bomb && this.collision(entities.get(i))) {
                RandomDirection();
            }
            if (entities.get(i) instanceof FlameSegment && this.collision(entities.get(i))) {
                Kill();
            }
        }
    }

    public void update() {
        if (alive) {
            ContactStillObject();
            ContactEntities();
            animate();
            switch (state) {
                case UP:
                    setY(getY() - speed);
                    setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 50).getFxImage());
                    break;
                case DOWN:
                    setY(getY() + speed);
                    setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 50).getFxImage());
                    break;
                case LEFT:
                    setX(getX() - speed);
                    setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 50).getFxImage());
                    break;
                case RIGHT:
                    setX(getX() + speed);
                    setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 50).getFxImage());
                    break;
            }
        } else {
            setImg(Sprite.balloom_dead.getFxImage());
            AfterKill++;
            if(AfterKill >= 150) {
                Erase();
            }
        }
    }
}
