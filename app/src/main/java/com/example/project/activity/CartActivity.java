package com.example.project.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.project.Adapter.CartAdapter;
import com.example.project.Helper.ChangeNumberItemsListener;
import com.example.project.Helper.ManagmentCart;
import com.example.project.R;
import com.example.project.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
    private ManagmentCart managmentCart;
    ActivityCartBinding binding;
    double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managmentCart=new ManagmentCart(this);
        setVariable();
        initlist();
        calculatorCart();
        statusBarColor();
    }

    private void statusBarColor() {
        Window window=CartActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(CartActivity.this,R.color.lavender));
    }

    private void initlist() {
        if(managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scroll.setVisibility(View.GONE);
        }else{
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scroll.setVisibility(View.VISIBLE);
        }

        binding.cartView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.cartView.setAdapter(new CartAdapter(managmentCart.getListCart(), () -> calculatorCart()));

        binding.orderBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Order Sent", Toast.LENGTH_SHORT).show();
        });
    }
    private void calculatorCart(){
        double percentTax=0.2;
        double delivery= 20;
        tax=Math.round(managmentCart.getTotalFee()* percentTax *100) / 100;

        double total =Math.round((managmentCart.getTotalFee()+tax+delivery) * 100) / 100;
        double itemTotal= Math.round(managmentCart.getTotalFee()*100)/100;
        binding.totalFeeTxt.setText("$"+itemTotal);
        binding.taxTxt.setText("$"+tax);
        binding.deliveryTxt.setText("$"+delivery);
        binding.totalTxt.setText("$"+total);
    }
    private void setVariable() {
        binding.backBtn.setOnClickListener(view -> finish());}
}