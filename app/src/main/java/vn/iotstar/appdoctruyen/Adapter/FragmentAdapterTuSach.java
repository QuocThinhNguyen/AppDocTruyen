package vn.iotstar.appdoctruyen.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.iotstar.appdoctruyen.GoiYTruyenFragment;
import vn.iotstar.appdoctruyen.LichSuDocFragment;

public class FragmentAdapterTuSach extends FragmentStateAdapter {

    public FragmentAdapterTuSach(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle){
        super(fragmentManager,lifecycle);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 1:
                return new GoiYTruyenFragment();
        }
        return new LichSuDocFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
