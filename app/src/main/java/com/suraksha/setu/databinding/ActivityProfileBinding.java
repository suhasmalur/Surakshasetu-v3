package com.suraksha.setu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.suraksha.setu.R;

/* JADX INFO: loaded from: classes8.dex */
public final class ActivityProfileBinding implements ViewBinding {
    public final MaterialButton btnChangePic;
    public final MaterialButton btnLogout;
    public final MaterialButton btnUpdateProfile;
    public final TextInputEditText etProfileName;
    public final TextInputEditText etProfilePhone;
    public final TextInputEditText etProfileVillage;
    public final ImageView ivProfilePic;
    private final LinearLayout rootView;
    public final MaterialToolbar toolbarProfile;

    private ActivityProfileBinding(LinearLayout rootView, MaterialButton btnChangePic, MaterialButton btnLogout, MaterialButton btnUpdateProfile, TextInputEditText etProfileName, TextInputEditText etProfilePhone, TextInputEditText etProfileVillage, ImageView ivProfilePic, MaterialToolbar toolbarProfile) {
        this.rootView = rootView;
        this.btnChangePic = btnChangePic;
        this.btnLogout = btnLogout;
        this.btnUpdateProfile = btnUpdateProfile;
        this.etProfileName = etProfileName;
        this.etProfilePhone = etProfilePhone;
        this.etProfileVillage = etProfileVillage;
        this.ivProfilePic = ivProfilePic;
        this.toolbarProfile = toolbarProfile;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityProfileBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityProfileBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_profile, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityProfileBinding bind(View rootView) {
        int id = R.id.btnChangePic;
        MaterialButton btnChangePic = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
        if (btnChangePic != null) {
            id = R.id.btnLogout;
            MaterialButton btnLogout = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
            if (btnLogout != null) {
                id = R.id.btnUpdateProfile;
                MaterialButton btnUpdateProfile = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
                if (btnUpdateProfile != null) {
                    id = R.id.etProfileName;
                    TextInputEditText etProfileName = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                    if (etProfileName != null) {
                        id = R.id.etProfilePhone;
                        TextInputEditText etProfilePhone = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                        if (etProfilePhone != null) {
                            id = R.id.etProfileVillage;
                            TextInputEditText etProfileVillage = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                            if (etProfileVillage != null) {
                                id = R.id.ivProfilePic;
                                ImageView ivProfilePic = (ImageView) ViewBindings.findChildViewById(rootView, id);
                                if (ivProfilePic != null) {
                                    id = R.id.toolbarProfile;
                                    MaterialToolbar toolbarProfile = (MaterialToolbar) ViewBindings.findChildViewById(rootView, id);
                                    if (toolbarProfile != null) {
                                        return new ActivityProfileBinding((LinearLayout) rootView, btnChangePic, btnLogout, btnUpdateProfile, etProfileName, etProfilePhone, etProfileVillage, ivProfilePic, toolbarProfile);
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
