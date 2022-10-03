package uet.oop.bomberman.entities.Tile.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovingEntity.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Mover;

public class SpeedItem extends Item{
    public SpeedItem(int x, int y, Image img) {
        super(x,y,img);
    }

    public void Contact(Bomber bomber) {
        bomber.setSpeedItem(true);
        Erase();
    }
}
