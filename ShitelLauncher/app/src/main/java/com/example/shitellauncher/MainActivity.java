package com.example.shitellauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        bind(R.id.btn_phone, new Intent(Intent.ACTION_DIAL));
        bind(R.id.btn_sms, Intent.makeMainSelectorActivity(
                Intent.ACTION_MAIN, Intent.CATEGORY_APP_MESSAGING));
        bind(R.id.btn_calc, Intent.makeMainSelectorActivity(
                Intent.ACTION_MAIN, Intent.CATEGORY_APP_CALCULATOR));
        bind(R.id.btn_calendar, Intent.makeMainSelectorActivity(
                Intent.ACTION_MAIN, Intent.CATEGORY_APP_CALENDAR));

        findViewById(R.id.btn_phone).requestFocus();
    }

    private void bind(int id, final Intent intent) {
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                try {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "App not found",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Ignore every touch: the launcher works with the keypad only
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) { return true; }

    @Override
    public void onBackPressed() { /* launcher: do nothing */ }
}
