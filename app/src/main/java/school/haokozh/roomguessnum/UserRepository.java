package school.haokozh.roomguessnum;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class UserRepository {

  private UserDao userDao;
  private LiveData<List<User>> allUsersOrderByPlayTime;

  UserRepository(Application application) {
    UserRoomDatabase db = UserRoomDatabase.getInstance(application);
    userDao = db.userDao();
    allUsersOrderByPlayTime = userDao.getUsersOrderByPlayTime();
  }

  LiveData<List<User>> getAllUsersOrderByPlayTime() {
    return allUsersOrderByPlayTime;
  }

  void insert(User user) {
    UserRoomDatabase.databaseWriteExecutor.execute(() -> {
      userDao.insert(user);
    });
  }

}
