package com.discord.widgets.user.profile;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.discord.databinding.UserProfileHeaderViewBinding;

public class UserProfileHeaderView extends ConstraintLayout {
    public String bannerUrl;

    public UserProfileHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public static UserProfileHeaderViewBinding access$getBinding$p(UserProfileHeaderView instance) {
        return null;
    }
}
