package ru.mail.homework2.adapter_bindet_logic;


import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;


/*
Generating data for cubes
 */
public class CubeList {
    private static volatile CubeList instance;
    private static final List<CubeData> list;

    static {
        list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            String text = String.valueOf(i);
            int color = (i % 2 == 1) ? Color.RED : Color.BLUE;
            list.add(new CubeData(text, color));
        }
    }

    public static CubeList getInstance() {
        if (instance == null) {
            synchronized (CubeList.class) {
                if (instance == null) {
                    instance = new CubeList();
                }
            }
        }
        return instance;
    }


    public static List<CubeData> getData() {
        return list;
    }

    private CubeList() {
    }

    //For statical adding cubes with on button pressed
    public static void addRandomCube() {
        int next = list.size() + 1;
        String text = String.valueOf(next);
        int color = (next % 2 == 1) ? Color.RED : Color.BLUE;
        list.add(new CubeData(text, color));
    }

    public static class CubeData {

        private final String text;
        private final int color;

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
