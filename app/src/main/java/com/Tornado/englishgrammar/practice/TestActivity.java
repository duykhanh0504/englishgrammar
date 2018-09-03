package com.Tornado.englishgrammar.practice;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Tornado.englishgrammar.R;

public class TestActivity extends AppCompatActivity {

    Button checkButton;
    boolean[] isChecked = new boolean[30];
    int[] selectedAnswer = new int[30];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_test);

        checkButton = (Button) findViewById(R.id.check_button);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_test);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toolbar_test_activity)));
        }

        Typeface roboto = Typeface.createFromAsset(getAssets(), "font/Roboto-Medium.ttf");
        final int testIndex = getIntent().getIntExtra("testIndex", 0) + 1;
        toolbar.setTitle("");
        TextView title = (TextView) findViewById(R.id.toolbar_test_title);
        title.setText("Bài kiểm tra số " + new Integer(testIndex).toString() + "     ");
        title.setTypeface(roboto);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.toolbar_test_activity));
        }
        final ViewPager viewPager = (ViewPager) findViewById(R.id.practice_viewpager);
        final PagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        Button next = (Button) findViewById(R.id.next_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPage = viewPager.getCurrentItem();
                if (currentPage < 29) {
                    currentPage++;
                    viewPager.setCurrentItem(currentPage);
                }
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Đáp án là ";
                int currentPage = viewPager.getCurrentItem();
                int key = new ListQuestion().getListQuestion(testIndex).get(currentPage).answer;
                switch (key) {
                    case 1: message += "A.";
                        break;
                    case 2: message += "B.";
                        break;
                    case 3: message += "C.";
                        break;
                    case 4: message += "D.";
                        break;
                }
                int temp = selectedAnswer[currentPage];
                if (temp == 0) {
                    message = "Bạn chưa chọn đáp án!";
                } else if (key == temp) {
                    message = "Bạn đúng! " + message;
                    isChecked[currentPage] = true;
                } else {
                    message = "Bạn sai! " + message;
                    isChecked[currentPage] = true;
                }
                Toast toast = Toast.makeText(TestActivity.this, message, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        Button prev = (Button) findViewById(R.id.prev_button);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPage = viewPager.getCurrentItem();
                if (currentPage > 0) {
                    currentPage--;
                    viewPager.setCurrentItem(currentPage);
                }
            }
        });
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 30;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    void setSelectedAnswer(int index, int value) {
        selectedAnswer[index] = value;
    }
}
