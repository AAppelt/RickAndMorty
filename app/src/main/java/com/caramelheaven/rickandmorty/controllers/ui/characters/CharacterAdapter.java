package com.caramelheaven.rickandmorty.controllers.ui.characters;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.caramelheaven.rickandmorty.R;
import com.caramelheaven.rickandmorty.datasourse.entity.character.Character;
import com.caramelheaven.rickandmorty.utils.AonItemClickListener;
import com.caramelheaven.rickandmorty.utils.CharacterDiffCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Character> characterList;
    private AonItemClickListener aonItemClickListener;

    public CharacterAdapter(List<Character> characterList) {
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new CharacterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CharacterVH characterVH = (CharacterVH) holder;
        characterVH.charName.setText(characterList.get(position).getName());
        characterVH.speciesPerson.setText("Schecies: " + characterList.get(position).getSpecies());
        characterVH.genderPerson.setText("Gender: " + characterList.get(position).getGender());
        characterVH.statusPerson.setText("Status: " + characterList.get(position).getStatus());

        if (characterList.get(position).getImage() != null) {
            Glide.with(characterVH.imagePerson.getContext())
                    .load(characterList.get(position).getImage())
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                    .apply(new RequestOptions().error(R.drawable.error)
                            .placeholder(R.drawable.animview))
                    .into(characterVH.imagePerson);
        }
    }

    public void updateAdapter(List<Character> list) {
        final CharacterDiffCallback diffCallback = new CharacterDiffCallback(this.characterList, list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.characterList.clear();
        this.characterList.addAll(list);
        diffResult.dispatchUpdatesTo(this);
        Timber.d("I'm updated");
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    class CharacterVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.char_name)
        TextView charName;
        @BindView(R.id.statusPerson)
        TextView statusPerson;
        @BindView(R.id.genderPerson)
        TextView genderPerson;
        @BindView(R.id.imagePerson)
        ImageView imagePerson;
        @BindView(R.id.speciesPerson)
        TextView speciesPerson;
        @BindView(R.id.cardView)
        CardView cardView;

        public CharacterVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            aonItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public void setAonItemClickListener(AonItemClickListener aonItemClickListener) {
        this.aonItemClickListener = aonItemClickListener;
    }
}
