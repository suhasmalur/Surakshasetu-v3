package com.suraksha.setu.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.suraksha.setu.data.model.Emergency;
import com.suraksha.setu.util.EmergencyNotificationHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SOSService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\"\u0010\u0018\u001a\u00020\u00192\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0019H\u0016J\b\u0010\u001c\u001a\u00020\u0016H\u0002J\b\u0010\u001d\u001a\u00020\u0016H\u0003J\u0018\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/suraksha/setu/service/SOSService;", "Landroid/app/Service;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "emergencyId", "", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "locationCallback", "Lcom/google/android/gms/location/LocationCallback;", "mediaPlayer", "Landroid/media/MediaPlayer;", "notificationHelper", "Lcom/suraksha/setu/util/EmergencyNotificationHelper;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "onStartCommand", "", "flags", "startId", "playSiren", "startLocationUpdates", "updateEmergencyInFirestore", "lat", "", "lon", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SOSService extends Service {
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private String emergencyId;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private MediaPlayer mediaPlayer;
    private EmergencyNotificationHelper notificationHelper;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(...)");
        this.fusedLocationClient = fusedLocationProviderClient;
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseFirestore, "getInstance(...)");
        this.db = firebaseFirestore;
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseAuth, "getInstance(...)");
        this.auth = firebaseAuth;
        this.notificationHelper = new EmergencyNotificationHelper(this);
        EmergencyNotificationHelper emergencyNotificationHelper = this.notificationHelper;
        if (emergencyNotificationHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notificationHelper");
            emergencyNotificationHelper = null;
        }
        startForeground(1001, emergencyNotificationHelper.getBaseNotificationBuilder().setContentText("Tracking live location...").build());
        playSiren();
        startLocationUpdates();
    }

    private final void playSiren() {
        try {
            Uri alert = RingtoneManager.getDefaultUri(4);
            this.mediaPlayer = MediaPlayer.create(this, alert);
            MediaPlayer mediaPlayer = this.mediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.setLooping(true);
            }
            MediaPlayer mediaPlayer2 = this.mediaPlayer;
            if (mediaPlayer2 != null) {
                mediaPlayer2.start();
            }
        } catch (Exception e) {
            Log.e("SOSService", "Error playing siren", e);
        }
    }

    private final void startLocationUpdates() {
        LocationRequest locationRequest = new LocationRequest.Builder(100, 5000L).setMinUpdateIntervalMillis(2000L).build();
        Intrinsics.checkNotNullExpressionValue(locationRequest, "build(...)");
        this.locationCallback = new LocationCallback() { // from class: com.suraksha.setu.service.SOSService.startLocationUpdates.1
            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationResult(LocationResult locationResult) {
                Intrinsics.checkNotNullParameter(locationResult, "locationResult");
                Location location = locationResult.getLastLocation();
                if (location != null) {
                    SOSService.this.updateEmergencyInFirestore(location.getLatitude(), location.getLongitude());
                }
            }
        };
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
        LocationCallback locationCallback = null;
        if (fusedLocationProviderClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fusedLocationClient");
            fusedLocationProviderClient = null;
        }
        LocationCallback locationCallback2 = this.locationCallback;
        if (locationCallback2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationCallback");
        } else {
            locationCallback = locationCallback2;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateEmergencyInFirestore(double lat, double lon) {
        String uid;
        String displayName;
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
        FirebaseAuth firebaseAuth2 = this.auth;
        if (firebaseAuth2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("auth");
            firebaseAuth2 = null;
        }
        FirebaseUser currentUser2 = firebaseAuth2.getCurrentUser();
        if (currentUser2 == null || (displayName = currentUser2.getDisplayName()) == null) {
            displayName = "User";
        }
        String name = displayName;
        if (this.emergencyId == null) {
            FirebaseFirestore firebaseFirestore2 = this.db;
            if (firebaseFirestore2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("db");
            } else {
                firebaseFirestore = firebaseFirestore2;
            }
            DocumentReference ref = firebaseFirestore.collection("emergencies").document();
            Intrinsics.checkNotNullExpressionValue(ref, "document(...)");
            this.emergencyId = ref.getId();
            String str = this.emergencyId;
            Intrinsics.checkNotNull(str);
            Timestamp timestampNow = Timestamp.now();
            Intrinsics.checkNotNull(timestampNow);
            Emergency emergency = new Emergency(str, uid, name, timestampNow, lat, lon, "ACTIVE", null, 128, null);
            ref.set(emergency);
            return;
        }
        FirebaseFirestore firebaseFirestore3 = this.db;
        if (firebaseFirestore3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("db");
        } else {
            firebaseFirestore = firebaseFirestore3;
        }
        CollectionReference collectionReferenceCollection = firebaseFirestore.collection("emergencies");
        String str2 = this.emergencyId;
        Intrinsics.checkNotNull(str2);
        collectionReferenceCollection.document(str2).update("latitude", Double.valueOf(lat), "longitude", Double.valueOf(lon), "timestamp", Timestamp.now());
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
        FirebaseFirestore firebaseFirestore = null;
        if (fusedLocationProviderClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fusedLocationClient");
            fusedLocationProviderClient = null;
        }
        LocationCallback locationCallback = this.locationCallback;
        if (locationCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationCallback");
            locationCallback = null;
        }
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 != null) {
            mediaPlayer2.release();
        }
        String it = this.emergencyId;
        if (it != null) {
            FirebaseFirestore firebaseFirestore2 = this.db;
            if (firebaseFirestore2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("db");
            } else {
                firebaseFirestore = firebaseFirestore2;
            }
            firebaseFirestore.collection("emergencies").document(it).update(NotificationCompat.CATEGORY_STATUS, "RESOLVED", new Object[0]);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }
}
