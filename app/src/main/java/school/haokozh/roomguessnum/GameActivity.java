package school.haokozh.roomguessnum;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameActivity extends AppCompatActivity {

  private int countA;
  private long startTime;
  private int guessCount;

  private EditText output;
  private EditText input;
  private Intent intent;

  String userName;
  long playTime;

  private Random random = new Random();
  private Set<Integer> set = new HashSet<>();
  private List<Integer> randList = new ArrayList<>(4);
  private StringBuilder builder = new StringBuilder();

  private UserViewModel userViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    setContentView(R.layout.activity_game);

    TextView userNameView = findViewById(R.id.user_name_display);
    intent = this.getIntent();
    String userNameDisplay =
        userNameView.getText().toString() +
        intent.getStringExtra("user_name");
    userNameView.setText(userNameDisplay);

    output = findViewById(R.id.output);
    input = findViewById(R.id.user_input);
    Button enter = findViewById(R.id.button_enter);

    output.setMovementMethod(new ScrollingMovementMethod());
    output.setVerticalScrollBarEnabled(true);

    enter.setOnClickListener(this::printResult);
    getRandomNumber();

    userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    userViewModel.insert(new User(userName, playTime, guessCount));

    startTime = System.currentTimeMillis();
  }

  private void getRandomNumber() {
    while (randList.size() < 4) {
      int randNum = random.nextInt(10);
      if (set.add(randNum)) randList.add(randNum);
    }
  }

  private String printRandom() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < randList.size(); i++) {
      result.append(randList.get(i));
    }
    return result.toString();
  }

  private String guess(String num) {
    countA = 0;
    int countB = 0;
    boolean[] check = new boolean[4];
    List<Integer> guessList = new ArrayList<>(4);

    for (int i = 0; i < 4; i++) {
      guessList.add(Character.getNumericValue(num.charAt(i)));
    }

    for (int i = 0; i < 4; i++) {
      if (randList.get(i).equals(guessList.get(i))) {
        check[i] = true;
        countA++;
      }
    }

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (randList.get(i).equals(guessList.get(j)) && !check[j]) {
          countB++;
        }
      }
    }

    return countA + "A" + countB + "B";
  }

  private void printResult(View view) {
    String userInput = input.getText().toString();
    if (TextUtils.isDigitsOnly(input.getText()) && userInput.length() != 4) {
      output.setText(builder.append("Please enter 4 number.\n").toString());
    } else {
      builder.append(userInput)
          .append(" => ")
          .append(guess(userInput))
          .append("\t")
          .append("Ans : ")
          .append(printRandom())
          .append("\n");

      guessCount++;

      if (countA == 4) {
        long endTime = System.currentTimeMillis();
        playTime = (endTime - startTime) / 1000;
        builder.append("Done.\n")
            .append("Time : ")
            .append(playTime)
            .append(" seconds.\n")
            .append("Count : ")
            .append(guessCount);

        userName = getIntent().getStringExtra("user_name");
        assert userName != null;
        userViewModel.insert(new User(userName, playTime, guessCount));

        intent.setClass(GameActivity.this, RankActivity.class);
        startActivity(intent);
      }

      output.setText(builder.toString());
    }

    input.setText("");
  }
}