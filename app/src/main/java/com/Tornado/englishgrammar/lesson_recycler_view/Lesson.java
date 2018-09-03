package com.Tornado.englishgrammar.lesson_recycler_view;

public class Lesson {
    private String name;
    private String description;
    private int isFavorite;
    private int isleaning;
    private int type;
    private int id;

    public Lesson(String n, String des, int isFavorite, int isleaning, int type , int uuid) {
        this.id = uuid;
        name = n;
        description = des;
        this.isFavorite = isFavorite;
        this.type =type;
        this.isleaning =isleaning;
    }

    public String getLessonName() {
        return name;
    }

    public String getLessonDescription() {return description;}

    public void setLearning(int f) {
        isleaning = f;
    }

    public int isLearning() {
        return isleaning;
    }
    public int isFavorite() {
        return isFavorite;
    }

    public void setType(int f) {
        type = f;
    }

    public int isType() {
        return type;
    }


    public int getUuid() {
        return id;
    }
    public void getsetUuid(int uuid) {
         id = uuid;
    }
}
