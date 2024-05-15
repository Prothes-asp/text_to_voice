package com.aspprothesbarai.texttovoice;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class HomePage extends AppCompatActivity {
    private TextInputEditText textMessageBox;
    private TextInputLayout TxtInputMessageBoxlayout;
    private AppCompatButton convertButton;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setStatusBarColor(getResources().getColor(R.color.appTitleColor));
        this.getWindow().setNavigationBarColor(getResources().getColor(R.color.appTitleColor));
        setContentView(R.layout.home_page);

        textMessageBox = findViewById(R.id.textMessageBox);
        TxtInputMessageBoxlayout = findViewById(R.id.TxtInputMessageBoxlayout);
        convertButton = findViewById(R.id.convertButton);

        textToSpeech = new TextToSpeech(HomePage.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                String getUserTxt = textMessageBox.getText().toString();
                if (getUserTxt.length() > 0){
                    textToSpeech.speak(getUserTxt,TextToSpeech.QUEUE_FLUSH,null,null);
                    boxSuccess();
                }
                else if (getUserTxt.length() <= 0){
                    boxError();
                }
            }
        });




    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void boxSuccess(){
        TxtInputMessageBoxlayout.setBoxStrokeColor(getResources().getColor(R.color.appTitleColor));
        TxtInputMessageBoxlayout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.appTitleColor)));
        TxtInputMessageBoxlayout.setCursorColor(ColorStateList.valueOf(getResources().getColor(R.color.appTitleColor)));
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void boxError(){
        textMessageBox.setError("Empty Box");
        TxtInputMessageBoxlayout.setBoxStrokeColor(getResources().getColor(R.color.red));
        TxtInputMessageBoxlayout.setHintTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
        TxtInputMessageBoxlayout.setCursorColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
        textMessageBox.setText(null);
    }


    // ============ Menu.....
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.privacy){}
        if (item.getItemId() == R.id.contact){}
        if (item.getItemId() == R.id.exit){
            warningPopUp();
        }
        return super.onOptionsItemSelected(item);
    }

    // BackPressed


    /** @noinspection deprecation*/
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        warningPopUp();
    }

    // Alert Dialogue
    public void warningPopUp(){
        AlertDialog alertdialog = new AlertDialog.Builder(HomePage.this)
                .setTitle("Warning !!!")
                .setMessage("Do you want exit this application ?")
                .setIcon(R.drawable.alert_orange)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finishAndRemoveTask();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
        alertdialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.appTitleColor));
        alertdialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.appTitleColor));
    }




}