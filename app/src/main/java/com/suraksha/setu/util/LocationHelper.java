package com.suraksha.setu.util;

import android.content.Context;
import android.location.Location;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocationHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bJ\u001e\u0010\r\u001a\u00020\u000e2\u0014\u0010\u000f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0012\u0004\u0012\u00020\u000e0\u0010H\u0007J\u0016\u0010\u0012\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/suraksha/setu/util/LocationHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "formatEmergencyMessage", "", "name", "lat", "", "lon", "getLastLocation", "", "callback", "Lkotlin/Function1;", "Landroid/location/Location;", "getMapsLink", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class LocationHelper {
    private final Context context;
    private final FusedLocationProviderClient fusedLocationClient;

    public LocationHelper(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.context);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(...)");
        this.fusedLocationClient = fusedLocationProviderClient;
    }

    public final void getLastLocation(final Function1<? super Location, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Task<Location> lastLocation = this.fusedLocationClient.getLastLocation();
        final Function1<Location, Unit> function1 = new Function1<Location, Unit>() { // from class: com.suraksha.setu.util.LocationHelper.getLastLocation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                callback.invoke(location);
            }
        };
        lastLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.suraksha.setu.util.LocationHelper$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                LocationHelper.getLastLocation$lambda$0(function1, obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.suraksha.setu.util.LocationHelper$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                LocationHelper.getLastLocation$lambda$1(callback, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getLastLocation$lambda$0(Function1 tmp0, Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getLastLocation$lambda$1(Function1 callback, Exception it) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Intrinsics.checkNotNullParameter(it, "it");
        callback.invoke(null);
    }

    public final String getMapsLink(double lat, double lon) {
        return "https://maps.google.com/?q=" + lat + ',' + lon;
    }

    public final String formatEmergencyMessage(String name, double lat, double lon) {
        Intrinsics.checkNotNullParameter(name, "name");
        return "DANGER ALERT!\n\n" + name + " may be in danger.\n\nLive Location:\nLatitude: " + lat + "\nLongitude: " + lon + "\n\nGoogle Maps Link:\n" + getMapsLink(lat, lon) + "\n\nPlease reach immediately or contact emergency services.";
    }
}
