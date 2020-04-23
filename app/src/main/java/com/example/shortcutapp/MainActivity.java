package com.example.shortcutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button shortcut ,secondactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shortcut=findViewById(R.id.shortcut);
        secondactivity=findViewById(R.id.activity2);


        shortcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createShortcutOfApp();
            }
        });
        secondactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent second = new Intent(getApplicationContext(),
                        Shortcut.class);
                startActivity(second);
            }
        });

    }

    private void createShortcutOfApp() {

        Intent shortcutIntent = new Intent(getApplicationContext(),
                Shortcut.class);
        shortcutIntent.setAction(Intent.ACTION_MAIN);

        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "2nd Activity");
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(getApplicationContext(),
                      R.drawable.shortcut));

        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        addIntent.putExtra("duplicate", true);  //may it's already there so   don't duplicate
        getApplicationContext().sendBroadcast(addIntent);


    }
}
