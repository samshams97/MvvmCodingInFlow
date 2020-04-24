package com.example.mvvmcodinginflow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    List<Note>noteList = new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_items,parent,false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note notes = noteList.get(position);
        holder.txtTitle.setText(notes.getTitle());
        holder.txtDescription.setText(notes.getDescription());
        holder.txtPriority.setText(String.valueOf(notes.getPriority()));

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void setNoteList(List<Note> noteList1){
        this.noteList = noteList1;
        notifyDataSetChanged();
    }
    public Note getPosition(int position){
        return noteList.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        TextView txtPriority;
        TextView txtDescription;
        TextView txtTitle;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            txtPriority = itemView.findViewById(R.id.priority_note);
            txtDescription = itemView.findViewById(R.id.description_note);
            txtTitle = itemView.findViewById(R.id.title_note);
        }
    }
}
