package com.suraksha.setu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import com.suraksha.setu.R;

/* JADX INFO: loaded from: classes8.dex */
public final class ItemSafeCircleMemberBinding implements ViewBinding {
    public final ImageButton btnCall;
    public final ImageButton btnDelete;
    public final View priorityIndicator;
    private final MaterialCardView rootView;
    public final TextView tvMemberName;
    public final TextView tvPhoneNumber;
    public final TextView tvRelationship;

    private ItemSafeCircleMemberBinding(MaterialCardView rootView, ImageButton btnCall, ImageButton btnDelete, View priorityIndicator, TextView tvMemberName, TextView tvPhoneNumber, TextView tvRelationship) {
        this.rootView = rootView;
        this.btnCall = btnCall;
        this.btnDelete = btnDelete;
        this.priorityIndicator = priorityIndicator;
        this.tvMemberName = tvMemberName;
        this.tvPhoneNumber = tvPhoneNumber;
        this.tvRelationship = tvRelationship;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static ItemSafeCircleMemberBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemSafeCircleMemberBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_safe_circle_member, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemSafeCircleMemberBinding bind(View rootView) {
        View priorityIndicator;
        int id = R.id.btnCall;
        ImageButton btnCall = (ImageButton) ViewBindings.findChildViewById(rootView, id);
        if (btnCall != null) {
            id = R.id.btnDelete;
            ImageButton btnDelete = (ImageButton) ViewBindings.findChildViewById(rootView, id);
            if (btnDelete != null && (priorityIndicator = ViewBindings.findChildViewById(rootView, (id = R.id.priorityIndicator))) != null) {
                id = R.id.tvMemberName;
                TextView tvMemberName = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (tvMemberName != null) {
                    id = R.id.tvPhoneNumber;
                    TextView tvPhoneNumber = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (tvPhoneNumber != null) {
                        id = R.id.tvRelationship;
                        TextView tvRelationship = (TextView) ViewBindings.findChildViewById(rootView, id);
                        if (tvRelationship != null) {
                            return new ItemSafeCircleMemberBinding((MaterialCardView) rootView, btnCall, btnDelete, priorityIndicator, tvMemberName, tvPhoneNumber, tvRelationship);
                        }
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
