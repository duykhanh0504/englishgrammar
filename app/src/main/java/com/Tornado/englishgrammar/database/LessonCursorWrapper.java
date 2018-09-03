package com.Tornado.englishgrammar.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.Tornado.englishgrammar.lesson_recycler_view.Lesson;

public class LessonCursorWrapper extends CursorWrapper {
    public LessonCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Lesson getLesson() {
        String lessonName = getString(getColumnIndex(LessonDatabase.LessonTable.lessonName));
        String lessonDescription = getString(getColumnIndex(LessonDatabase.LessonTable.lessonDescription));
        int isFavorite = getInt(getColumnIndex(LessonDatabase.LessonTable.ISFAVORITE));
        int uuid = getInt(getColumnIndex(LessonDatabase.LessonTable.UUID));
        int islearning = getInt(getColumnIndex(LessonDatabase.LessonTable.ISlEANING));
        int type = getInt(getColumnIndex(LessonDatabase.LessonTable.TYPE));
        return new Lesson(lessonName, lessonDescription, isFavorite ,islearning,type, uuid);
    }
}
