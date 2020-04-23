package com.example.shortcutapp;


import android.accessibilityservice.AccessibilityService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    Button shortcut;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shortcut = findViewById(R.id.shortcut);


        shortcut.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {


                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

                    ShortcutManager shortcutManager = (ShortcutManager) getSystemService(Context.SHORTCUT_SERVICE);

                    if (shortcutManager.isRequestPinShortcutSupported()) {

                        ShortcutInfo pinned = new ShortcutInfo.Builder(MainActivity.this,"Pinned")
                                .setShortLabel("pin")
                                .setLongLabel("pinned")
                                .setIcon(Icon.createWithResource(MainActivity.this, R.drawable.shortcut))
                                .setIntent(new Intent(MainActivity.this, Shortcut.class).setAction("pinned"))
                                .build();

                        if (alreadyexist(pinned)) {
                            Toast.makeText(MainActivity.this, "Already exist", Toast.LENGTH_SHORT).show();
                        }

                        shortcutManager.requestPinShortcut(pinned, null);


                    }

                }
                else {
                    Toast.makeText(MainActivity.this, "Shortcut Not Supported On thos device", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    private boolean alreadyexist(ShortcutInfo shortcut) {

        boolean exist = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) ;
        {

            ShortcutManager shortcutManager = (ShortcutManager) getSystemService(Context.SHORTCUT_SERVICE);

            for (int i = 0; i < shortcutManager.getPinnedShortcuts().size(); i++) {

                ShortcutInfo shortcutInfo = shortcutManager.getPinnedShortcuts().get(i);

                if (shortcut.getId().equals(shortcutInfo.getId())) {
                    exist = true;
                    break;
                }

            }} return exist;
    }

}