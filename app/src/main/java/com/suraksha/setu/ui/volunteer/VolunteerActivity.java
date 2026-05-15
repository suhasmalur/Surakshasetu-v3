package com.suraksha.setu.ui.volunteer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.suraksha.setu.data.model.Volunteer;
import com.suraksha.setu.databinding.ActivityVolunteerRegistrationBinding;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: VolunteerActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/suraksha/setu/ui/volunteer/VolunteerActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "binding", "Lcom/suraksha/setu/databinding/ActivityVolunteerRegistrationBinding;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "checkIfAlreadyVolunteer", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "registerVolunteer", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class VolunteerActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private ActivityVolunteerRegistrationBinding binding;
    private FirebaseFirestore db;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityVolunteerRegistrationBinding activityVolunteerRegistrationBindingInflate = ActivityVolunteerRegistrationBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityVolunteerRegistrationBindingInflate, "inflate(...)");
        this.binding = activityVolunteerRegistrationBindingInflate;
        ActivityVolunteerRegistrationBinding activityVolunteerRegistrationBinding = this.binding;
        ActivityVolunteerRegistrationBinding activityVolunteerRegistrationBinding2 = null;
        if (activityVolunteerRegistrationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVolunteerRegistrationBinding = null;
        }
        setContentView(activityVolunteerRegistrationBinding.getRoot());
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseAuth, "getInstance(...)");
        this.auth = firebaseAuth;
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseFirestore, "getInstance(...)");
        this.db = firebaseFirestore;
        checkIfAlreadyVolunteer();
        ActivityVolunteerRegistrationBinding activityVolunteerRegistrationBinding3 = this.binding;
        if (activityVolunteerRegistrationBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityVolunteerRegistrationBinding2 = activityVolunteerRegistrationBinding3;
        }
        activityVolunteerRegistrationBinding2.btnRegisterVol.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.volunteer.VolunteerActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VolunteerActivity.onCreate$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(VolunteerActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.registerVolunteer();
    }

    private final void checkIfAlreadyVolunteer() {
        String uid;
        FirebaseAuth firebaseAuth = this.auth;
        FirebaseFirestore firebaseFirestore = null;
        if (firebaseAuth == null) {
            Intrinsics.throwUninitializedPropertyAccessException("auth");
            firebaseAuth = null;
        }
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null || (uid = currentUser.getUid()) == null) {
            return;
        }
        FirebaseFirestore firebaseFirestore2 = this.db;
        if (firebaseFirestore2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("db");
        } else {
            firebaseFirestore = firebaseFirestore2;
        }
        Task<DocumentSnapshot> task = firebaseFirestore.collection("volunteers").document(uid).get();
        final Function1<DocumentSnapshot, Unit> function1 = new Function1<DocumentSnapshot, Unit>() { // from class: com.suraksha.setu.ui.volunteer.VolunteerActivity.checkIfAlreadyVolunteer.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DocumentSnapshot documentSnapshot) {
                invoke2(documentSnapshot);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DocumentSnapshot doc) {
                if (doc.exists()) {
                    VolunteerActivity.this.startActivity(new Intent(VolunteerActivity.this, (Class<?>) VolunteerDashboardActivity.class));
                    VolunteerActivity.this.finish();
                }
            }
        };
        task.addOnSuccessListener(new OnSuccessListener() { // from class: com.suraksha.setu.ui.volunteer.VolunteerActivity$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                VolunteerActivity.checkIfAlreadyVolunteer$lambda$1(function1, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkIfAlreadyVolunteer$lambda$1(Function1 tmp0, Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(p0);
    }

    private final void registerVolunteer() {
        String uid;
        ActivityVolunteerRegistrationBinding activityVolunteerRegistrationBinding = this.binding;
        if (activityVolunteerRegistrationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVolunteerRegistrationBinding = null;
        }
        String name = String.valueOf(activityVolunteerRegistrationBinding.etVolName.getText());
        ActivityVolunteerRegistrationBinding activityVolunteerRegistrationBinding2 = this.binding;
        if (activityVolunteerRegistrationBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVolunteerRegistrationBinding2 = null;
        }
        String phone = String.valueOf(activityVolunteerRegistrationBinding2.etVolPhone.getText());
        ActivityVolunteerRegistrationBinding activityVolunteerRegistrationBinding3 = this.binding;
        if (activityVolunteerRegistrationBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVolunteerRegistrationBinding3 = null;
        }
        Integer intOrNull = StringsKt.toIntOrNull(String.valueOf(activityVolunteerRegistrationBinding3.etVolAge.getText()));
        int age = intOrNull != null ? intOrNull.intValue() : 0;
        ActivityVolunteerRegistrationBinding activityVolunteerRegistrationBinding4 = this.binding;
        if (activityVolunteerRegistrationBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVolunteerRegistrationBinding4 = null;
        }
        String village = String.valueOf(activityVolunteerRegistrationBinding4.etVolVillage.getText());
        ActivityVolunteerRegistrationBinding activityVolunteerRegistrationBinding5 = this.binding;
        if (activityVolunteerRegistrationBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVolunteerRegistrationBinding5 = null;
        }
        String skills = String.valueOf(activityVolunteerRegistrationBinding5.etVolSkills.getText());
        FirebaseAuth firebaseAuth = this.auth;
        if (firebaseAuth == null) {
            Intrinsics.throwUninitializedPropertyAccessException("auth");
            firebaseAuth = null;
        }
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null || (uid = currentUser.getUid()) == null) {
            return;
        }
        if (name.length() > 0) {
            if (phone.length() > 0) {
                if (village.length() > 0) {
                    Volunteer volunteer = new Volunteer(uid, name, phone, village, age, null, true, false, skills, 0.0d, 0.0d, null, 3616, null);
                    FirebaseFirestore firebaseFirestore = this.db;
                    if (firebaseFirestore == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("db");
                        firebaseFirestore = null;
                    }
                    Task<Void> task = firebaseFirestore.collection("volunteers").document(uid).set(volunteer);
                    final Function1<Void, Unit> function1 = new Function1<Void, Unit>() { // from class: com.suraksha.setu.ui.volunteer.VolunteerActivity.registerVolunteer.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Void r2) {
                            invoke2(r2);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Void it) {
                            Toast.makeText(VolunteerActivity.this, "Volunteer Registration Successful!", 0).show();
                            VolunteerActivity.this.startActivity(new Intent(VolunteerActivity.this, (Class<?>) VolunteerDashboardActivity.class));
                            VolunteerActivity.this.finish();
                        }
                    };
                    task.addOnSuccessListener(new OnSuccessListener() { // from class: com.suraksha.setu.ui.volunteer.VolunteerActivity$$ExternalSyntheticLambda1
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public final void onSuccess(Object obj) {
                            VolunteerActivity.registerVolunteer$lambda$2(function1, obj);
                        }
                    }).addOnFailureListener(new OnFailureListener() { // from class: com.suraksha.setu.ui.volunteer.VolunteerActivity$$ExternalSyntheticLambda2
                        @Override // com.google.android.gms.tasks.OnFailureListener
                        public final void onFailure(Exception exc) {
                            VolunteerActivity.registerVolunteer$lambda$3(this.f$0, exc);
                        }
                    });
                    return;
                }
            }
        }
        Toast.makeText(this, "Please fill required fields", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registerVolunteer$lambda$2(Function1 tmp0, Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registerVolunteer$lambda$3(VolunteerActivity this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Toast.makeText(this$0, "Registration Failed: " + it.getMessage(), 0).show();
    }
}
