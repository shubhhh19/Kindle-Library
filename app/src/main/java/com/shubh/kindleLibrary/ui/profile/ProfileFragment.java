package com.shubh.kindleLibrary.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shubh.kindleLibrary.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    CircleImageView profileImg;
    EditText name, email, number, address;
    Button update;

    FirebaseAuth auth;
    FirebaseFirestore database;
    FirebaseUser user;
    String userid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        user = auth.getCurrentUser();
        userid = auth.getCurrentUser().getUid();

        profileImg = root.findViewById(R.id.profile_img);
        name = root.findViewById(R.id.profile_name);
        email = root.findViewById(R.id.profile_email);
        number = root.findViewById(R.id.profile_number);
        address = root.findViewById(R.id.profile_address);
        update = root.findViewById(R.id.update);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();
            }
        });


        return root;
    }

    private void updateUserProfile() {

    }
}