package com.suraksha.setu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.suraksha.setu.R;

/* JADX INFO: loaded from: classes8.dex */
public final class ActivitySafeCircleBinding implements ViewBinding {
    public final LinearLayout emptyState;
    public final TextInputEditText etSearch;
    public final FloatingActionButton fabAddMember;
    private final CoordinatorLayout rootView;
    public final RecyclerView rvMembers;
    public final TextInputLayout searchLayout;
    public final MaterialToolbar toolbar;
    public final MaterialCardView warningCard;

    private ActivitySafeCircleBinding(CoordinatorLayout rootView, LinearLayout emptyState, TextInputEditText etSearch, FloatingActionButton fabAddMember, RecyclerView rvMembers, TextInputLayout searchLayout, MaterialToolbar toolbar, MaterialCardView warningCard) {
        this.rootView = rootView;
        this.emptyState = emptyState;
        this.etSearch = etSearch;
        this.fabAddMember = fabAddMember;
        this.rvMembers = rvMembers;
        this.searchLayout = searchLayout;
        this.toolbar = toolbar;
        this.warningCard = warningCard;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static ActivitySafeCircleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySafeCircleBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_safe_circle, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivitySafeCircleBinding bind(View rootView) {
        int id = R.id.emptyState;
        LinearLayout emptyState = (LinearLayout) ViewBindings.findChildViewById(rootView, id);
        if (emptyState != null) {
            id = R.id.etSearch;
            TextInputEditText etSearch = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
            if (etSearch != null) {
                id = R.id.fabAddMember;
                FloatingActionButton fabAddMember = (FloatingActionButton) ViewBindings.findChildViewById(rootView, id);
                if (fabAddMember != null) {
                    id = R.id.rvMembers;
                    RecyclerView rvMembers = (RecyclerView) ViewBindings.findChildViewById(rootView, id);
                    if (rvMembers != null) {
                        id = R.id.searchLayout;
                        TextInputLayout searchLayout = (TextInputLayout) ViewBindings.findChildViewById(rootView, id);
                        if (searchLayout != null) {
                            id = R.id.toolbar;
                            MaterialToolbar toolbar = (MaterialToolbar) ViewBindings.findChildViewById(rootView, id);
                            if (toolbar != null) {
                                id = R.id.warningCard;
                                MaterialCardView warningCard = (MaterialCardView) ViewBindings.findChildViewById(rootView, id);
                                if (warningCard != null) {
                                    return new ActivitySafeCircleBinding((CoordinatorLayout) rootView, emptyState, etSearch, fabAddMember, rvMembers, searchLayout, toolbar, warningCard);
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
