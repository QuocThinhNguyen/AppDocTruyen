package vn.iotstar.appdoctruyen.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.iotstar.appdoctruyen.ChapterFragment;
import vn.iotstar.appdoctruyen.ChiTietTruyenFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public  FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle){
        super(fragmentManager,lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 1){
            return new ChapterFragment();
        }
        return new ChiTietTruyenFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
