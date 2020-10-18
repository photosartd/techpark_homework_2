package ru.mail.homework2.adapter_bindet_logic;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.mail.homework2.constants.Constants;

/*
Generating data for cubes
 */
public class CubeList {
    private static final CubeList instance = new CubeList();
    private final List<CubeData> list;
    private Random random;
    private static int next = 100;

    public static CubeList getInstance() {return instance;}


    public List<CubeData> getData() {
        return list;
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

    //For statical adding cubes with on button pressed
    public static void addRandomCube(List<CubeData> list) {
        Random random = new Random();
        String text = String.valueOf(next);
        int color = Constants.mColors[random.nextInt(Constants.mColors.length)];
        list.add(new CubeData(text, color));
        next++;
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
