package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.entities.MovingEntity.State;
import uet.oop.bomberman.graphics.Sprite;

public class Doll extends Enemy{
    long changeSpeed = -1;

    public Doll(int x, int y, Image img, Bomber bomber) {
        super(x, y, img, bomber);
        Score = 200;
        setState(State.LEFT);
    }

    public void RandomSpeed() {
        if(changeSpeed > 0) {
            if(System.currentTimeMillis() - changeSpeed >= 1500) {
                double randomDouble = Math.random();
                randomDouble = randomDouble * 100 + 1;
                int randomInt = (int) randomDouble;
                if (randomInt <= 75) setSpeed(1);
                else setSpeed(2);
                changeSpeed = -1;
            }
        } else {
            changeSpeed = System.currentTimeMillis();
        }
    }

    public void update() {
        if (alive) {
            ContactStillObject();
            ContactEntities();
            RandomSpeed();
            animate();
            switch (state) {
                case UP:
                    setY(getY() - speed);
                    setImg(Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate, 50).getFxImage());
                    break;
                case DOWN:
                    setY(getY() + speed);
                    setImg(Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate, 50).getFxImage());
                    break;
                case LEFT:
                    setX(getX() - speed);
                    setImg(Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate, 50).getFxImage());
                    break;
                case RIGHT:
                    setX(getX() + speed);
                    setImg(Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate, 50).getFxImage());
                    break;
            }
        } else {
            setImg(Sprite.balloom_dead.getFxImage());
            AfterKill();
        }
    }
}
