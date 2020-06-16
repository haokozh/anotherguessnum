package school.haokozh.roomguessnum;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class UserRepository {

  private UserDao userDao;
  private LiveData<List<User>> allUsersOrderByPlayTime;
  private LiveData<List<User>> allUserById;

  UserRepository(Application application) {
    UserRoomDatabase db = UserRoomDatabase.getInstance(application);
    userDao = db.userDao();
    allUsersOrderByPlayTime = userDao.getUsersOrderByPlayTime();
  }

  LiveData<List<User>> getAllUsersOrderByPlayTime() {
    return allUsersOrderByPlayTime;
  }

  LiveData<List<User>> getUserById(int id) {
    return userDao.getUserById(id);
  }

  void insert(User user) {
    UserRoomDatabase.databaseWriteExecutor.execute(() -> {
      userDao.insert(user);
    });
  }

  void deleteById(int id) {
    UserRoomDatabase.databaseWriteExecutor.execute(() -> {
      userDao.deleteById(id);
    });
  }

}
