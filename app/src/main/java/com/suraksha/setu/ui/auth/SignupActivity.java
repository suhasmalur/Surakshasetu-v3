package com.suraksha.setu.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.suraksha.setu.data.model.User;
import com.suraksha.setu.databinding.ActivitySignupBinding;
import com.suraksha.setu.ui.main.MainActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SignupActivity.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/suraksha/setu/ui/auth/SignupActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "binding", "Lcom/suraksha/setu/databinding/ActivitySignupBinding;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "saveUserToFirestore", "user", "Lcom/suraksha/setu/data/model/User;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SignupActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private ActivitySignupBinding binding;
    private FirebaseFirestore db;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySignupBinding activitySignupBindingInflate = ActivitySignupBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activitySignupBindingInflate, "inflate(...)");
        this.binding = activitySignupBindingInflate;
        ActivitySignupBinding activitySignupBinding = this.binding;
        ActivitySignupBinding activitySignupBinding2 = null;
        if (activitySignupBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySignupBinding = null;
        }
        setContentView(activitySignupBinding.getRoot());
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseAuth, "getInstance(...)");
        this.auth = firebaseAuth;
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseFirestore, "getInstance(...)");
        this.db = firebaseFirestore;
        ActivitySignupBinding activitySignupBinding3 = this.binding;
        if (activitySignupBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySignupBinding3 = null;
        }
        activitySignupBinding3.btnSignup.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.auth.SignupActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SignupActivity.onCreate$lambda$1(this.f$0, view);
            }
        });
        ActivitySignupBinding activitySignupBinding4 = this.binding;
        if (activitySignupBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySignupBinding2 = activitySignupBinding4;
        }
        activitySignupBinding2.tvGoToLogin.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.auth.SignupActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SignupActivity.onCreate$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(final SignupActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivitySignupBinding activitySignupBinding = this$0.binding;
        FirebaseAuth firebaseAuth = null;
        if (activitySignupBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySignupBinding = null;
        }
        final String fullName = String.valueOf(activitySignupBinding.etFullName.getText());
        ActivitySignupBinding activitySignupBinding2 = this$0.binding;
        if (activitySignupBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySignupBinding2 = null;
        }
        final String phone = String.valueOf(activitySignupBinding2.etPhone.getText());
        ActivitySignupBinding activitySignupBinding3 = this$0.binding;
        if (activitySignupBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySignupBinding3 = null;
        }
        final String email = String.valueOf(activitySignupBinding3.etEmail.getText());
        ActivitySignupBinding activitySignupBinding4 = this$0.binding;
        if (activitySignupBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySignupBinding4 = null;
        }
        final String village = String.valueOf(activitySignupBinding4.etVillage.getText());
        ActivitySignupBinding activitySignupBinding5 = this$0.binding;
        if (activitySignupBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySignupBinding5 = null;
        }
        String password = String.valueOf(activitySignupBinding5.etPassword.getText());
        ActivitySignupBinding activitySignupBinding6 = this$0.binding;
        if (activitySignupBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySignupBinding6 = null;
        }
        int selectedGenderId = activitySignupBinding6.rgGender.getCheckedRadioButtonId();
        final String gender = ((RadioButton) this$0.findViewById(selectedGenderId)).getText().toString();
        if (fullName.length() > 0) {
            if (phone.length() > 0) {
                if (email.length() > 0) {
                    if (village.length() > 0) {
                        if (password.length() > 0) {
                            FirebaseAuth firebaseAuth2 = this$0.auth;
                            if (firebaseAuth2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("auth");
                            } else {
                                firebaseAuth = firebaseAuth2;
                            }
                            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener() { // from class: com.suraksha.setu.ui.auth.SignupActivity$$ExternalSyntheticLambda0
                                @Override // com.google.android.gms.tasks.OnCompleteListener
                                public final void onComplete(Task task) {
                                    SignupActivity.onCreate$lambda$1$lambda$0(this.f$0, fullName, phone, email, village, gender, task);
                                }
                            });
                            return;
                        }
                    }
                }
            }
        }
        Toast.makeText(this$0, "Please fill all fields", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1$lambda$0(SignupActivity this$0, String fullName, String phone, String email, String village, String gender, Task task) {
        String uid;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fullName, "$fullName");
        Intrinsics.checkNotNullParameter(phone, "$phone");
        Intrinsics.checkNotNullParameter(email, "$email");
        Intrinsics.checkNotNullParameter(village, "$village");
        Intrinsics.checkNotNullParameter(gender, "$gender");
        Intrinsics.checkNotNullParameter(task, "task");
        FirebaseAuth firebaseAuth = null;
        if (task.isSuccessful()) {
            FirebaseAuth firebaseAuth2 = this$0.auth;
            if (firebaseAuth2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("auth");
            } else {
                firebaseAuth = firebaseAuth2;
            }
            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
            if (currentUser == null || (uid = currentUser.getUid()) == null) {
                uid = "";
            }
            this$0.saveUserToFirestore(new User(uid, fullName, phone, email, village, gender));
            return;
        }
        SignupActivity signupActivity = this$0;
        StringBuilder sbAppend = new StringBuilder().append("Signup Failed: ");
        Exception exception = task.getException();
        Toast.makeText(signupActivity, sbAppend.append(exception != null ? exception.getMessage() : null).toString(), 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(SignupActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    private final void saveUserToFirestore(User user) {
        FirebaseFirestore firebaseFirestore = this.db;
        if (firebaseFirestore == null) {
            Intrinsics.throwUninitializedPropertyAccessException("db");
            firebaseFirestore = null;
        }
        Task<Void> task = firebaseFirestore.collection("users").document(user.getUid()).set(user);
        final Function1<Void, Unit> function1 = new Function1<Void, Unit>() { // from class: com.suraksha.setu.ui.auth.SignupActivity.saveUserToFirestore.1
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
                SignupActivity.this.startActivity(new Intent(SignupActivity.this, (Class<?>) MainActivity.class));
                SignupActivity.this.finishAffinity();
            }
        };
        task.addOnSuccessListener(new OnSuccessListener() { // from class: com.suraksha.setu.ui.auth.SignupActivity$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                SignupActivity.saveUserToFirestore$lambda$3(function1, obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.suraksha.setu.ui.auth.SignupActivity$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                SignupActivity.saveUserToFirestore$lambda$4(this.f$0, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void saveUserToFirestore$lambda$3(Function1 tmp0, Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void saveUserToFirestore$lambda$4(SignupActivity this$0, Exception e) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(e, "e");
        Toast.makeText(this$0, "Error saving profile: " + e.getMessage(), 0).show();
    }
}
