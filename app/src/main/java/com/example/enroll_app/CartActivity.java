package com.example.enroll_app;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private TextView cartDetailsTextView;
    private TextView totalCreditTextView;

    private ArrayList<String> selectedSubjects = new ArrayList<>();
    private ArrayList<Integer> subjectCredits = new ArrayList<>();
    private int totalCredits = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartDetailsTextView = findViewById(R.id.cartDetailsTextView);
        totalCreditTextView = findViewById(R.id.totalCreditTextView);

        selectedSubjects = getIntent().getStringArrayListExtra("selectedSubjects");
        subjectCredits = getIntent().getIntegerArrayListExtra("subjectCredits");

        StringBuilder cartDetails = new StringBuilder();
        for (int i = 0; i < selectedSubjects.size(); i++) {
            cartDetails.append(selectedSubjects.get(i))
                    .append(" - Kredit: ").append(subjectCredits.get(i))
                    .append("\n");
            totalCredits += subjectCredits.get(i);
        }

        cartDetailsTextView.setText(cartDetails.toString());
        totalCreditTextView.setText("Total Kredit: " + totalCredits);

        if (totalCredits > 24) {
            Toast.makeText(this, "Total kredit melebihi 24, tidak bisa melanjutkan.", Toast.LENGTH_LONG).show();
        }
    }
}
