package com.Tornado.englishgrammar.lesson_recycler_view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.Tornado.englishgrammar.R;

class LessonHolder extends RecyclerView.ViewHolder {
    CardView lessonCard;
    TextView lessonName;
    TextView lessonDescription;
    ImageView imageView;

    LessonHolder(final View itemView) {
        super(itemView);
        lessonCard = (CardView) itemView.findViewById(R.id.lesson_card);
       // imageView = (ImageView) itemView.findViewById(R.id.image_tittle);
        lessonName = (TextView) itemView.findViewById(R.id.lesson_name);
        lessonDescription = (TextView) itemView.findViewById(R.id.lesson_description);
    }
}
