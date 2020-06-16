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

  LiveData<List<User>> getUserById(int id) {
    return repository.getUserById(id);
  }

  public void insert(User user) {
    repository.insert(user);
  }

  void deleteById(int id) {
    repository.deleteById(id);
  }
}
