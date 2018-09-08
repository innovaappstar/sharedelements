package com.lgvalle.material_animations;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;

import com.lgvalle.material_animations.databinding.ActivitySharedelementBinding;

public class SharedElementActivity extends BaseDetailActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Sample sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        bindData(sample);
        setupWindowAnimations();
        setupLayout(sample);
        setupToolbar();
    }

    private void bindData(Sample sample) {
        ActivitySharedelementBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sharedelement);
        binding.setSharedSample(sample);
    }

    private void setupWindowAnimations() {
        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_long));
    }

    private void setupLayout(Sample sample) {
        // Transition for fragment1
        Slide slideTransition = new Slide(Gravity.LEFT);    // nativa de api de android...
//        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        slideTransition.setDuration(100);
        // Create fragment and define some of it transitions
        SharedElementFragment1 sharedElementFragment1 = SharedElementFragment1.newInstance(sample);
        sharedElementFragment1.setReenterTransition(slideTransition);   // Sets the Transition that will be used to move Views in to the scene when returning due to popping a back stack
        sharedElementFragment1.setExitTransition(slideTransition);  //Sets the Transition that will be used to move Views out of the scene when the fragment is removed, hidden, or detached when not popping the back stack.
        sharedElementFragment1.setSharedElementEnterTransition(new ChangeBounds()); // set transici[on   that will be used for shared elements transferred into the content Scene.

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, sharedElementFragment1)
                .commit();
    }
}
