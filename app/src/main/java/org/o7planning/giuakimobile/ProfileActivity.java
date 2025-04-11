package org.o7planning.giuakimobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewUsername;
    private Button buttonEdit;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setTitle("Profile");

        textViewUsername = findViewById(R.id.textViewUsername);
        buttonEdit = findViewById(R.id.buttonEdit);


        username = getIntent().getStringExtra("username");
        if (username != null) {
            textViewUsername.setText(username);
        }


        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditUserActivity.class);
                intent.putExtra("username", username);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Lấy username mới từ EditUserActivity
            username = data.getStringExtra("newUsername");
            textViewUsername.setText(username);
        }
    }
}