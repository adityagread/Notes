package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

        private final LayoutInflater mInflater;
        private List<Note> mnotes; // Cached copy of words

        NoteListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public NoteListAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycleview_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListAdapter.NoteViewHolder holder, int position) {
        if (mnotes != null) {
            Note current = mnotes.get(position);
            holder.noteItemView.setText(current.getMnote());
        } else {
            // Covers the case of data not being ready yet.
            holder.noteItemView.setText("No Word");
        }
    }

    void setNotes(List<Note> notes){
        mnotes = notes;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (mnotes != null)
            return mnotes.size();
        else return 0;
    }

    public Note getNotePosition(int position){
            return mnotes.get(position);
    }


    class NoteViewHolder extends RecyclerView.ViewHolder {
            private final TextView noteItemView;

            private NoteViewHolder(View itemView) {
                super(itemView);
                noteItemView = itemView.findViewById(R.id.textView);
            }
        }
    }