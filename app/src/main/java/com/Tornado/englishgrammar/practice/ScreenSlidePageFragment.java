package com.Tornado.englishgrammar.practice;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.Tornado.englishgrammar.R;

public class ScreenSlidePageFragment extends Fragment {

    int questionIndex;
    private CardView cardA;
    private CardView cardB;
    private CardView cardC;
    private CardView cardD;

    public ScreenSlidePageFragment() {
    }

    public static ScreenSlidePageFragment newInstance(int sectionNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt("questionIndex", sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        TextView numberQuestion = (TextView) view.findViewById(R.id.question_number);
        TextView question = (TextView) view.findViewById(R.id.question);
        TextView answerA = (TextView) view.findViewById(R.id.answerA);
        TextView answerB = (TextView) view.findViewById(R.id.answerB);
        TextView answerC = (TextView) view.findViewById(R.id.answerC);
        TextView answerD = (TextView) view.findViewById(R.id.answerD);
        int testIndex = getActivity().getIntent().getIntExtra("testIndex", 0) + 1;
        Bundle args = getArguments();
        questionIndex = args.getInt("questionIndex");
        final ListQuestion.Question wholeQuestion = new ListQuestion().getListQuestion(testIndex).get(questionIndex);
        numberQuestion.setText("Câu " + new Integer(questionIndex + 1).toString() + ": ");
        question.setText(wholeQuestion.question);
        answerA.setText("A.  " + wholeQuestion.a);
        answerB.setText("B.  " + wholeQuestion.b);
        answerC.setText("C.  " + wholeQuestion.c);
        answerD.setText("D.  " + wholeQuestion.d);

        cardA = (CardView) view.findViewById(R.id.cardA);
        cardB = (CardView) view.findViewById(R.id.cardB);
        cardC = (CardView) view.findViewById(R.id.cardC);
        cardD = (CardView) view.findViewById(R.id.cardD);

        boolean isChecked = ((TestActivity)getContext()).selectedAnswer[questionIndex] != 0;
        if (isChecked) {
            switch (((TestActivity)getContext()).selectedAnswer[questionIndex]) {
                case 1: cardA.setCardBackgroundColor(Color.parseColor("#FFDC73"));
                    break;
                case 2: cardB.setCardBackgroundColor(Color.parseColor("#FFDC73"));
                    break;
                case 3: cardC.setCardBackgroundColor(Color.parseColor("#FFDC73"));
                    break;
                case 4: cardD.setCardBackgroundColor(Color.parseColor("#FFDC73"));
                    break;
            }
        }

        cardA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(1);
            }
        });
        cardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(2);
            }
        });

        cardC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(3);
            }
        });
        cardD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(4);
            }
        });
        return view;
    }

    private void setCardColor(int i) {
        boolean isChecked = ((TestActivity)getContext()).isChecked[questionIndex];
        if (isChecked == true) return;
        switch (i) {
            case 1: cardA.setCardBackgroundColor(Color.parseColor("#FFDC73"));
                cardB.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                cardC.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                cardD.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                ((TestActivity)getContext()).setSelectedAnswer(questionIndex, 1);
                break;
            case 2: cardB.setCardBackgroundColor(Color.parseColor("#FFDC73"));
                cardA.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                cardC.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                cardD.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                ((TestActivity)getContext()).setSelectedAnswer(questionIndex, 2);
                break;
            case 3: cardC.setCardBackgroundColor(Color.parseColor("#FFDC73"));
                cardB.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                cardA.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                cardD.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                ((TestActivity)getContext()).setSelectedAnswer(questionIndex, 3);
                break;
            case 4: cardD.setCardBackgroundColor(Color.parseColor("#FFDC73"));
                cardB.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                cardC.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                cardA.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                ((TestActivity)getContext()).setSelectedAnswer(questionIndex, 4);
                break;
        }
    }
}
