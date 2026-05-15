package com.suraksha.setu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.suraksha.setu.R;

/* JADX INFO: loaded from: classes8.dex */
public final class DialogAddMemberBinding implements ViewBinding {
    public final TextView dialogTitle;
    public final TextInputEditText etMemberName;
    public final TextInputEditText etMemberPhone;
    public final TextInputEditText etNotes;
    public final TextInputEditText etRelationship;
    public final RadioButton rbHigh;
    public final RadioButton rbLow;
    public final RadioButton rbMedium;
    public final RadioGroup rgPriority;
    private final ScrollView rootView;

    private DialogAddMemberBinding(ScrollView rootView, TextView dialogTitle, TextInputEditText etMemberName, TextInputEditText etMemberPhone, TextInputEditText etNotes, TextInputEditText etRelationship, RadioButton rbHigh, RadioButton rbLow, RadioButton rbMedium, RadioGroup rgPriority) {
        this.rootView = rootView;
        this.dialogTitle = dialogTitle;
        this.etMemberName = etMemberName;
        this.etMemberPhone = etMemberPhone;
        this.etNotes = etNotes;
        this.etRelationship = etRelationship;
        this.rbHigh = rbHigh;
        this.rbLow = rbLow;
        this.rbMedium = rbMedium;
        this.rgPriority = rgPriority;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ScrollView getRoot() {
        return this.rootView;
    }

    public static DialogAddMemberBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static DialogAddMemberBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.dialog_add_member, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static DialogAddMemberBinding bind(View rootView) {
        int id = R.id.dialogTitle;
        TextView dialogTitle = (TextView) ViewBindings.findChildViewById(rootView, id);
        if (dialogTitle != null) {
            id = R.id.etMemberName;
            TextInputEditText etMemberName = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
            if (etMemberName != null) {
                id = R.id.etMemberPhone;
                TextInputEditText etMemberPhone = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                if (etMemberPhone != null) {
                    id = R.id.etNotes;
                    TextInputEditText etNotes = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                    if (etNotes != null) {
                        id = R.id.etRelationship;
                        TextInputEditText etRelationship = (TextInputEditText) ViewBindings.findChildViewById(rootView, id);
                        if (etRelationship != null) {
                            id = R.id.rbHigh;
                            RadioButton rbHigh = (RadioButton) ViewBindings.findChildViewById(rootView, id);
                            if (rbHigh != null) {
                                id = R.id.rbLow;
                                RadioButton rbLow = (RadioButton) ViewBindings.findChildViewById(rootView, id);
                                if (rbLow != null) {
                                    id = R.id.rbMedium;
                                    RadioButton rbMedium = (RadioButton) ViewBindings.findChildViewById(rootView, id);
                                    if (rbMedium != null) {
                                        id = R.id.rgPriority;
                                        RadioGroup rgPriority = (RadioGroup) ViewBindings.findChildViewById(rootView, id);
                                        if (rgPriority != null) {
                                            return new DialogAddMemberBinding((ScrollView) rootView, dialogTitle, etMemberName, etMemberPhone, etNotes, etRelationship, rbHigh, rbLow, rbMedium, rgPriority);
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
