package school.haokozh.roomguessnum;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface UserDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  void insert(User user);

  @Query("DELETE FROM user_table")
  void deleteAll();

  @Query("SELECT * FROM user_table ORDER BY play_time ASC")
  LiveData<List<User>> getUsersOrderByPlayTime();
}