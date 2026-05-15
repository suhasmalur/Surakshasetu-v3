package com.suraksha.setu.ui.volunteer;

import android.os.Bundle;
import android.widget.CompoundButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.suraksha.setu.R;
import com.suraksha.setu.data.model.Emergency;
import com.suraksha.setu.databinding.ActivityVolunteerDashboardBinding;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VolunteerDashboardActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\nH\u0002J\u0016\u0010\u000f\u001a\u00020\n2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/suraksha/setu/ui/volunteer/VolunteerDashboardActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "binding", "Lcom/suraksha/setu/databinding/ActivityVolunteerDashboardBinding;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "listenForEmergencies", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupDashboard", "setupRecyclerView", "emergencies", "", "Lcom/suraksha/setu/data/model/Emergency;", "updateStatusUI", "isAvailable", "", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class VolunteerDashboardActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private ActivityVolunteerDashboardBinding binding;
    private FirebaseFirestore db;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityVolunteerDashboardBinding activityVolunteerDashboardBindingInflate = ActivityVolunteerDashboardBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityVolunteerDashboardBindingInflate, "inflate(...)");
        this.binding = activityVolunteerDashboardBindingInflate;
        ActivityVolunteerDashboardBinding activityVolunteerDashboardBinding = this.binding;
        if (activityVolunteerDashboardBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVolunteerDashboardBinding = null;
        }
        setContentView(activityVolunteerDashboardBinding.getRoot());
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseFirestore, "getInstance(...)");
        this.db = firebaseFirestore;
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseAuth, "getInstance(...)");
        this.auth = firebaseAuth;
        setupDashboard();
        listenForEmergencies();
    }

    private final void setupDashboard() {
        final String uid;
        FirebaseAuth firebaseAuth = this.auth;
        ActivityVolunteerDashboardBinding activityVolunteerDashboardBinding = null;
        if (firebaseAuth == null) {
            Intrinsics.throwUninitializedPropertyAccessException("auth");
            firebaseAuth = null;
        }
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null || (uid = currentUser.getUid()) == null) {
            return;
        }
        FirebaseFirestore firebaseFirestore = this.db;
        if (firebaseFirestore == null) {
            Intrinsics.throwUninitializedPropertyAccessException("db");
            firebaseFirestore = null;
        }
        Task<DocumentSnapshot> task = firebaseFirestore.collection("volunteers").document(uid).get();
        final Function1<DocumentSnapshot, Unit> function1 = new Function1<DocumentSnapshot, Unit>() { // from class: com.suraksha.setu.ui.volunteer.VolunteerDashboardActivity.setupDashboard.1
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
                Boolean bool = doc.getBoolean("available");
                if (bool == null) {
                    bool = false;
                }
                boolean available = bool.booleanValue();
                ActivityVolunteerDashboardBinding activityVolunteerDashboardBinding2 = VolunteerDashboardActivity.this.binding;
                if (activityVolunteerDashboardBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityVolunteerDashboardBinding2 = null;
                }
                activityVolunteerDashboardBinding2.switchAvailability.setChecked(available);
                VolunteerDashboardActivity.this.updateStatusUI(available);
            }
        };
        task.addOnSuccessListener(new OnSuccessListener() { // from class: com.suraksha.setu.ui.volunteer.VolunteerDashboardActivity$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                VolunteerDashboardActivity.setupDashboard$lambda$0(function1, obj);
            }
        });
        ActivityVolunteerDashboardBinding activityVolunteerDashboardBinding2 = this.binding;
        if (activityVolunteerDashboardBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityVolunteerDashboardBinding = activityVolunteerDashboardBinding2;
        }
        activityVolunteerDashboardBinding.switchAvailability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.suraksha.setu.ui.volunteer.VolunteerDashboardActivity$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                VolunteerDashboardActivity.setupDashboard$lambda$1(this.f$0, uid, compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupDashboard$lambda$0(Function1 tmp0, Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupDashboard$lambda$1(VolunteerDashboardActivity this$0, String uid, CompoundButton compoundButton, boolean isChecked) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(uid, "$uid");
        FirebaseFirestore firebaseFirestore = this$0.db;
        if (firebaseFirestore == null) {
            Intrinsics.throwUninitializedPropertyAccessException("db");
            firebaseFirestore = null;
        }
        firebaseFirestore.collection("volunteers").document(uid).update("available", Boolean.valueOf(isChecked), new Object[0]);
        this$0.updateStatusUI(isChecked);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateStatusUI(boolean isAvailable) {
        ActivityVolunteerDashboardBinding activityVolunteerDashboardBinding = this.binding;
        ActivityVolunteerDashboardBinding activityVolunteerDashboardBinding2 = null;
        if (activityVolunteerDashboardBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVolunteerDashboardBinding = null;
        }
        activityVolunteerDashboardBinding.tvVolStatus.setText(isAvailable ? "Online & Ready" : "Offline");
        ActivityVolunteerDashboardBinding activityVolunteerDashboardBinding3 = this.binding;
        if (activityVolunteerDashboardBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityVolunteerDashboardBinding2 = activityVolunteerDashboardBinding3;
        }
        activityVolunteerDashboardBinding2.tvVolStatus.setTextColor(isAvailable ? getColor(R.color.primary_green) : getColor(R.color.grey_800));
    }

    private final void listenForEmergencies() {
        FirebaseFirestore firebaseFirestore = this.db;
        if (firebaseFirestore == null) {
            Intrinsics.throwUninitializedPropertyAccessException("db");
            firebaseFirestore = null;
        }
        firebaseFirestore.collection("emergencies").whereEqualTo(NotificationCompat.CATEGORY_STATUS, "ACTIVE").addSnapshotListener(new EventListener() { // from class: com.suraksha.setu.ui.volunteer.VolunteerDashboardActivity$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                VolunteerDashboardActivity.listenForEmergencies$lambda$2(this.f$0, (QuerySnapshot) obj, firebaseFirestoreException);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void listenForEmergencies$lambda$2(VolunteerDashboardActivity this$0, QuerySnapshot snapshot, FirebaseFirestoreException e) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (e != null) {
            return;
        }
        ActivityVolunteerDashboardBinding activityVolunteerDashboardBinding = null;
        List<Emergency> objects = snapshot != null ? snapshot.toObjects(Emergency.class) : null;
        if (objects == null) {
            objects = CollectionsKt.emptyList();
        }
        if (objects.isEmpty()) {
            ActivityVolunteerDashboardBinding activityVolunteerDashboardBinding2 = this$0.binding;
            if (activityVolunteerDashboardBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityVolunteerDashboardBinding2 = null;
            }
            activityVolunteerDashboardBinding2.emptyVolState.setVisibility(0);
            ActivityVolunteerDashboardBinding activityVolunteerDashboardBinding3 = this$0.binding;
            if (activityVolunteerDashboardBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityVolunteerDashboardBinding = activityVolunteerDashboardBinding3;
            }
            activityVolunteerDashboardBinding.rvActiveEmergencies.setVisibility(8);
            return;
        }
        ActivityVolunteerDashboardBinding activityVolunteerDashboardBinding4 = this$0.binding;
        if (activityVolunteerDashboardBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVolunteerDashboardBinding4 = null;
        }
        activityVolunteerDashboardBinding4.emptyVolState.setVisibility(8);
        ActivityVolunteerDashboardBinding activityVolunteerDashboardBinding5 = this$0.binding;
        if (activityVolunteerDashboardBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityVolunteerDashboardBinding = activityVolunteerDashboardBinding5;
        }
        activityVolunteerDashboardBinding.rvActiveEmergencies.setVisibility(0);
        this$0.setupRecyclerView(objects);
    }

    private final void setupRecyclerView(List<Emergency> emergencies) {
        ActivityVolunteerDashboardBinding activityVolunteerDashboardBinding = this.binding;
        if (activityVolunteerDashboardBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityVolunteerDashboardBinding = null;
        }
        activityVolunteerDashboardBinding.rvActiveEmergencies.setLayoutManager(new LinearLayoutManager(this));
    }
}
