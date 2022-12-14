package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    protected boolean removed = false;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getXRound() {
        return (x / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE;
    }

    public int getYRound() {
        return (y / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE;
    }

    public int getXTile() {
        return x/Sprite.SCALED_SIZE;
    }

    public int getYTile() {
        return y/Sprite.SCALED_SIZE;
    }


    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity() {

    }

    public boolean collision(Entity other) {
        // TODO: Kiem tra va cham cua 2 thuc the.
        int leftA, leftB;
        int rightA, rightB;
        int topA, topB;
        int bottomA, bottomB;

        //Calculate the sides of rect of this
        leftA = x;
        rightA = x + Sprite.SCALED_SIZE;
        topA = y;
        bottomA = y + Sprite.SCALED_SIZE;

        //Calculate the sides of rect of other
        leftB = other.x;
        rightB = other.x + Sprite.SCALED_SIZE;
        topB = other.y;
        bottomB = other.y + Sprite.SCALED_SIZE;

        if (bottomA <= topB) {
            return false;
        }

        if (topA >= bottomB) {
            return false;
        }

        if (rightA <= leftB) {
            return false;
        }

        if (leftA >= rightB) {
            return false;
        }
        //If none of the sides from A are outside B
        return true;
    }

    public void Erase() {
        setX(-50);
        setY(-50);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();
}
