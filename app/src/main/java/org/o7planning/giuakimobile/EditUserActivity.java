package org.o7planning.giuakimobile;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditUserActivity extends AppCompatActivity {

    private EditText editTextNewUsername;
    private Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        setTitle("Edit Profile");

        editTextNewUsername = findViewById(R.id.editTextNewUsername);
        buttonDone = findViewById(R.id.buttonDone);


        String currentUsername = getIntent().getStringExtra("username");
        if (currentUsername != null) {
            editTextNewUsername.setText(currentUsername);
        }


        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = editTextNewUsername.getText().toString().trim();
                if (newUsername.isEmpty()) {
                    Toast.makeText(EditUserActivity.this, "Vui lòng nhập username mới!", Toast.LENGTH_SHORT).show();
                } else {

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newUsername", newUsername);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}