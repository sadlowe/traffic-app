package com.tp.transport;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tp.transport.GererSignalement;

import java.util.List;

public class SignalementAdapter extends RecyclerView.Adapter<SignalementAdapter.SignalementViewHolder> {

    private final List<GererSignalement> signalementList;

    public SignalementAdapter(List<GererSignalement> signalementList) {
        this.signalementList = signalementList;
    }

    @NonNull
    @Override
    public SignalementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_signalement, parent, false);
        return new SignalementViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SignalementViewHolder holder, int position) {
        GererSignalement signalement = signalementList.get(position);
        holder.bind(signalement);
    }

    @Override
    public int getItemCount() {
        return signalementList.size();
    }

    public static class SignalementViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewProblemType;
        private final TextView textViewContactEmail;
        private final TextView textViewGravity;
        private final TextView textViewDescription;

        public SignalementViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProblemType = itemView.findViewById(R.id.problem_type);
            textViewContactEmail = itemView.findViewById(R.id.contact_email);
            textViewGravity = itemView.findViewById(R.id.gravity);
            textViewDescription = itemView.findViewById(R.id.description);
        }

        @SuppressLint("SetTextI18n")
        public void bind(GererSignalement signalement) {
            textViewProblemType.setText("Type de problème : " + signalement.getProblemType());
            textViewContactEmail.setText("Email de contact : " + signalement.getContactEmail());
            textViewGravity.setText("Gravité : " + signalement.getGravity());
            textViewDescription.setText("Description : " + signalement.getDescription());
        }
    }
}
