package com.suraksha.setu.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.suraksha.setu.databinding.ActivityProfileBinding;
import com.suraksha.setu.ui.auth.LoginActivity;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProfileActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/suraksha/setu/ui/main/ProfileActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "binding", "Lcom/suraksha/setu/databinding/ActivityProfileBinding;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "loadProfileData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "updateProfile", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private ActivityProfileBinding binding;
    private FirebaseFirestore db;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfileBinding activityProfileBindingInflate = ActivityProfileBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityProfileBindingInflate, "inflate(...)");
        this.binding = activityProfileBindingInflate;
        ActivityProfileBinding activityProfileBinding = this.binding;
        ActivityProfileBinding activityProfileBinding2 = null;
        if (activityProfileBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileBinding = null;
        }
        setContentView(activityProfileBinding.getRoot());
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseAuth, "getInstance(...)");
        this.auth = firebaseAuth;
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseFirestore, "getInstance(...)");
        this.db = firebaseFirestore;
        loadProfileData();
        ActivityProfileBinding activityProfileBinding3 = this.binding;
        if (activityProfileBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileBinding3 = null;
        }
        activityProfileBinding3.btnUpdateProfile.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.main.ProfileActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileActivity.onCreate$lambda$0(this.f$0, view);
            }
        });
        ActivityProfileBinding activityProfileBinding4 = this.binding;
        if (activityProfileBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileBinding4 = null;
        }
        activityProfileBinding4.btnLogout.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.main.ProfileActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileActivity.onCreate$lambda$1(this.f$0, view);
            }
        });
        ActivityProfileBinding activityProfileBinding5 = this.binding;
        if (activityProfileBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityProfileBinding2 = activityProfileBinding5;
        }
        activityProfileBinding2.toolbarProfile.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.main.ProfileActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ProfileActivity.onCreate$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(ProfileActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updateProfile();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(ProfileActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseAuth firebaseAuth = this$0.auth;
        if (firebaseAuth == null) {
            Intrinsics.throwUninitializedPropertyAccessException("auth");
            firebaseAuth = null;
        }
        firebaseAuth.signOut();
        Intent intent = new Intent(this$0, (Class<?>) LoginActivity.class);
        intent.setFlags(268468224);
        this$0.startActivity(intent);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(ProfileActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    private final void loadProfileData() {
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
        Task<DocumentSnapshot> task = firebaseFirestore.collection("users").document(uid).get();
        final Function1<DocumentSnapshot, Unit> function1 = new Function1<DocumentSnapshot, Unit>() { // from class: com.suraksha.setu.ui.main.ProfileActivity.loadProfileData.1
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
                ActivityProfileBinding activityProfileBinding = ProfileActivity.this.binding;
                ActivityProfileBinding activityProfileBinding2 = null;
                if (activityProfileBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityProfileBinding = null;
                }
                activityProfileBinding.etProfileName.setText(doc.getString("fullName"));
                ActivityProfileBinding activityProfileBinding3 = ProfileActivity.this.binding;
                if (activityProfileBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityProfileBinding3 = null;
                }
                activityProfileBinding3.etProfileVillage.setText(doc.getString("villageName"));
                ActivityProfileBinding activityProfileBinding4 = ProfileActivity.this.binding;
                if (activityProfileBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityProfileBinding2 = activityProfileBinding4;
                }
                activityProfileBinding2.etProfilePhone.setText(doc.getString("phoneNumber"));
            }
        };
        task.addOnSuccessListener(new OnSuccessListener() { // from class: com.suraksha.setu.ui.main.ProfileActivity$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                ProfileActivity.loadProfileData$lambda$3(function1, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadProfileData$lambda$3(Function1 tmp0, Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(p0);
    }

    private final void updateProfile() {
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
        Pair[] pairArr = new Pair[3];
        ActivityProfileBinding activityProfileBinding = this.binding;
        if (activityProfileBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileBinding = null;
        }
        pairArr[0] = TuplesKt.to("fullName", String.valueOf(activityProfileBinding.etProfileName.getText()));
        ActivityProfileBinding activityProfileBinding2 = this.binding;
        if (activityProfileBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileBinding2 = null;
        }
        pairArr[1] = TuplesKt.to("villageName", String.valueOf(activityProfileBinding2.etProfileVillage.getText()));
        ActivityProfileBinding activityProfileBinding3 = this.binding;
        if (activityProfileBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityProfileBinding3 = null;
        }
        pairArr[2] = TuplesKt.to("phoneNumber", String.valueOf(activityProfileBinding3.etProfilePhone.getText()));
        Map<String, Object> mapMapOf = MapsKt.mapOf(pairArr);
        FirebaseFirestore firebaseFirestore2 = this.db;
        if (firebaseFirestore2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("db");
        } else {
            firebaseFirestore = firebaseFirestore2;
        }
        Task<Void> taskUpdate = firebaseFirestore.collection("users").document(uid).update(mapMapOf);
        final Function1<Void, Unit> function1 = new Function1<Void, Unit>() { // from class: com.suraksha.setu.ui.main.ProfileActivity.updateProfile.1
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
                Toast.makeText(ProfileActivity.this, "Profile Updated!", 0).show();
            }
        };
        taskUpdate.addOnSuccessListener(new OnSuccessListener() { // from class: com.suraksha.setu.ui.main.ProfileActivity$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                ProfileActivity.updateProfile$lambda$4(function1, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateProfile$lambda$4(Function1 tmp0, Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(p0);
    }
}
