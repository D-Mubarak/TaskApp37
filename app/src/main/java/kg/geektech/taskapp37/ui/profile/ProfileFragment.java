package kg.geektech.taskapp37.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kg.geektech.taskapp37.databinding.FragmentNewsBinding;
import kg.geektech.taskapp37.databinding.FragmentProfileBinding;
import kg.geektech.taskapp37.ui.notifications.NotificationsViewModel;

public class ProfileFragment extends Fragment {

    private static final String RECEIVER_KEY = "key1";
    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    binding.photo.setImageURI(uri);
                }
            });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.photo.setOnClickListener(view1 -> {
            putPhoto();

        });
    }

    public void putPhoto(){

        mGetContent.launch("image/*");
    }
}