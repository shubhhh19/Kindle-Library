package com.shubh.kindleLibrary.ui.category;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.shubh.kindleLibrary.R;
import com.shubh.kindleLibrary.adapters.NavCategoryAdapter;
import com.shubh.kindleLibrary.models.NavCategoryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CategoryFragment extends Fragment {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<NavCategoryModel> categoryModelList;
    NavCategoryAdapter navCategoryAdapter;
    ProgressBar progressBar;
    TextToSpeech mtts;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        db = FirebaseFirestore.getInstance();
        recyclerView = root.findViewById(R.id.cat_rec);

        progressBar = root.findViewById(R.id.progressbarr2);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        //Navigation Category Books
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        categoryModelList = new ArrayList<>();
        navCategoryAdapter = new NavCategoryAdapter(getActivity(),categoryModelList);
        recyclerView.setAdapter(navCategoryAdapter);

        db.collection("NavCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                NavCategoryModel navCategoryModel = document.toObject(NavCategoryModel.class);
                                categoryModelList.add(navCategoryModel);
                                navCategoryAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(),"Error"+task.getException(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                });

        navCategoryAdapter.setOnItemClickListener(new NavCategoryAdapter.onItemClickListener() {
            @Override
            public void MyTTS(int position) {
                tts(position);
            }
        });

        return root;
    }

    private void tts(int position) {
        mtts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!= TextToSpeech.ERROR){
                    mtts.setLanguage(Locale.ENGLISH);
                    mtts.speak(categoryModelList.get(position).getDescription(),1,null,null);

                }
            }
        });

    }
}