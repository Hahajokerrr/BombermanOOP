package uet.oop.bomberman.entities.Bomb;

import uet.oop.bomberman.entities.MovingEntity.Bomber;

import java.util.ArrayList;
import java.util.List;

public class BombManager {
    private List<Bomb> BombList = new ArrayList<>();
    private List<Bomb> PlacedBomb = new ArrayList<>();
    private int MaxBombNum = 3;
    private int BombNum = 0;
    private boolean isPlacing = false;

    public BombManager() {
    }

    /**
     * Thêm bomb vào mảng.
     */
    public void addBomb(Bomb bomb) {
        BombList.add(bomb);
    }

    /**
     * Đặt bom ở vị trí của bomber.
     */
    public void PlaceBomb(Bomber bomber) {
        if (isPlacing == false && BombNum < MaxBombNum) {
            for (int i = 0; i < BombList.size(); i++) {
                if (!BombList.get(i).isPlaced()) {
                    BombList.get(i).setX(bomber.getXTile());
                    BombList.get(i).setY(bomber.getYTile());
                    BombList.get(i).setPlaced(true);
                    BombList.get(i).setPlacedTime(System.currentTimeMillis());
                    PlacedBomb.add(BombList.get(i));
                    System.out.println(i);
                    BombNum = PlacedBomb.size();
                    //System.out.println(BombNum);
                    isPlacing = true;
                    break;
                }
            }
        }
    }

    public void DetonateBomb() {
        if (PlacedBomb.size() > 0) {
            if (PlacedBomb.get(0).DetonateBomb()) {
                PlacedBomb.remove(0);
                BombNum = PlacedBomb.size();
            }
        }
    }

    public void DonePlacing() {
        isPlacing = false;
    }

}
