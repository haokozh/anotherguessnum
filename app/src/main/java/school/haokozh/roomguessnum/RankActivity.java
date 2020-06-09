package school.haokozh.roomguessnum;

import android.content.Intent;
import android.net.Uri.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RankActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rank);

    RecyclerView recyclerView = findViewById(R.id.recyclerview);
    final UserListAdapter adapter = new UserListAdapter(this);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    userViewModel.getAllUsersOrderByPlayTime().observe(this, adapter::setUsers);

    Button buttonBack = findViewById(R.id.button_back);
    buttonBack.setOnClickListener(v -> {
      Intent intent = new Intent(RankActivity.this, MainActivity.class);
      startActivity(intent);
    });
  }
}