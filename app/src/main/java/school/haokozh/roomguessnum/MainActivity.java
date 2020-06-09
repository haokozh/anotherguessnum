package school.haokozh.roomguessnum;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  private EditText userNameInput;
  private Button buttonEnter;
  private Button buttonExit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    userNameInput = findViewById(R.id.user_name_input);
    buttonEnter = findViewById(R.id.button_enter);
    buttonExit = findViewById(R.id.button_exit);

    setOnClickListener();
  }

  private void setOnClickListener() {
    buttonEnter.setOnClickListener(v -> {
      String userNameResult;

      if (TextUtils.isEmpty(userNameInput.getText())) {
        userNameResult = "Anonymous";
      } else {
        userNameResult = userNameInput.getText().toString();
      }

      Intent intent = new Intent(MainActivity.this, GameActivity.class);
      intent.putExtra("user_name", userNameResult);
      startActivity(intent);
    });

    buttonExit.setOnClickListener(v -> {
      AlertDialog.Builder alertDialogBuilder = new Builder(this);

      alertDialogBuilder.setTitle("Exit");

      alertDialogBuilder
          .setMessage("Exit?")
          .setCancelable(false)
          .setPositiveButton("Yes",
              (dialog, which) -> {
                finishAffinity();
                System.exit(0);
              })
          .setNeutralButton("Cancel",
              ((dialog, which) -> {
                dialog.cancel();
              }))
          .setNegativeButton("No",
              ((dialog, which) -> {
                dialog.cancel();
              }));

      AlertDialog alertDialog = alertDialogBuilder.create();
      alertDialog.show();
    });
  }
}