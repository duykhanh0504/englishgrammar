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
        return new Lesson(lessonName, lessonDescription, isFavorite == 1, uuid);
    }
}
