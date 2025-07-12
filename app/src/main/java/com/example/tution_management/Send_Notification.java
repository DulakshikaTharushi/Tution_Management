package com.example.tution_management;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tution_management.R;

import java.util.Calendar;

public class Send_Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_notification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void clickSendButton(View v) {
        EditText txtSending = findViewById(R.id.txtSendMessage);
        String enteredMsg = txtSending.getText().toString();
        //Intent myIntent = new Intent(this, ReceiveMessageActivity.class);
        //myIntent.putExtra("MyMessage",enteredMsg);
        //myIntent.putExtra("year", Calendar.getInstance().get(Calendar.YEAR));
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        myIntent.putExtra(Intent.EXTRA_TEXT, enteredMsg);
        //Toast.makeText(this, "enteredMsg", Toast.LENGTH_SHORT).show();

        Intent intentChooser = Intent.createChooser(myIntent,
                "Choose your messanger");
        try {
            startActivity(myIntent);
        } catch (ActivityNotFoundException e){
            Toast.makeText(this,  "Target ativity not found", Toast.LENGTH_LONG).show();
        }
    }
}
