package harish.mvvmexample.ui.test;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import harish.mvvmexample.R;
import harish.mvvmexample.base.MyBaseFragment;

public class DetailsFragment extends MyBaseFragment {

    @BindView(R.id.img)
    ImageView img;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_details;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle b = getArguments();
        if (b != null) {
            String transitionName = b.getString("transitionName");
            img.setTransitionName(transitionName);
            Glide
                    .with(getContext())
                    .load("https://avatars3.githubusercontent.com/u/439362")
                    .placeholder(R.mipmap.ic_launcher)
                    .into(img);
        }
    }
}
