package school.haokozh.roomguessnum;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class UserViewModel extends AndroidViewModel {

  private UserRepository repository;
  private LiveData<List<User>> allUsersOrderByPlayTime;

  public UserViewModel(Application application) {
    super(application);
    repository = new UserRepository(application);
    allUsersOrderByPlayTime = repository.getAllUsersOrderByPlayTime();
  }

  LiveData<List<User>> getAllUsersOrderByPlayTime() {
    return allUsersOrderByPlayTime;
  }

  public void insert(User user) {
    repository.insert(user);
  }
}
