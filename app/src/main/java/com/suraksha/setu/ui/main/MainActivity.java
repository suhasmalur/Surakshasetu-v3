package com.suraksha.setu.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.suraksha.setu.R;
import com.suraksha.setu.databinding.ActivityMainBinding;
import com.suraksha.setu.service.SOSService;
import com.suraksha.setu.ui.safe_circle.SafeCircleActivity;
import com.suraksha.setu.ui.volunteer.VolunteerActivity;
import com.suraksha.setu.util.LocationHelper;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\u0012\u0010\u0017\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0015H\u0002J\b\u0010\u001b\u001a\u00020\u0015H\u0002J\b\u0010\u001c\u001a\u00020\u0015H\u0002J\b\u0010\u001d\u001a\u00020\u0015H\u0002J\u0010\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R(\u0010\u000f\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0012 \u0013*\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00110\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/suraksha/setu/ui/main/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "binding", "Lcom/suraksha/setu/databinding/ActivityMainBinding;", "countDownTimer", "Landroid/os/CountDownTimer;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "isEmergencyActive", "", "locationHelper", "Lcom/suraksha/setu/util/LocationHelper;", "requestPermissionsLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "kotlin.jvm.PlatformType", "checkPermissions", "", "loadUserProfile", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showSOSCountdown", "startEmergency", "startLiveLocationUpdates", "stopEmergency", "updateLocationUI", "location", "Landroid/location/Location;", "vibrateShort", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private ActivityMainBinding binding;
    private CountDownTimer countDownTimer;
    private FirebaseFirestore db;
    private boolean isEmergencyActive;
    private LocationHelper locationHelper;
    private final ActivityResultLauncher<String[]> requestPermissionsLauncher;

    public MainActivity() {
        ActivityResultLauncher<String[]> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.suraksha.setu.ui.main.MainActivity$$ExternalSyntheticLambda5
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                MainActivity.requestPermissionsLauncher$lambda$0(this.f$0, (Map) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResult(...)");
        this.requestPermissionsLauncher = activityResultLauncherRegisterForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestPermissionsLauncher$lambda$0(MainActivity this$0, Map permissions) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(permissions.get("android.permission.ACCESS_FINE_LOCATION"), (Object) true)) {
            this$0.startLiveLocationUpdates();
        } else {
            Toast.makeText(this$0, "Location permission is required for safety features", 1).show();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBindingInflate = ActivityMainBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityMainBindingInflate, "inflate(...)");
        this.binding = activityMainBindingInflate;
        ActivityMainBinding activityMainBinding = this.binding;
        ActivityMainBinding activityMainBinding2 = null;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        setContentView(activityMainBinding.getRoot());
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseAuth, "getInstance(...)");
        this.auth = firebaseAuth;
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseFirestore, "getInstance(...)");
        this.db = firebaseFirestore;
        this.locationHelper = new LocationHelper(this);
        checkPermissions();
        loadUserProfile();
        ActivityMainBinding activityMainBinding3 = this.binding;
        if (activityMainBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding3 = null;
        }
        activityMainBinding3.toolbarMain.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() { // from class: com.suraksha.setu.ui.main.MainActivity$$ExternalSyntheticLambda1
            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return MainActivity.onCreate$lambda$1(this.f$0, menuItem);
            }
        });
        ActivityMainBinding activityMainBinding4 = this.binding;
        if (activityMainBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding4 = null;
        }
        activityMainBinding4.btnSOS.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.main.MainActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.onCreate$lambda$2(this.f$0, view);
            }
        });
        ActivityMainBinding activityMainBinding5 = this.binding;
        if (activityMainBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding5 = null;
        }
        activityMainBinding5.cardSafeCircle.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.main.MainActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.onCreate$lambda$3(this.f$0, view);
            }
        });
        ActivityMainBinding activityMainBinding6 = this.binding;
        if (activityMainBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMainBinding2 = activityMainBinding6;
        }
        activityMainBinding2.cardVolunteer.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.main.MainActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MainActivity.onCreate$lambda$4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$1(MainActivity this$0, MenuItem item) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int itemId = item.getItemId();
        if (itemId == R.id.action_profile) {
            this$0.startActivity(new Intent(this$0, (Class<?>) ProfileActivity.class));
            return true;
        }
        if (itemId == R.id.action_history) {
            Toast.makeText(this$0, "History Feature Coming Soon", 0).show();
            return true;
        }
        if (itemId != R.id.action_about) {
            return false;
        }
        Toast.makeText(this$0, "Suraksha-Setu v1.0", 0).show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.isEmergencyActive) {
            this$0.showSOSCountdown();
        } else {
            this$0.stopEmergency();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, (Class<?>) SafeCircleActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$4(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, (Class<?>) VolunteerActivity.class));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final void checkPermissions() {
        List permissions = CollectionsKt.mutableListOf("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION");
        if (Build.VERSION.SDK_INT >= 33) {
            permissions.add("android.permission.POST_NOTIFICATIONS");
        }
        List $this$toTypedArray$iv = permissions;
        this.requestPermissionsLauncher.launch($this$toTypedArray$iv.toArray(new String[0]));
    }

    private final void startLiveLocationUpdates() {
        LocationHelper locationHelper = this.locationHelper;
        if (locationHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationHelper");
            locationHelper = null;
        }
        locationHelper.getLastLocation(new Function1<Location, Unit>() { // from class: com.suraksha.setu.ui.main.MainActivity.startLiveLocationUpdates.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Location location) {
                invoke2(location);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Location location) {
                if (location != null) {
                    MainActivity.this.updateLocationUI(location);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLocationUI(Location location) {
        String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        ActivityMainBinding activityMainBinding = this.binding;
        ActivityMainBinding activityMainBinding2 = null;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        TextView textView = activityMainBinding.tvLiveLocationStatus;
        StringBuilder sbAppend = new StringBuilder().append("Lat: ");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%.4f", Arrays.copyOf(new Object[]{Double.valueOf(location.getLatitude())}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        StringBuilder sbAppend2 = sbAppend.append(str).append(", Lon: ");
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String str2 = String.format("%.4f", Arrays.copyOf(new Object[]{Double.valueOf(location.getLongitude())}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
        textView.setText(sbAppend2.append(str2).toString());
        ActivityMainBinding activityMainBinding3 = this.binding;
        if (activityMainBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding3 = null;
        }
        activityMainBinding3.tvLastUpdated.setText("Last Updated: " + time);
        ActivityMainBinding activityMainBinding4 = this.binding;
        if (activityMainBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMainBinding2 = activityMainBinding4;
        }
        activityMainBinding2.tvLastUpdated.setVisibility(0);
    }

    /* JADX WARN: Type inference failed for: r3v6, types: [com.suraksha.setu.ui.main.MainActivity$showSOSCountdown$1] */
    private final void showSOSCountdown() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_sos_countdown, (ViewGroup) null);
        final TextView tvCountdown = (TextView) dialogView.findViewById(R.id.tvCountdown);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(dialogView).setCancelable(false).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() { // from class: com.suraksha.setu.ui.main.MainActivity$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.showSOSCountdown$lambda$5(this.f$0, dialogInterface, i);
            }
        }).create();
        Intrinsics.checkNotNullExpressionValue(dialog, "create(...)");
        dialog.show();
        this.countDownTimer = new CountDownTimer() { // from class: com.suraksha.setu.ui.main.MainActivity.showSOSCountdown.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(5000L, 1000L);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long millisUntilFinished) {
                tvCountdown.setText(String.valueOf(millisUntilFinished / ((long) 1000)));
                this.vibrateShort();
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                dialog.dismiss();
                this.startEmergency();
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSOSCountdown$lambda$5(MainActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CountDownTimer countDownTimer = this$0.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startEmergency() {
        this.isEmergencyActive = true;
        ActivityMainBinding activityMainBinding = this.binding;
        ActivityMainBinding activityMainBinding2 = null;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        activityMainBinding.btnSOS.setText("STOP SOS");
        ActivityMainBinding activityMainBinding3 = this.binding;
        if (activityMainBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMainBinding2 = activityMainBinding3;
        }
        activityMainBinding2.getRoot().setBackgroundColor(ContextCompat.getColor(this, R.color.safety_red));
        Intent serviceIntent = new Intent(this, (Class<?>) SOSService.class);
        ContextCompat.startForegroundService(this, serviceIntent);
        Toast.makeText(this, "SOS Alert Sent to Safe Circle & Volunteers!", 1).show();
    }

    private final void stopEmergency() {
        this.isEmergencyActive = false;
        ActivityMainBinding activityMainBinding = this.binding;
        ActivityMainBinding activityMainBinding2 = null;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        activityMainBinding.btnSOS.setText("SOS");
        ActivityMainBinding activityMainBinding3 = this.binding;
        if (activityMainBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMainBinding2 = activityMainBinding3;
        }
        activityMainBinding2.getRoot().setBackgroundColor(ContextCompat.getColor(this, R.color.grey_100));
        stopService(new Intent(this, (Class<?>) SOSService.class));
        Toast.makeText(this, "Emergency Session Resolved", 0).show();
    }

    private final void loadUserProfile() {
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
        final Function1<DocumentSnapshot, Unit> function1 = new Function1<DocumentSnapshot, Unit>() { // from class: com.suraksha.setu.ui.main.MainActivity.loadUserProfile.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DocumentSnapshot documentSnapshot) {
                invoke2(documentSnapshot);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DocumentSnapshot document) {
                if (document != null) {
                    String name = document.getString("fullName");
                    if (name == null) {
                        name = "User";
                    }
                    String village = document.getString("villageName");
                    if (village == null) {
                        village = "Unknown";
                    }
                    ActivityMainBinding activityMainBinding = MainActivity.this.binding;
                    ActivityMainBinding activityMainBinding2 = null;
                    if (activityMainBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMainBinding = null;
                    }
                    activityMainBinding.tvUserName.setText("Welcome, " + name);
                    ActivityMainBinding activityMainBinding3 = MainActivity.this.binding;
                    if (activityMainBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityMainBinding2 = activityMainBinding3;
                    }
                    activityMainBinding2.tvVillage.setText("Village: " + village);
                }
            }
        };
        task.addOnSuccessListener(new OnSuccessListener() { // from class: com.suraksha.setu.ui.main.MainActivity$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                MainActivity.loadUserProfile$lambda$6(function1, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadUserProfile$lambda$6(Function1 tmp0, Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void vibrateShort() {
        Object systemService = getSystemService("vibrator");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.Vibrator");
        Vibrator vibrator = (Vibrator) systemService;
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(100L, -1));
        } else {
            vibrator.vibrate(100L);
        }
    }
}
