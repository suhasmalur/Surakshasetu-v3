package com.suraksha.setu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.suraksha.setu.R;

/* JADX INFO: loaded from: classes8.dex */
public final class ActivityEmergencyMapBinding implements ViewBinding {
    public final MaterialCardView bottomSheet;
    public final MaterialButton btnMarkResolved;
    public final MaterialButton btnNavigate;
    public final FragmentContainerView map;
    private final CoordinatorLayout rootView;
    public final TextView tvMapDistance;
    public final TextView tvMapVictimName;

    private ActivityEmergencyMapBinding(CoordinatorLayout rootView, MaterialCardView bottomSheet, MaterialButton btnMarkResolved, MaterialButton btnNavigate, FragmentContainerView map, TextView tvMapDistance, TextView tvMapVictimName) {
        this.rootView = rootView;
        this.bottomSheet = bottomSheet;
        this.btnMarkResolved = btnMarkResolved;
        this.btnNavigate = btnNavigate;
        this.map = map;
        this.tvMapDistance = tvMapDistance;
        this.tvMapVictimName = tvMapVictimName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static ActivityEmergencyMapBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityEmergencyMapBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_emergency_map, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityEmergencyMapBinding bind(View rootView) {
        int id = R.id.bottomSheet;
        MaterialCardView bottomSheet = (MaterialCardView) ViewBindings.findChildViewById(rootView, id);
        if (bottomSheet != null) {
            id = R.id.btnMarkResolved;
            MaterialButton btnMarkResolved = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
            if (btnMarkResolved != null) {
                id = R.id.btnNavigate;
                MaterialButton btnNavigate = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
                if (btnNavigate != null) {
                    id = R.id.map;
                    FragmentContainerView map = (FragmentContainerView) ViewBindings.findChildViewById(rootView, id);
                    if (map != null) {
                        id = R.id.tvMapDistance;
                        TextView tvMapDistance = (TextView) ViewBindings.findChildViewById(rootView, id);
                        if (tvMapDistance != null) {
                            id = R.id.tvMapVictimName;
                            TextView tvMapVictimName = (TextView) ViewBindings.findChildViewById(rootView, id);
                            if (tvMapVictimName != null) {
                                return new ActivityEmergencyMapBinding((CoordinatorLayout) rootView, bottomSheet, btnMarkResolved, btnNavigate, map, tvMapDistance, tvMapVictimName);
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
