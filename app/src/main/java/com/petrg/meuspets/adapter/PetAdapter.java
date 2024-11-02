package com.petrg.meuspets.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petrg.meuspets.R;
import com.petrg.meuspets.model.PetModel;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    private List<PetModel> petList;

    public PetAdapter(List<PetModel> petList) {
        this.petList = petList;
    }

    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pet, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PetViewHolder holder, int position) {
        PetModel pet = petList.get(position);
        holder.textPetName.setText("Nome: " + pet.getNamePet());
        holder.textPetAge.setText("Idade: " + pet.getAgePet());
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    static class PetViewHolder extends RecyclerView.ViewHolder {
        TextView textPetName, textPetAge;

        public PetViewHolder(View itemView) {
            super(itemView);
            textPetName = itemView.findViewById(R.id.textPetName);
            textPetAge = itemView.findViewById(R.id.textPetAge);
        }
    }
}
