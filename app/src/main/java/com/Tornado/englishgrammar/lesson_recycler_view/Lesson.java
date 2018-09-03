package com.Tornado.englishgrammar.lesson_recycler_view;

public class Lesson {
    private String name;
    private String description;
    private boolean isFavorite;
    private int uuid;

    public Lesson(String n, String des, boolean isFavorite, int uuid) {
        this.uuid = uuid;
        name = n;
        description = des;
        this.isFavorite = isFavorite;
    }

    public String getLessonName() {
        return name;
    }

    public String getLessonDescription() {return description;}

    public void setFavorite(boolean f) {
        isFavorite = f;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public int getUuid() {
        return uuid;
    }
}
