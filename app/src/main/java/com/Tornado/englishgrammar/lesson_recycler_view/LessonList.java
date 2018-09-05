package com.Tornado.englishgrammar.lesson_recycler_view;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

import com.Tornado.englishgrammar.database.LessonBaseHelper;
import com.Tornado.englishgrammar.database.LessonCursorWrapper;
import com.Tornado.englishgrammar.database.LessonDatabase;
import com.Tornado.englishgrammar.database.LessonDatabase.LessonTable;

public class LessonList {
    private static LessonList sLessonList;
    private SQLiteDatabase mDatabase;

    public static LessonList get(Context context) {
        if (sLessonList == null) {
            sLessonList = new LessonList(context);
        }
        return sLessonList;
    }

    public List<Lesson> getLessons() {
        List<Lesson> lessons = new ArrayList<>();
        LessonCursorWrapper cursor = queryLessons(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                lessons.add(cursor.getLesson());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return lessons;
    }

    public List<Lesson> searchLesson(String text) {
        List<Lesson> lessons = new ArrayList<>();
        LessonCursorWrapper cursor = queryLessons(LessonTable.lessonName +" like '%" +text+"%'" , null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                lessons.add(cursor.getLesson());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return lessons;
    }


    public List<Lesson> getLessonsSentence() {
        List<Lesson> lessons = new ArrayList<>();
        LessonCursorWrapper cursor = queryLessons(LessonTable.TYPE +" = 1", null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                lessons.add(cursor.getLesson());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return lessons;
    }

    public List<Lesson> getLessonsWord() {
        List<Lesson> lessons = new ArrayList<>();
        LessonCursorWrapper cursor = queryLessons(LessonTable.TYPE +" = 2", null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                lessons.add(cursor.getLesson());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return lessons;
    }

    private ContentValues getContentValues(Lesson lesson) {
        ContentValues values = new ContentValues();
        values.put(LessonTable.lessonName, lesson.getLessonName());
        values.put(LessonTable.lessonDescription, lesson.getLessonDescription());
        values.put(LessonTable.ISFAVORITE, lesson.isFavorite());
        return values;
    }

    public void updateLesson(Lesson lesson) {
        String lessonName = lesson.getLessonName();
        ContentValues values = getContentValues(lesson);
        mDatabase.update(LessonTable.NAME, values, LessonTable.lessonName + " = ?", new String[] {lessonName});
    }

    private LessonCursorWrapper queryLessons(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(LessonTable.NAME, null, whereClause, whereArgs, null, null, null);
        return new LessonCursorWrapper(cursor);
    }

    private LessonList(Context context) {
        Context mContext = context.getApplicationContext();
        mDatabase =  LessonBaseHelper.getDatabase(mContext).getWritableDatabase();
    }
}
