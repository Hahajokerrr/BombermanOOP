package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.entities.MovingEntity.State;
import uet.oop.bomberman.graphics.Sprite;

public class Minvo extends Enemy {

    private Balloom balloom = new Balloom(-50, -50, Sprite.balloom_left1.getFxImage(), this.bomber);
    private Doll doll = new Doll(-50, -50, Sprite.doll_left1.getFxImage(), this.bomber);

    public Balloom getBalloom() {
        return balloom;
    }

    public void setBalloom(Balloom balloom) {
        this.balloom = balloom;
    }

    public Doll getDoll() {
        return doll;
    }

    public void setDoll(Doll doll) {
        this.doll = doll;
    }

    public Minvo(int x, int y, Image img, Bomber bomber) {
        super(x, y, img, bomber);
        Score = 250;
        setState(State.LEFT);
    }

    public void AfterKill() {
        if(AfterKill == 0) bomber.enemyNum[bomber.level-1]--;
        AfterKill++;
        if(AfterKill >= 50) setImg(Sprite.mob_dead1.getFxImage());
        if(AfterKill >= 100) setImg(Sprite.mob_dead2.getFxImage());
        if(AfterKill >= 150) setImg(Sprite.mob_dead3.getFxImage());
        if(AfterKill == 200) {
            balloom.setX(this.x);
            balloom.setY(this.y);
            doll.setX(this.x);
            doll.setY(this.y);

            AfterKill = 201;
            Erase();
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
                    setImg(Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, 50).getFxImage());
                    break;
                case DOWN:
                    setY(getY() + speed);
                    setImg(Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate, 50).getFxImage());
                    break;
                case LEFT:
                    setX(getX() - speed);
                    setImg(Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, 50).getFxImage());
                    break;
                case RIGHT:
                    setX(getX() + speed);
                    setImg(Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate, 50).getFxImage());
                    break;
            }
        } else {
            setImg(Sprite.balloom_dead.getFxImage());
            AfterKill();
        }
    }


}
