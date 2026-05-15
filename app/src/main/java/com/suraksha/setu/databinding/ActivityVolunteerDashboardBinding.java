package com.suraksha.setu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.suraksha.setu.R;

/* JADX INFO: loaded from: classes8.dex */
public final class ActivityVolunteerDashboardBinding implements ViewBinding {
    public final LinearLayout emptyVolState;
    private final CoordinatorLayout rootView;
    public final RecyclerView rvActiveEmergencies;
    public final MaterialSwitch switchAvailability;
    public final MaterialToolbar toolbarVol;
    public final TextView tvRank;
    public final TextView tvResolvedCount;
    public final TextView tvVolStatus;

    private ActivityVolunteerDashboardBinding(CoordinatorLayout rootView, LinearLayout emptyVolState, RecyclerView rvActiveEmergencies, MaterialSwitch switchAvailability, MaterialToolbar toolbarVol, TextView tvRank, TextView tvResolvedCount, TextView tvVolStatus) {
        this.rootView = rootView;
        this.emptyVolState = emptyVolState;
        this.rvActiveEmergencies = rvActiveEmergencies;
        this.switchAvailability = switchAvailability;
        this.toolbarVol = toolbarVol;
        this.tvRank = tvRank;
        this.tvResolvedCount = tvResolvedCount;
        this.tvVolStatus = tvVolStatus;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static ActivityVolunteerDashboardBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityVolunteerDashboardBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_volunteer_dashboard, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityVolunteerDashboardBinding bind(View rootView) {
        int id = R.id.emptyVolState;
        LinearLayout emptyVolState = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
        if (emptyVolState != null) {
            id = R.id.rvActiveEmergencies;
            RecyclerView rvActiveEmergencies = (RecyclerView) ViewBindings.findChildViewById(rootView, id);
            if (rvActiveEmergencies != null) {
                id = R.id.switchAvailability;
                MaterialSwitch switchAvailability = (MaterialSwitch) ViewBindings.findChildViewById(rootView, id);
                if (switchAvailability != null) {
                    id = R.id.toolbarVol;
                    MaterialToolbar toolbarVol = (MaterialToolbar) ViewBindings.findChildViewById(rootView, id);
                    if (toolbarVol != null) {
                        id = R.id.tvRank;
                        TextView tvRank = (TextView) ViewBindings.findChildViewById(rootView, id);
                        if (tvRank != null) {
                            id = R.id.tvResolvedCount;
                            TextView tvResolvedCount = (TextView) ViewBindings.findChildViewById(rootView, id);
                            if (tvResolvedCount != null) {
                                id = R.id.tvVolStatus;
                                TextView tvVolStatus = (TextView) ViewBindings.findChildViewById(rootView, id);
                                if (tvVolStatus != null) {
                                    return new ActivityVolunteerDashboardBinding((CoordinatorLayout) rootView, emptyVolState, rvActiveEmergencies, switchAvailability, toolbarVol, tvRank, tvResolvedCount, tvVolStatus);
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
