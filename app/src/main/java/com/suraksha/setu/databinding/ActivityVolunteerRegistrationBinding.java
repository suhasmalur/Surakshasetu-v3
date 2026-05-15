package com.suraksha.setu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.suraksha.setu.R;

/* JADX INFO: loaded from: classes8.dex */
public final class ActivityVolunteerRegistrationBinding implements ViewBinding {
    public final MaterialButton btnRegisterVol;
    public final MaterialButton btnUploadID;
    public final TextInputEditText etVolAge;
    public final TextInputEditText etVolName;
    public final TextInputEditText etVolPhone;
    public final TextInputEditText etVolSkills;
    public final TextInputEditText etVolVillage;
    private final ScrollView rootView;

    private ActivityVolunteerRegistrationBinding(ScrollView rootView, MaterialButton btnRegisterVol, MaterialButton btnUploadID, TextInputEditText etVolAge, TextInputEditText etVolName, TextInputEditText etVolPhone, TextInputEditText etVolSkills, TextInputEditText etVolVillage) {
        this.rootView = rootView;
        this.btnRegisterVol = btnRegisterVol;
        this.btnUploadID = btnUploadID;
        this.etVolAge = etVolAge;
        this.etVolName = etVolName;
        this.etVolPhone = etVolPhone;
        this.etVolSkills = etVolSkills;
        this.etVolVillage = etVolVillage;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static ActivityVolunteerRegistrationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityVolunteerRegistrationBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_volunteer_registration, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityVolunteerRegistrationBinding bind(View rootView) {
        int id = R.id.btnRegisterVol;
        MaterialButton btnRegisterVol = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
        if (btnRegisterVol != null) {
            id = R.id.btnUploadID;
            MaterialButton btnUploadID = (MaterialButton) ViewBindings.findChildViewById(rootView, id);
            if (btnUploadID != null) {
                id = R.id.etVolAge;
                TextInputEditText etVolAge = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                if (etVolAge != null) {
                    id = R.id.etVolName;
                    TextInputEditText etVolName = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                    if (etVolName != null) {
                        id = R.id.etVolPhone;
                        TextInputEditText etVolPhone = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                        if (etVolPhone != null) {
                            id = R.id.etVolSkills;
                            TextInputEditText etVolSkills = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                            if (etVolSkills != null) {
                                id = R.id.etVolVillage;
                                TextInputEditText etVolVillage = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                                if (etVolVillage != null) {
                                    return new ActivityVolunteerRegistrationBinding((ScrollView) rootView, btnRegisterVol, btnUploadID, etVolAge, etVolName, etVolPhone, etVolSkills, etVolVillage);
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
