package school.haokozh.roomguessnum;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UserExecutors {

  private Executor executor;

  public UserExecutors() {
    executor = Executors.newSingleThreadExecutor();
  }

  public Executor getExecutor() {
    return executor;
  }
}
