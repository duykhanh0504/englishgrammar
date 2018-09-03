package com.Tornado.englishgrammar.home_page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.Tornado.englishgrammar.lesson_recycler_view.Lesson;
import com.Tornado.englishgrammar.lesson_recycler_view.LessonAdapter;
import com.Tornado.englishgrammar.lesson_recycler_view.LessonList;

import java.util.ArrayList;
import java.util.List;

import com.Tornado.englishgrammar.R;

public class TenseLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_favorite_lesson);
        Toolbar toolbar = (Toolbar) findViewById(R.id.favorite_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        RecyclerView mLessonRecyclerView = (RecyclerView) findViewById(R.id.favorite_lesson_recycler);
        mLessonRecyclerView.setHasFixedSize(true);
        mLessonRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        LessonList lessonList = LessonList.get(this);
        List<Lesson> tenseLessons = new ArrayList<>();
        for (Lesson lesson: lessonList.getLessons()) {
            if (lesson.getUuid() >= 40 && lesson.getUuid() <= 47) tenseLessons.add(lesson);
        }
        RecyclerView.Adapter mLessonAdapter = new LessonAdapter(this, tenseLessons);
        mLessonRecyclerView.setAdapter(mLessonAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
}
