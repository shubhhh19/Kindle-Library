package com.shubh.kindleLibrary;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shubh.kindleLibrary.models.ViewAllModel;

import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;

    ImageView detailedImg, addBook, removeBook;
    TextView price, rating, description, stock;
    Button addToCart;
    Toolbar toolbar1;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    ViewAllModel viewAllModel;
    //ViewAllModel viewAllModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        quantity = findViewById(R.id.quantity);
        detailedImg = findViewById(R.id.detailed_img);
        addBook = findViewById(R.id.add_book);
        removeBook = findViewById(R.id.remove_book);

        price = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);
        description = findViewById(R.id.detailed_dec);
        stock = findViewById(R.id.stock_db);


        Intent intent = getIntent();
        viewAllModel = (ViewAllModel)intent.getSerializableExtra("details");
        Log.d("OBJECT",String.valueOf(viewAllModel.getDescription()));

        if(viewAllModel != null){
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText("Price : "+viewAllModel.getPrice()+"€");
            stock.setText("Stock : "+viewAllModel.getAvailability());

            totalPrice = viewAllModel.getPrice() * totalQuantity;
        }

        addToCart = findViewById(R.id.add_to_cart);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });


        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < viewAllModel.getAvailability()){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice() * totalQuantity;
                }

            }
        });

        removeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity <= viewAllModel.getAvailability() && totalQuantity != 0){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice() * totalQuantity;
                }
            }
        });


    }

    private void addedToCart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("bookName", viewAllModel.getName());
        cartMap.put("bookPrice", price.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this,"Successfully added to cart!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

}