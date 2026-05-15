package com.suraksha.setu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.suraksha.setu.R;

/* JADX INFO: loaded from: classes8.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final MaterialButton btnSOS;
    public final MaterialCardView cardSafeCircle;
    public final MaterialCardView cardVolunteer;
    public final ImageView ivProfile;
    private final CoordinatorLayout rootView;
    public final MaterialToolbar toolbarMain;
    public final TextView tvLastUpdated;
    public final TextView tvLiveLocationStatus;
    public final TextView tvUserName;
    public final TextView tvVillage;

    private ActivityMainBinding(CoordinatorLayout rootView, MaterialButton btnSOS, MaterialCardView cardSafeCircle, MaterialCardView cardVolunteer, ImageView ivProfile, MaterialToolbar toolbarMain, TextView tvLastUpdated, TextView tvLiveLocationStatus, TextView tvUserName, TextView tvVillage) {
        this.rootView = rootView;
        this.btnSOS = btnSOS;
        this.cardSafeCircle = cardSafeCircle;
        this.cardVolunteer = cardVolunteer;
        this.ivProfile = ivProfile;
        this.toolbarMain = toolbarMain;
        this.tvLastUpdated = tvLastUpdated;
        this.tvLiveLocationStatus = tvLiveLocationStatus;
        this.tvUserName = tvUserName;
        this.tvVillage = tvVillage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_main, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityMainBinding bind(View rootView) {
        int id = R.id.btnSOS;
        MaterialButton btnSOS = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
        if (btnSOS != null) {
            id = R.id.cardSafeCircle;
            MaterialCardView cardSafeCircle = (MaterialCardView) ViewBindings.findChildViewById(rootView, id);
            if (cardSafeCircle != null) {
                id = R.id.cardVolunteer;
                MaterialCardView cardVolunteer = (MaterialCardView) ViewBindings.findChildViewById(rootView, id);
                if (cardVolunteer != null) {
                    id = R.id.ivProfile;
                    ImageView ivProfile = (ImageView) ViewBindings.findChildViewById(rootView, id);
                    if (ivProfile != null) {
                        id = R.id.toolbarMain;
                        MaterialToolbar toolbarMain = (MaterialToolbar) ViewBindings.findChildViewById(rootView, id);
                        if (toolbarMain != null) {
                            id = R.id.tvLastUpdated;
                            TextView tvLastUpdated = (TextView) ViewBindings.findChildViewById(rootView, id);
                            if (tvLastUpdated != null) {
                                id = R.id.tvLiveLocationStatus;
                                TextView tvLiveLocationStatus = (TextView) ViewBindings.findChildViewById(rootView, id);
                                if (tvLiveLocationStatus != null) {
                                    id = R.id.tvUserName;
                                    TextView tvUserName = (TextView) ViewBindings.findChildViewById(rootView, id);
                                    if (tvUserName != null) {
                                        id = R.id.tvVillage;
                                        TextView tvVillage = (TextView) ViewBindings.findChildViewById(rootView, id);
                                        if (tvVillage != null) {
                                            return new ActivityMainBinding((CoordinatorLayout) rootView, btnSOS, cardSafeCircle, cardVolunteer, ivProfile, toolbarMain, tvLastUpdated, tvLiveLocationStatus, tvUserName, tvVillage);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
