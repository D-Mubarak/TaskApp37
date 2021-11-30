package kg.geektech.taskapp37.ui.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import kg.geektech.taskapp37.R;
import kg.geektech.taskapp37.databinding.FragmentNewsBinding;
import kg.geektech.taskapp37.databinding.FragmentProfileBinding;
import kg.geektech.taskapp37.ui.notifications.NotificationsViewModel;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private boolean proverka = false;
    private boolean proverka2 = true;

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    if (uri == null){
                        proverka = false;
                    }else {
                        binding.photo.setImageURI(uri);
                        proverka2 = true;
                    }
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

            putPhoto();
    }

    public void putPhoto(){
    binding.photo.setOnClickListener(view1 -> {
        if (proverka && proverka2) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());
            alertDialog.setMessage("Do you want to delete?");
            alertDialog.setNeutralButton("Заменить", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mGetContent.launch("image/*");
                }
            });
            alertDialog.setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    binding.photo.setImageResource(R.drawable.ic_person);
                    proverka2 = false;
                }
            });
            AlertDialog dialog =alertDialog.create();
            dialog.show();
        } else {
            mGetContent.launch("image/*");
            proverka = true;

        }
    });
    }
}