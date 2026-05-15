package com.suraksha.setu.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.suraksha.setu.databinding.ActivityLoginBinding;
import com.suraksha.setu.ui.main.MainActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LoginActivity.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/suraksha/setu/ui/auth/LoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "binding", "Lcom/suraksha/setu/databinding/ActivityLoginBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private ActivityLoginBinding binding;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding activityLoginBindingInflate = ActivityLoginBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityLoginBindingInflate, "inflate(...)");
        this.binding = activityLoginBindingInflate;
        ActivityLoginBinding activityLoginBinding = this.binding;
        ActivityLoginBinding activityLoginBinding2 = null;
        if (activityLoginBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding = null;
        }
        setContentView(activityLoginBinding.getRoot());
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseAuth, "getInstance(...)");
        this.auth = firebaseAuth;
        ActivityLoginBinding activityLoginBinding3 = this.binding;
        if (activityLoginBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding3 = null;
        }
        activityLoginBinding3.btnLogin.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.auth.LoginActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.onCreate$lambda$1(this.f$0, view);
            }
        });
        ActivityLoginBinding activityLoginBinding4 = this.binding;
        if (activityLoginBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityLoginBinding2 = activityLoginBinding4;
        }
        activityLoginBinding2.tvGoToSignup.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.auth.LoginActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.onCreate$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(final LoginActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityLoginBinding activityLoginBinding = this$0.binding;
        FirebaseAuth firebaseAuth = null;
        if (activityLoginBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding = null;
        }
        String email = String.valueOf(activityLoginBinding.etEmail.getText());
        ActivityLoginBinding activityLoginBinding2 = this$0.binding;
        if (activityLoginBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityLoginBinding2 = null;
        }
        String password = String.valueOf(activityLoginBinding2.etPassword.getText());
        if (email.length() > 0) {
            if (password.length() > 0) {
                FirebaseAuth firebaseAuth2 = this$0.auth;
                if (firebaseAuth2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("auth");
                } else {
                    firebaseAuth = firebaseAuth2;
                }
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener() { // from class: com.suraksha.setu.ui.auth.LoginActivity$$ExternalSyntheticLambda0
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public final void onComplete(Task task) {
                        LoginActivity.onCreate$lambda$1$lambda$0(this.f$0, task);
                    }
                });
                return;
            }
        }
        Toast.makeText(this$0, "Please fill all fields", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1$lambda$0(LoginActivity this$0, Task task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            this$0.startActivity(new Intent(this$0, (Class<?>) MainActivity.class));
            this$0.finish();
        } else {
            LoginActivity loginActivity = this$0;
            StringBuilder sbAppend = new StringBuilder().append("Login Failed: ");
            Exception exception = task.getException();
            Toast.makeText(loginActivity, sbAppend.append(exception != null ? exception.getMessage() : null).toString(), 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(LoginActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, (Class<?>) SignupActivity.class));
    }
}
