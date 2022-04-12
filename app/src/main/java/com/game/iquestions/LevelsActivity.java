package com.game.iquestions;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class LevelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);


       Button Flags = findViewById(R.id.FOC);
        findViewById(R.id.FOC).setOnClickListener(view -> {
            Intent intent = new Intent(LevelsActivity.this, FOC_Act.class);
            startActivity(intent);
        });
    }
}
//TODO добавить кнопку паузы
//     Button undo_back = findViewById(R.id.undo_back);
//        findViewById(R.id.undo_back).setOnClickListener(view -> {
//            Intent undo_back1 = new Intent(LevelsActivity.this, StartApp.class);
//            startActivity(undo_back1);
//        });
//    }