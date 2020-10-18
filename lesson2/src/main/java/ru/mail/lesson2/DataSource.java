package ru.mail.lesson2;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

class DataSource {
    private static final DataSource ourInstance = new DataSource();
    private final List<NewsModel> list;
    private String[] mtitles = new String[] {
            "titel1",
            "titel2",
            "titel3",
            "titel4",
            "titel5",
            "titel6",
            "titel7"
    };

    private int[] mColors = new int[] {
            Color.GREEN,
            Color.RED,
            Color.CYAN,
            Color.BLACK,
            Color.BLUE
    };

    static DataSource getInstance() {return ourInstance;}

    public List<NewsModel> getData() {
        return list;
    }

    private DataSource() {
        list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            String title = mtitles[random.nextInt(mtitles.length)];
            String date = String.valueOf(new Date(random.nextInt()));
            int color = mColors[random.nextInt(mColors.length)];
            list.add(new NewsModel(title, date, color));
        }
    }

    public static class NewsModel {
        private String mTitel;

        private String mDate;
        private int mColor;
        public NewsModel(String mTitel, String mDate, int mColor) {
            this.mTitel = mTitel;
            this.mDate = mDate;
            this.mColor = mColor;
        }

        public String getmTitel() {
            return mTitel;
        }

        public String getmDate() {
            return mDate;
        }

        public int getmColor() {
            return mColor;
        }
    }
}
