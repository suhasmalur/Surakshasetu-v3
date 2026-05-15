package com.suraksha.setu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.suraksha.setu.R;

/* JADX INFO: loaded from: classes8.dex */
public final class ActivitySplashBinding implements ViewBinding {
    public final ImageView ivLogo;
    private final RelativeLayout rootView;
    public final TextView tvAppName;
    public final TextView tvSubtitle;

    private ActivitySplashBinding(RelativeLayout rootView, ImageView ivLogo, TextView tvAppName, TextView tvSubtitle) {
        this.rootView = rootView;
        this.ivLogo = ivLogo;
        this.tvAppName = tvAppName;
        this.tvSubtitle = tvSubtitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySplashBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySplashBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_splash, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivitySplashBinding bind(View rootView) {
        int id = R.id.ivLogo;
        ImageView ivLogo = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (ivLogo != null) {
            id = R.id.tvAppName;
            TextView tvAppName = (TextView) ViewBindings.findChildViewById(rootView, id);
            if (tvAppName != null) {
                id = R.id.tvSubtitle;
                TextView tvSubtitle = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (tvSubtitle != null) {
                    return new ActivitySplashBinding((RelativeLayout) rootView, ivLogo, tvAppName, tvSubtitle);
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
