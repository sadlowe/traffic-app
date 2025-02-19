package com.tp.transport;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ResponsableActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    Button reportButton;
    ActivityResultLauncher<Intent> resultLauncher;
    ImageView back;
    private ImageView imageView;
    private Button pickImageButton;
    private Button userModeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responsable);

        BottomNavigationView nav = findViewById(R.id.bottomNav);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_signale) {
                    Intent intent = new Intent(ResponsableActivity.this, SignalementActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.nav_home) {
                    Intent intent = new Intent(ResponsableActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.nav_res) {
                    Intent intent = new Intent(ResponsableActivity.this, EspResActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        pickImageButton = findViewById(R.id.btnPick);
        imageView = findViewById(R.id.imageView);

        Button personalInfoButton = findViewById(R.id.personalInfoButton);
        personalInfoButton.setOnClickListener(v -> {
            String codeConfidentiel = getIntent().getStringExtra("codeConfidentiel");
            Intent intent = new Intent(ResponsableActivity.this, infoResEditActivity.class);
            intent.putExtra("codeConfidentiel", codeConfidentiel);
            startActivity(intent);
        });

        pickImageButton.setOnClickListener(v -> openGallery());

        reportButton = findViewById(R.id.reportButton);
        reportButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResponsableActivity.this, ResponsableSignalementsActivity.class);
            startActivity(intent);
        });

        back = findViewById(R.id.backres);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(ResponsableActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // New User Mode Button
        userModeButton = findViewById(R.id.userModeButton);
        userModeButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResponsableActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
