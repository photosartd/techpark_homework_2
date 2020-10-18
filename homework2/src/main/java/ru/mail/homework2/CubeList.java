package ru.mail.homework2;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class CubeList {
    private static final CubeList instance = new CubeList();
    private final List<CubeData> list;
    private Random random;
    private static int next = 100;

    static CubeList getInstance() {return instance;}


    public List<CubeData> getData() {
        return list;
    }

    static void addRandomCube(List<CubeData> list) {
        Random random = new Random();
        String text = String.valueOf(next);
        int color = Constants.mColors[random.nextInt(Constants.mColors.length)];
        list.add(new CubeData(text, color));
        next++;
    }

    private CubeList() {
        random = new Random();
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String text = String.valueOf(i);
            int color = Constants.mColors[random.nextInt(Constants.mColors.length)];
            list.add(new CubeData(text, color));
        }
    }

    public static class CubeData {
        private String text;
        private int color;

        public CubeData(String text, int color) {
            this.text = text;
            this.color = color;
        }

        public String getText() {
            return text;
        }

        public int getColor() {
            return color;
        }
    }

}
