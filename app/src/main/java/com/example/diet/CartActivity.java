package com.example.diet;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diet.cart.dto.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button checkoutButton;
    private TextView discountCode;
    private Button addCodeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerView);
        checkoutButton = findViewById(R.id.checkoutButton);
        discountCode = findViewById(R.id.discountCode);
        addCodeButton = findViewById(R.id.addCodeButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CartAdapter adapter = new CartAdapter(getCartItems());
        recyclerView.setAdapter(adapter);

        checkoutButton.setOnClickListener(v -> {
            // Handle checkout logic
        });

        addCodeButton.setOnClickListener(v -> {
            // Handle discount code application
        });
    }

    private List<CartItem> getCartItems() {
        // Fetch cart items from the data source
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem("OstroVit ZMAdvanced", 100000, 1));
        cartItems.add(new CartItem("OstroVit ZMAdvanced", 100000, 2));
        cartItems.add(new CartItem("OstroVit ZMAdvanced", 100000, 1));
        return cartItems;
    }
}
