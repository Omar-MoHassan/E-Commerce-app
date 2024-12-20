package com.example.project.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.project.Adapter.PopularAdapter;
import com.example.project.R;
import com.example.project.databinding.ActivityMainBinding;
import com.example.project.domain.PopularDomain;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SharedPreferences sharedPreferences;
    final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    final String USERS = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TextView name = findViewById(R.id.mainuserTxt);
        TextView signout = findViewById(R.id.signOut);

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);

        Gson gson = new Gson();

        String jsonText = sharedPreferences.getString(USERS, null);
        HashMap<String, Object> person = gson.fromJson(jsonText,HashMap.class);


        statusBarColor();
        initRecyclerView();
        bottomNavigation();

        name.setText(person.get("name").toString().trim());

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                HashMap<String, Object> person = new HashMap<String, Object>();
                person.put("name","");
                person.put("email", "");
                person.put("username", "");
                person.put("password", "");
                Gson gson = new Gson();
                String jsonList = gson.toJson(person);
                editor.putString(USERS,jsonList);
                editor.apply();
                Intent intent = new Intent(MainActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void bottomNavigation() {
        binding.CartBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
    }

    private void statusBarColor() {
        Window window = MainActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.lavender));
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("T-shirt black", "item_1", 15, 4, 22,
                "The T-shirts Are Always One Of These Pieces Of Clothes\n" +
                        "That You Will Find Yourself To Wear Over And Again,\n" +
                        "They Are Perfectly Suitable With Anything In Your Closet,\n" +
                        "You Can Wear It On Jeans, Casual Pants, Sweatpants And Jeans Pants.\n" +
                        "It can be simple, or you can make it more attractive.\n" +
                        "Just dress up with the right accessory"));

        items.add(new PopularDomain("Smart Watch", "item_2", 19, 4.5, 400,
                "Model: Pop 3S\n" +
                        "Color: Metallic Black\n" +
                        "Display: 1.96 HD AMOLED Display\n" +
                        "Design: Stainless Steel frame , Tempered Glass Protection.\n" +
                        "Water Resistance: IP68 Water-resistance\n" +
                        "Bluetooth Calling & AI Voice Assistant\n" +
                        "24H Heart Rate, SpO2 & Stress Monitoring\n" +
                        "100+ Sports Modes\n" +
                        "12-day Battery Life\n" +
                        "100+ Vibrant Watch Faces & Music Control"));

        items.add(new PopularDomain("Phone", "item_3", 3, 4.9, 350,
                "Brand\n" +
                        "    Nothing\n" +
                        "Model\n" +
                        "    Phone 2A\n" +
                        "Display\n" +

                        "    6.7 Inch, AMOLED, 1B colors, 120Hz, HDR10+, 700 nits (typ), 1100 nits (HBM), 1300 nits (peak), (1080 x 2412)\n" +
                        "Processor\n" +

                        "    Mediatek Dimensity 7200 Pro (4 nm),Octa-core (2x2.8 GHz Cortex-A715 & 6x 2.0 Cortex-A510)\n" +
                        "Storage Capacity\n" +

                        "    256GB\n" +
                        "RAM\n" +

                        "    12GB\n" +
                        "Rear Camera\n" +

                        "    -Dual Camera\n" +

                        "    - 50 MP, f/1.9, 24mm (wide), 1/1.56\", 1.0µm, PDAF, OIS\n" +

                        "    - 50 MP, f/2.2, 114˚ (ultrawide), 1/2.76\", 0.64µm\n" +

                        "    - Features: LED flash, panorama, HDR\n" +

                        "    - Video: 4K@30fps, 1080p@60/120fps, gyro-EIS\n" +
                        "Front Camera\n" +

                        "    - 32 MP, f/2.2, (wide), 1/2.74\", 0.8µm\n" +

                        "    - Features: HDR\n" +

                        "    - Video: 1080p@60fps\n" +
                        "Battery Capacity\n" +

                        "    5000 MAH, 45W, 50% in 23 min, 100% in 1 hour\n" +
                        "Number of SIM Cards\n" +

                        "    Dual SIM\n" +
                        "Connectivity\n" +

                        "    - Wi-Fi 802.11 a/b/g/n/ac/6, dual-band, Wi-Fi Direct\n" +

                        "    - Bluetooth 5.3, A2DP, LE\n" +

                        "    - Positioning: GPS, GALILEO, GLONASS, BDS, QZSS\n" +

                        "    - USB Type-C 2.0, OTG\n" +
                        "Color\n" +

                        "    Black\n" +
                        "Other Features\n" +

                        "    - Water Resistance: IP54 - splash, water and dust resistant\n" +

                        "    - Stereo speakers\n" +

                        "    - Sensors: Fingerprint (under display ), accelerometer, gyro, proximity, compass\n"));


        binding.PopularView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.PopularView.setAdapter(new PopularAdapter(items));
    }
}