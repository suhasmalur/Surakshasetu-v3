package com.suraksha.setu.ui.volunteer;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.suraksha.setu.R;
import com.suraksha.setu.databinding.ActivityEmergencyMapBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EmergencyMapActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0007H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/suraksha/setu/ui/volunteer/EmergencyMapActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "()V", "binding", "Lcom/suraksha/setu/databinding/ActivityEmergencyMapBinding;", "mMap", "Lcom/google/android/gms/maps/GoogleMap;", "victimLat", "", "victimLon", "victimName", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMapReady", "googleMap", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class EmergencyMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ActivityEmergencyMapBinding binding;
    private GoogleMap mMap;
    private double victimLat;
    private double victimLon;
    private String victimName = "Victim";

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEmergencyMapBinding activityEmergencyMapBindingInflate = ActivityEmergencyMapBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityEmergencyMapBindingInflate, "inflate(...)");
        this.binding = activityEmergencyMapBindingInflate;
        ActivityEmergencyMapBinding activityEmergencyMapBinding = this.binding;
        ActivityEmergencyMapBinding activityEmergencyMapBinding2 = null;
        if (activityEmergencyMapBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEmergencyMapBinding = null;
        }
        setContentView(activityEmergencyMapBinding.getRoot());
        this.victimLat = getIntent().getDoubleExtra("LAT", 0.0d);
        this.victimLon = getIntent().getDoubleExtra("LON", 0.0d);
        String stringExtra = getIntent().getStringExtra("NAME");
        if (stringExtra == null) {
            stringExtra = "Victim";
        }
        this.victimName = stringExtra;
        ActivityEmergencyMapBinding activityEmergencyMapBinding3 = this.binding;
        if (activityEmergencyMapBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEmergencyMapBinding3 = null;
        }
        activityEmergencyMapBinding3.tvMapVictimName.setText("Victim: " + this.victimName);
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(R.id.map);
        Intrinsics.checkNotNull(fragmentFindFragmentById, "null cannot be cast to non-null type com.google.android.gms.maps.SupportMapFragment");
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentFindFragmentById;
        mapFragment.getMapAsync(this);
        ActivityEmergencyMapBinding activityEmergencyMapBinding4 = this.binding;
        if (activityEmergencyMapBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityEmergencyMapBinding2 = activityEmergencyMapBinding4;
        }
        activityEmergencyMapBinding2.btnMarkResolved.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.volunteer.EmergencyMapActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EmergencyMapActivity.onCreate$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(EmergencyMapActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(GoogleMap googleMap) {
        Intrinsics.checkNotNullParameter(googleMap, "googleMap");
        this.mMap = googleMap;
        LatLng victimLocation = new LatLng(this.victimLat, this.victimLon);
        GoogleMap googleMap2 = this.mMap;
        GoogleMap googleMap3 = null;
        if (googleMap2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMap");
            googleMap2 = null;
        }
        googleMap2.addMarker(new MarkerOptions().position(victimLocation).title(this.victimName));
        GoogleMap googleMap4 = this.mMap;
        if (googleMap4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMap");
            googleMap4 = null;
        }
        googleMap4.addCircle(new CircleOptions().center(victimLocation).radius(500.0d).strokeColor(getColor(R.color.safety_red)).fillColor(587137024));
        GoogleMap googleMap5 = this.mMap;
        if (googleMap5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mMap");
        } else {
            googleMap3 = googleMap5;
        }
        googleMap3.moveCamera(CameraUpdateFactory.newLatLngZoom(victimLocation, 15.0f));
    }
}
