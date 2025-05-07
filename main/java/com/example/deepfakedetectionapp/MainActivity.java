package com.example.deepfakedetectionapp;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.graphics.Typeface;

public class MainActivity extends AppCompatActivity {

    private boolean isDetectOn = true;
    private TextView tabDetect, tabExample, tabStat;
    private TextView selectedTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        ImageView detectButton = findViewById(R.id.detectButton);
        TextView historyButton = findViewById(R.id.historyButton);
        tabDetect = findViewById(R.id.tab_detect);
        tabExample = findViewById(R.id.tab_example);
        tabStat = findViewById(R.id.tab_stat);

        selectedTab = tabDetect;

        tabDetect.setOnClickListener(v -> activateTab(tabDetect));
        tabExample.setOnClickListener(v -> activateTab(tabExample));
        tabStat.setOnClickListener(v -> activateTab(tabStat));

        detectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bg_color, txt_color;
                Drawable drawable;

                if (isDetectOn) {
                    detectButton.setImageResource(R.drawable.detect_off);
                    bg_color = getResources().getColor(R.color.orange_bg);
                    txt_color = getResources().getColor(R.color.orange_text);
                    drawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.orange_rounded_outline_button);
                } else {
                    detectButton.setImageResource(R.drawable.detect_on);
                    bg_color = getResources().getColor(R.color.blue_bg);
                    txt_color = getResources().getColor(R.color.blue_text);
                    drawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.blue_rounded_outline_button);
                }
                mainLayout.setBackgroundColor(bg_color);
                historyButton.setTextColor(txt_color);
                historyButton.setBackground(drawable);
                isDetectOn = !isDetectOn;
                activateTab(selectedTab);
            }
        });
    }

    private void activateTab(TextView selectedTab) {
        // 모든 탭 초기화 (회색)
        tabDetect.setTextColor(ContextCompat.getColor(this, R.color.gray));
        tabExample.setTextColor(ContextCompat.getColor(this, R.color.gray));
        tabStat.setTextColor(ContextCompat.getColor(this, R.color.gray));

        tabDetect.setTypeface(null, Typeface.NORMAL);
        tabExample.setTypeface(null, Typeface.NORMAL);
        tabStat.setTypeface(null, Typeface.NORMAL);

        int txt_color = 0;
        if(isDetectOn)
            txt_color = getColor(R.color.blue_text);
        else
            txt_color = getColor(R.color.orange_text);
        // 선택된 탭만 파란색 + 굵게
        selectedTab.setTextColor(txt_color);
        selectedTab.setTypeface(null, Typeface.BOLD);
        this.selectedTab = selectedTab;
    }
}
