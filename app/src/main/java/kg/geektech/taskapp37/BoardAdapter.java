package kg.geektech.taskapp37;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kg.geektech.taskapp37.databinding.ItemBoardBinding;
import kg.geektech.taskapp37.interfaces.OnStartClickListener;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private ItemBoardBinding binding;
    private String[] titles = new String[]{"gh", "gt", "gy"};
    //private Uri[] uris =
    private OnStartClickListener clickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public void setClickListener(OnStartClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView skip;

        public ViewHolder(@NonNull ItemBoardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick();
                }
            });
        }

        public void bind(int position) {
            binding.textTitle.setText(titles[position]);
            if (position == titles.length - 1) {
                binding.btnStart.setVisibility(View.VISIBLE);
                skip.setVisibility(View.VISIBLE);
            } else {
                binding.btnStart.setVisibility(View.INVISIBLE);
                skip.setVisibility(View.INVISIBLE);
            }
        }
    }
}
