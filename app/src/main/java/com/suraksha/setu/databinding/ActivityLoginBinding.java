package com.suraksha.setu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.suraksha.setu.R;

/* JADX INFO: loaded from: classes8.dex */
public final class ActivityLoginBinding implements ViewBinding {
    public final Button btnLogin;
    public final TextInputEditText etEmail;
    public final TextInputEditText etPassword;
    private final LinearLayout rootView;
    public final TextView tvGoToSignup;

    private ActivityLoginBinding(LinearLayout rootView, Button btnLogin, TextInputEditText etEmail, TextInputEditText etPassword, TextView tvGoToSignup) {
        this.rootView = rootView;
        this.btnLogin = btnLogin;
        this.etEmail = etEmail;
        this.etPassword = etPassword;
        this.tvGoToSignup = tvGoToSignup;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_login, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityLoginBinding bind(View rootView) {
        int id = R.id.btnLogin;
        Button btnLogin = (Button) ViewBindings.findChildViewById(rootView, id);
        if (btnLogin != null) {
            id = R.id.etEmail;
            TextInputEditText etEmail = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
            if (etEmail != null) {
                id = R.id.etPassword;
                TextInputEditText etPassword = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                if (etPassword != null) {
                    id = R.id.tvGoToSignup;
                    TextView tvGoToSignup = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (tvGoToSignup != null) {
                        return new ActivityLoginBinding((LinearLayout) rootView, btnLogin, etEmail, etPassword, tvGoToSignup);
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
