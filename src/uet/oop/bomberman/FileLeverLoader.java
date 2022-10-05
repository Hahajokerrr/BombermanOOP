package uet.oop.bomberman;

import java.util.List;
import uet.oop.bomberman.entities.Tile.LevelLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class FileLeverLoader extends LevelLoader {

    private static  char[][] _map;
    private static int _height;
    private static int _width;
    private static int _level;

    @Override
    public void loadLever (int level) {
        //đọc dữ liệu từ tệp cấu hình /levers/Level{}.txt
        // cap nhat gia tri doc duoc vao _width, _height, _level, _map
        List<String> list = new ArrayList<>();
        try {   // doc tep luu map
            FileReader fr = new FileReader("res\\levels\\Level" + level + ".txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (!line.equals("")) {
                list.add(line);
                line = br.readLine();
                //doc file txt luu vao list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] arrays = list.get(0).trim().split(" ");
        _level = Integer.parseInt(arrays[0]);
        _height = Integer.parseInt(arrays[1]);
        _width = Integer.parseInt(arrays[2]);
        _map = new char[_height][_width];
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                _map[i][j] = list.get(i + 1).charAt(j);
            }
        }
        //gan cac phan tu cho mang
    }
}