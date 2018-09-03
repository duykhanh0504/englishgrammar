package com.Tornado.englishgrammar.database;

import android.provider.BaseColumns;

public final class LessonDatabase {
    private LessonDatabase() {
    }

    public static class LessonTable implements BaseColumns{
        public static final String NAME = "lessons";

        public static final String UUID = "id";
        public static final String lessonName = "name";
        public static final String lessonDescription = "description";
        public static final String ISFAVORITE = "isFavorite";
        public static final String ISlEANING = "isleaning";
        public static final String TYPE = "type";
    }
}
