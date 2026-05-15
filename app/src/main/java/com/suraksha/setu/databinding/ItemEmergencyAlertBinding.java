package com.suraksha.setu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.suraksha.setu.R;

/* JADX INFO: loaded from: classes8.dex */
public final class ItemEmergencyAlertBinding implements ViewBinding {
    public final MaterialButton btnAccept;
    public final MaterialButton btnViewMap;
    private final MaterialCardView rootView;
    public final TextView tvDistance;
    public final TextView tvEmergencyTime;
    public final TextView tvVictimName;

    private ItemEmergencyAlertBinding(MaterialCardView rootView, MaterialButton btnAccept, MaterialButton btnViewMap, TextView tvDistance, TextView tvEmergencyTime, TextView tvVictimName) {
        this.rootView = rootView;
        this.btnAccept = btnAccept;
        this.btnViewMap = btnViewMap;
        this.tvDistance = tvDistance;
        this.tvEmergencyTime = tvEmergencyTime;
        this.tvVictimName = tvVictimName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public MaterialCardView getRoot() {
        return this.rootView;
    }

    public static ItemEmergencyAlertBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemEmergencyAlertBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_emergency_alert, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemEmergencyAlertBinding bind(View rootView) {
        int id = R.id.btnAccept;
        MaterialButton btnAccept = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
        if (btnAccept != null) {
            id = R.id.btnViewMap;
            MaterialButton btnViewMap = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
            if (btnViewMap != null) {
                id = R.id.tvDistance;
                TextView tvDistance = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (tvDistance != null) {
                    id = R.id.tvEmergencyTime;
                    TextView tvEmergencyTime = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (tvEmergencyTime != null) {
                        id = R.id.tvVictimName;
                        TextView tvVictimName = (TextView) ViewBindings.findChildViewById(rootView, id);
                        if (tvVictimName != null) {
                            return new ItemEmergencyAlertBinding((MaterialCardView) rootView, btnAccept, btnViewMap, tvDistance, tvEmergencyTime, tvVictimName);
                        }
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
