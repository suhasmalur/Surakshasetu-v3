package com.suraksha.setu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.suraksha.setu.R;
import com.suraksha.setu.databinding.ActivitySplashBinding;
import com.suraksha.setu.ui.auth.LoginActivity;
import com.suraksha.setu.ui.main.MainActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SplashActivity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/suraksha/setu/ui/SplashActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "binding", "Lcom/suraksha/setu/databinding/ActivitySplashBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SplashActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private ActivitySplashBinding binding;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding activitySplashBindingInflate = ActivitySplashBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activitySplashBindingInflate, "inflate(...)");
        this.binding = activitySplashBindingInflate;
        ActivitySplashBinding activitySplashBinding = this.binding;
        ActivitySplashBinding activitySplashBinding2 = null;
        if (activitySplashBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySplashBinding = null;
        }
        setContentView(activitySplashBinding.getRoot());
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseAuth, "getInstance(...)");
        this.auth = firebaseAuth;
        Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        final Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        ActivitySplashBinding activitySplashBinding3 = this.binding;
        if (activitySplashBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySplashBinding3 = null;
        }
        activitySplashBinding3.ivLogo.startAnimation(zoomIn);
        ActivitySplashBinding activitySplashBinding4 = this.binding;
        if (activitySplashBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySplashBinding4 = null;
        }
        activitySplashBinding4.tvAppName.startAnimation(fadeIn);
        ActivitySplashBinding activitySplashBinding5 = this.binding;
        if (activitySplashBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySplashBinding2 = activitySplashBinding5;
        }
        activitySplashBinding2.tvSubtitle.startAnimation(fadeIn);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.suraksha.setu.ui.SplashActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SplashActivity.onCreate$lambda$0(this.f$0, pulse);
            }
        }, 1000L);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.suraksha.setu.ui.SplashActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SplashActivity.onCreate$lambda$1(this.f$0);
            }
        }, 3500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(SplashActivity this$0, Animation $pulse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivitySplashBinding activitySplashBinding = this$0.binding;
        if (activitySplashBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySplashBinding = null;
        }
        activitySplashBinding.ivLogo.startAnimation($pulse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(SplashActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseAuth firebaseAuth = this$0.auth;
        if (firebaseAuth == null) {
            Intrinsics.throwUninitializedPropertyAccessException("auth");
            firebaseAuth = null;
        }
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            this$0.startActivity(new Intent(this$0, (Class<?>) MainActivity.class));
        } else {
            this$0.startActivity(new Intent(this$0, (Class<?>) LoginActivity.class));
        }
        this$0.finish();
    }
}
