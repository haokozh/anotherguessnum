package school.haokozh.roomguessnum;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_id")
  private int id;

  @NonNull
  @ColumnInfo(name = "user_name")
  private String userName;

  @ColumnInfo(name = "play_time")
  private long playTime;

  @ColumnInfo(name = "guess_count")
  private int guessCount;

  public User(@NonNull String userName, long playTime, int guessCount) {
    this.userName = userName;
    this.playTime = playTime;
    this.guessCount = guessCount;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @NonNull
  public String getUserName() {
    return this.userName;
  }

  public long getPlayTime() {
    return this.playTime;
  }

  public int getGuessCount() {
    return this.guessCount;
  }
}
