package com.example.enroll_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private ArrayList<Subject> subjects;
    private SubjectSelectionListener selectionListener;

    public SubjectAdapter(ArrayList<Subject> subjects, SubjectSelectionListener selectionListener) {
        this.subjects = subjects;
        this.selectionListener = selectionListener;
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_item, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int position) {
        Subject subject = subjects.get(position);

        holder.subjectNameTextView.setText(subject.getName());
        holder.creditTextView.setText("Credit: " + subject.getCredit());

        // Reset listener untuk menghindari efek daur ulang
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(subject.isSelected());

        // Tambahkan listener baru
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            subject.setSelected(isChecked);
            selectionListener.onSubjectSelected(subject.getName(), subject.getCredit(), isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
        TextView subjectNameTextView;
        TextView creditTextView;
        CheckBox checkBox;

        public SubjectViewHolder(View itemView) {
            super(itemView);
            subjectNameTextView = itemView.findViewById(R.id.subjectNameTextView);
            creditTextView = itemView.findViewById(R.id.creditTextView);
            checkBox = itemView.findViewById(R.id.subjectCheckBox);
        }
    }

    public interface SubjectSelectionListener {
        void onSubjectSelected(String subjectName, int credit, boolean isSelected);
    }
}
