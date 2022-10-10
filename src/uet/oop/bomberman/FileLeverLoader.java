package uet.oop.bomberman;

import java.io.File;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class FileLeverLoader {

    public FileLeverLoader() {

    }
    private char[][] map;
    private int height;
    private int width;
    private int level;


    public char getMapAt(int i, int j) {
        return map[i][j];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void loadLevel (int level) {
        //đọc dữ liệu từ tệp cấu hình /levers/Level{}.txt
        // cap nhat gia tri doc duoc vao width, height, level, map
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
        level = Integer.parseInt(arrays[0]);
        height = Integer.parseInt(arrays[1]);
        width = Integer.parseInt(arrays[2]);
        map = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = list.get(i + 1).charAt(j);
            }
        }
        //gan cac phan tu cho mang
    }
}