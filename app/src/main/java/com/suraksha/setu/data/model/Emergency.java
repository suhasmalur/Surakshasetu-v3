package com.suraksha.setu.data.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.firebase.Timestamp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Emergency.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003JY\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006("}, d2 = {"Lcom/suraksha/setu/data/model/Emergency;", "", "id", "", "userId", "userName", "timestamp", "Lcom/google/firebase/Timestamp;", "latitude", "", "longitude", NotificationCompat.CATEGORY_STATUS, "message", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/firebase/Timestamp;DDLjava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getLatitude", "()D", "getLongitude", "getMessage", "getStatus", "getTimestamp", "()Lcom/google/firebase/Timestamp;", "getUserId", "getUserName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final /* data */ class Emergency {
    private final String id;
    private final double latitude;
    private final double longitude;
    private final String message;
    private final String status;
    private final Timestamp timestamp;
    private final String userId;
    private final String userName;

    public Emergency() {
        this(null, null, null, null, 0.0d, 0.0d, null, null, 255, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getUserName() {
        return this.userName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Timestamp getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final double getLatitude() {
        return this.latitude;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final double getLongitude() {
        return this.longitude;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final Emergency copy(String id, String userId, String userName, Timestamp timestamp, double latitude, double longitude, String status, String message) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(message, "message");
        return new Emergency(id, userId, userName, timestamp, latitude, longitude, status, message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Emergency)) {
            return false;
        }
        Emergency emergency = (Emergency) other;
        return Intrinsics.areEqual(this.id, emergency.id) && Intrinsics.areEqual(this.userId, emergency.userId) && Intrinsics.areEqual(this.userName, emergency.userName) && Intrinsics.areEqual(this.timestamp, emergency.timestamp) && Double.compare(this.latitude, emergency.latitude) == 0 && Double.compare(this.longitude, emergency.longitude) == 0 && Intrinsics.areEqual(this.status, emergency.status) && Intrinsics.areEqual(this.message, emergency.message);
    }

    public int hashCode() {
        return (((((((((((((this.id.hashCode() * 31) + this.userId.hashCode()) * 31) + this.userName.hashCode()) * 31) + this.timestamp.hashCode()) * 31) + Double.hashCode(this.latitude)) * 31) + Double.hashCode(this.longitude)) * 31) + this.status.hashCode()) * 31) + this.message.hashCode();
    }

    public String toString() {
        return "Emergency(id=" + this.id + ", userId=" + this.userId + ", userName=" + this.userName + ", timestamp=" + this.timestamp + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", status=" + this.status + ", message=" + this.message + ')';
    }

    public Emergency(String id, String userId, String userName, Timestamp timestamp, double latitude, double longitude, String status, String message) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(message, "message");
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.message = message;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ Emergency(String str, String str2, String str3, Timestamp timestamp, double d, double d2, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Timestamp timestampNow;
        String str6 = (i & 1) != 0 ? "" : str;
        String str7 = (i & 2) != 0 ? "" : str2;
        String str8 = (i & 4) != 0 ? "" : str3;
        if ((i & 8) != 0) {
            timestampNow = Timestamp.now();
            Intrinsics.checkNotNullExpressionValue(timestampNow, "now(...)");
        } else {
            timestampNow = timestamp;
        }
        this(str6, str7, str8, timestampNow, (i & 16) != 0 ? 0.0d : d, (i & 32) == 0 ? d2 : 0.0d, (i & 64) != 0 ? "ACTIVE" : str4, (i & 128) == 0 ? str5 : "");
    }

    public final String getId() {
        return this.id;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getUserName() {
        return this.userName;
    }

    public final Timestamp getTimestamp() {
        return this.timestamp;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getMessage() {
        return this.message;
    }
}
