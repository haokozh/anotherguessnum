package school.haokozh.roomguessnum;

import android.content.Intent;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RankActivity extends AppCompatActivity {


  private Button buttonBack;
  private Button buttonQuery;
  private Button buttonDelete;

  private EditText idInput;

  private UserViewModel userViewModel;

  private UserListAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rank);

    buttonBack = findViewById(R.id.button_back);
    buttonQuery = findViewById(R.id.button_query);
    buttonDelete = findViewById(R.id.button_delete);
    idInput = findViewById(R.id.id_input);


    RecyclerView recyclerView = findViewById(R.id.recyclerview);
    adapter = new UserListAdapter(this);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    userViewModel.getAllUsersOrderByPlayTime().observe(this, adapter::setUsers);

    if (!TextUtils.isEmpty(idInput.getText())) {
      int queryId = Integer.parseInt(idInput.getText().toString());
      userViewModel.getUserById(queryId);
    }

    setOnClickListener();
  }

  private void setOnClickListener() {
    buttonBack.setOnClickListener(v -> {
      Intent intent = new Intent(RankActivity.this, MainActivity.class);
      startActivity(intent);
    });

    buttonQuery.setOnClickListener(v -> {
      if (TextUtils.isEmpty(idInput.getText())) return;
      if (TextUtils.equals(idInput.getText(), "all")) {
        userViewModel.getAllUsersOrderByPlayTime().observe(this, adapter::setUsers);
      }
      if (TextUtils.isDigitsOnly(idInput.getText())) {
        int queryId = Integer.parseInt(idInput.getText().toString());
        userViewModel.getUserById(queryId).observe(this, adapter::setUsers);
      } else {
        String userName = idInput.getText().toString();
        userViewModel.getUserByName(userName).observe(this, adapter::setUsers);
      }
      idInput.setText("");
    });

    buttonDelete.setOnClickListener(v -> {
      if (TextUtils.isEmpty(idInput.getText())) return;
      int queryId = Integer.parseInt(idInput.getText().toString());
      userViewModel.deleteById(queryId);
      idInput.setText("");
    });
  }
}