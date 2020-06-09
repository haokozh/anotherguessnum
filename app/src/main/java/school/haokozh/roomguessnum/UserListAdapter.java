package school.haokozh.roomguessnum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

  static class UserViewHolder extends RecyclerView.ViewHolder {
    private final TextView itemView;

    private UserViewHolder(View itemView) {
      super(itemView);
      this.itemView = itemView.findViewById(R.id.textView);
    }
  }

  private final LayoutInflater inflater;
  private List<User> users;

  UserListAdapter(Context context) {
    inflater = LayoutInflater.from(context);
  }

  void setUsers(List<User> users) {
    this.users = users;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public UserListAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
    return new UserViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull UserListAdapter.UserViewHolder holder, int position) {
    if (users != null) {
      User current = users.get(position);
      String result =
          "Id : " + current.getId() + "\n" +
          "Username : " + current.getUserName() + "\n" +
          "Play Time : " + current.getPlayTime() + "\n" +
          "Guess Count : " + current.getGuessCount();

      holder.itemView.setText(result);
    } else {
      holder.itemView.setText(R.string.no_user);
    }
  }

  @Override
  public int getItemCount() {
    if (users != null) {
      return users.size();
    } else {
      return 0;
    }
  }
}
