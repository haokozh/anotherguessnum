package school.haokozh.roomguessnum;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {

  public abstract UserDao userDao();

  private static volatile UserRoomDatabase INSTANCE;
  private static final int NUMBER_OF_THREADS = 4;
  static final ExecutorService databaseWriteExecutor =
      Executors.newFixedThreadPool(NUMBER_OF_THREADS);

  static UserRoomDatabase getInstance(final Context context) {
    if (INSTANCE == null) {
      synchronized (UserRoomDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              UserRoomDatabase.class, "user_database")
              .addCallback(databaseCallback)
              .build();
        }
      }
    }
    return INSTANCE;
  }

  private static RoomDatabase.Callback databaseCallback = new RoomDatabase.Callback() {
    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
      databaseWriteExecutor.execute(() -> {
        UserDao dao = INSTANCE.userDao();
        dao.deleteAll();
      });
    }
  };
}
