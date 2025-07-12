package com.example.tution_management.Student;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tution_management.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;

public class Student_QR_code extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_qr_code);

        ImageView imageViewQRCode = findViewById(R.id.imageViewQRCode);

        // Get data passed from Student_Dashboard via Intent extras
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String contact = getIntent().getStringExtra("contact");

        // Check for nulls and set default values if necessary
        if (name == null) name = "No Name";
        if (email == null) email = "No Email";
        if (contact == null) contact = "No Contact";

        // Combine the data into a formatted string for QR code content
        String studentInfo = "Name: " + name + "\nEmail: " + email + "\nContact: " + contact;

        // Generate QR code bitmap and display it
        QRCodeWriter writer = new QRCodeWriter();
        try {
            com.google.zxing.common.BitMatrix bitMatrix = writer.encode(studentInfo, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

            // Fill bitmap pixels based on QR code matrix
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            imageViewQRCode.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to generate QR code", Toast.LENGTH_SHORT).show();
        }
    }
}
