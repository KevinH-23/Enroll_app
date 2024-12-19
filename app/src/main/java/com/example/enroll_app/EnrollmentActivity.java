package com.example.enroll_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EnrollmentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SubjectAdapter subjectAdapter;
    private ArrayList<Subject> subjectList = new ArrayList<>();
    private ArrayList<String> selectedSubjects = new ArrayList<>();
    private ArrayList<Integer> subjectCredits = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button proceedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        proceedButton = findViewById(R.id.proceedButton);

        // Fetch subjects from Firestore
        db.collection("subjects")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot documents = task.getResult();
                        for (QueryDocumentSnapshot document : documents) {
                            String subjectName = document.getId();
                            int credit = document.getLong("credit").intValue();
                            subjectList.add(new Subject(subjectName, credit));
                        }
                        subjectAdapter = new SubjectAdapter(subjectList, this::onSubjectSelected);
                        recyclerView.setAdapter(subjectAdapter);
                    } else {
                        Toast.makeText(this, "Error fetching subjects", Toast.LENGTH_SHORT).show();
                    }
                });

        // Proceed to CartActivity
        proceedButton.setOnClickListener(v -> {
            if (subjectCredits.size() > 0) {
                Intent intent = new Intent(EnrollmentActivity.this, CartActivity.class);
                intent.putStringArrayListExtra("selectedSubjects", selectedSubjects);
                intent.putIntegerArrayListExtra("subjectCredits", subjectCredits);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please select at least one subject", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onSubjectSelected(String subjectName, int credit, boolean isSelected) {
        if (isSelected) {
            selectedSubjects.add(subjectName);
            subjectCredits.add(credit);
        } else {
            selectedSubjects.remove(subjectName);
            subjectCredits.remove(Integer.valueOf(credit));
        }
    }
}
