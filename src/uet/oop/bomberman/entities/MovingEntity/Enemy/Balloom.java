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
    public Balloom(int x, int y, Image img, Bomber bomber) {
        super(x, y, img, bomber);
        setState(State.LEFT);
        Score = 100;
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
            AfterKill();
        }
    }
}
