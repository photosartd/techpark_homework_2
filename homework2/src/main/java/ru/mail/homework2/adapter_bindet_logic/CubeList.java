package ru.mail.homework2.adapter_bindet_logic;


import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;



/*
Generating data for cubes
 */
public class CubeList {
    private static final CubeList instance = new CubeList();
    private final List<CubeData> list;
    private static int next = 101;

    public static CubeList getInstance() {return instance;}


    public List<CubeData> getData() {
        return list;
    }

    private CubeList() {
        list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            String text = String.valueOf(i);
            int color;
            if (i % 2 == 1) {
                color = Color.RED;
            }
            else {
                color = Color.BLUE;
            }
            list.add(new CubeData(text, color));
        }
    }

    //For statical adding cubes with on button pressed
    public static void addRandomCube(List<CubeData> list) {
        String text = String.valueOf(next);
        int color;
        if (next % 2 == 1) {
            color = Color.RED;
        }
        else {
            color = Color.BLUE;
        }
        list.add(new CubeData(text, color));
        next++;
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
