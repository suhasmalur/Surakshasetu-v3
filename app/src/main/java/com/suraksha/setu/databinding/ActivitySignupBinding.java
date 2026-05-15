package com.suraksha.setu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.suraksha.setu.R;

/* JADX INFO: loaded from: classes8.dex */
public final class ActivitySignupBinding implements ViewBinding {
    public final Button btnSignup;
    public final TextInputEditText etEmail;
    public final TextInputEditText etFullName;
    public final TextInputEditText etPassword;
    public final TextInputEditText etPhone;
    public final TextInputEditText etVillage;
    public final RadioButton rbFemale;
    public final RadioButton rbMale;
    public final RadioButton rbOther;
    public final RadioGroup rgGender;
    private final ScrollView rootView;
    public final TextView tvGoToLogin;

    private ActivitySignupBinding(ScrollView rootView, Button btnSignup, TextInputEditText etEmail, TextInputEditText etFullName, TextInputEditText etPassword, TextInputEditText etPhone, TextInputEditText etVillage, RadioButton rbFemale, RadioButton rbMale, RadioButton rbOther, RadioGroup rgGender, TextView tvGoToLogin) {
        this.rootView = rootView;
        this.btnSignup = btnSignup;
        this.etEmail = etEmail;
        this.etFullName = etFullName;
        this.etPassword = etPassword;
        this.etPhone = etPhone;
        this.etVillage = etVillage;
        this.rbFemale = rbFemale;
        this.rbMale = rbMale;
        this.rbOther = rbOther;
        this.rgGender = rgGender;
        this.tvGoToLogin = tvGoToLogin;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static ActivitySignupBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySignupBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_signup, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivitySignupBinding bind(View rootView) {
        int id = R.id.btnSignup;
        Button btnSignup = (Button) ViewBindings.findChildViewById(rootView, id);
        if (btnSignup != null) {
            id = R.id.etEmail;
            TextInputEditText etEmail = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
            if (etEmail != null) {
                id = R.id.etFullName;
                TextInputEditText etFullName = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                if (etFullName != null) {
                    id = R.id.etPassword;
                    TextInputEditText etPassword = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                    if (etPassword != null) {
                        id = R.id.etPhone;
                        TextInputEditText etPhone = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                        if (etPhone != null) {
                            id = R.id.etVillage;
                            TextInputEditText etVillage = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                            if (etVillage != null) {
                                id = R.id.rbFemale;
                                RadioButton rbFemale = (RadioButton) ViewBindings.findChildViewById(rootView, id);
                                if (rbFemale != null) {
                                    id = R.id.rbMale;
                                    RadioButton rbMale = (RadioButton) ViewBindings.findChildViewById(rootView, id);
                                    if (rbMale != null) {
                                        id = R.id.rbOther;
                                        RadioButton rbOther = (RadioButton) ViewBindings.findChildViewById(rootView, id);
                                        if (rbOther != null) {
                                            id = R.id.rgGender;
                                            RadioGroup rgGender = (RadioGroup) ViewBindings.findChildViewById(rootView, id);
                                            if (rgGender != null) {
                                                id = R.id.tvGoToLogin;
                                                TextView tvGoToLogin = (TextView) ViewBindings.findChildViewById(rootView, id);
                                                if (tvGoToLogin != null) {
                                                    return new ActivitySignupBinding((ScrollView) rootView, btnSignup, etEmail, etFullName, etPassword, etPhone, etVillage, rbFemale, rbMale, rbOther, rgGender, tvGoToLogin);
                                                }
                                            }
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
