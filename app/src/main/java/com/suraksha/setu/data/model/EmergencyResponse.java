package com.suraksha.setu.data.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.firebase.Timestamp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: compiled from: Volunteer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006#"}, d2 = {"Lcom/suraksha/setu/data/model/EmergencyResponse;", "", "id", "", "emergencyId", "volunteerId", "volunteerName", NotificationCompat.CATEGORY_STATUS, "timestamp", "Lcom/google/firebase/Timestamp;", "eta", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/firebase/Timestamp;Ljava/lang/String;)V", "getEmergencyId", "()Ljava/lang/String;", "getEta", "getId", "getStatus", "getTimestamp", "()Lcom/google/firebase/Timestamp;", "getVolunteerId", "getVolunteerName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final /* data */ class EmergencyResponse {
    private final String emergencyId;
    private final String eta;
    private final String id;
    private final String status;
    private final Timestamp timestamp;
    private final String volunteerId;
    private final String volunteerName;

    public EmergencyResponse() {
        this(null, null, null, null, null, null, null, WorkQueueKt.MASK, null);
    }

    public static /* synthetic */ EmergencyResponse copy$default(EmergencyResponse emergencyResponse, String str, String str2, String str3, String str4, String str5, Timestamp timestamp, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = emergencyResponse.id;
        }
        if ((i & 2) != 0) {
            str2 = emergencyResponse.emergencyId;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = emergencyResponse.volunteerId;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = emergencyResponse.volunteerName;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = emergencyResponse.status;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            timestamp = emergencyResponse.timestamp;
        }
        Timestamp timestamp2 = timestamp;
        if ((i & 64) != 0) {
            str6 = emergencyResponse.eta;
        }
        return emergencyResponse.copy(str, str7, str8, str9, str10, timestamp2, str6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getEmergencyId() {
        return this.emergencyId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getVolunteerId() {
        return this.volunteerId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getVolunteerName() {
        return this.volunteerName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Timestamp getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getEta() {
        return this.eta;
    }

    public final EmergencyResponse copy(String id, String emergencyId, String volunteerId, String volunteerName, String status, Timestamp timestamp, String eta) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(emergencyId, "emergencyId");
        Intrinsics.checkNotNullParameter(volunteerId, "volunteerId");
        Intrinsics.checkNotNullParameter(volunteerName, "volunteerName");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(eta, "eta");
        return new EmergencyResponse(id, emergencyId, volunteerId, volunteerName, status, timestamp, eta);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EmergencyResponse)) {
            return false;
        }
        EmergencyResponse emergencyResponse = (EmergencyResponse) other;
        return Intrinsics.areEqual(this.id, emergencyResponse.id) && Intrinsics.areEqual(this.emergencyId, emergencyResponse.emergencyId) && Intrinsics.areEqual(this.volunteerId, emergencyResponse.volunteerId) && Intrinsics.areEqual(this.volunteerName, emergencyResponse.volunteerName) && Intrinsics.areEqual(this.status, emergencyResponse.status) && Intrinsics.areEqual(this.timestamp, emergencyResponse.timestamp) && Intrinsics.areEqual(this.eta, emergencyResponse.eta);
    }

    public int hashCode() {
        return (((((((((((this.id.hashCode() * 31) + this.emergencyId.hashCode()) * 31) + this.volunteerId.hashCode()) * 31) + this.volunteerName.hashCode()) * 31) + this.status.hashCode()) * 31) + this.timestamp.hashCode()) * 31) + this.eta.hashCode();
    }

    public String toString() {
        return "EmergencyResponse(id=" + this.id + ", emergencyId=" + this.emergencyId + ", volunteerId=" + this.volunteerId + ", volunteerName=" + this.volunteerName + ", status=" + this.status + ", timestamp=" + this.timestamp + ", eta=" + this.eta + ')';
    }

    public EmergencyResponse(String id, String emergencyId, String volunteerId, String volunteerName, String status, Timestamp timestamp, String eta) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(emergencyId, "emergencyId");
        Intrinsics.checkNotNullParameter(volunteerId, "volunteerId");
        Intrinsics.checkNotNullParameter(volunteerName, "volunteerName");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(eta, "eta");
        this.id = id;
        this.emergencyId = emergencyId;
        this.volunteerId = volunteerId;
        this.volunteerName = volunteerName;
        this.status = status;
        this.timestamp = timestamp;
        this.eta = eta;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ EmergencyResponse(String str, String str2, String str3, String str4, String str5, Timestamp timestamp, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Timestamp timestamp2;
        String str7 = (i & 1) != 0 ? "" : str;
        String str8 = (i & 2) != 0 ? "" : str2;
        String str9 = (i & 4) != 0 ? "" : str3;
        String str10 = (i & 8) != 0 ? "" : str4;
        String str11 = (i & 16) != 0 ? "PENDING" : str5;
        if ((i & 32) != 0) {
            Timestamp timestampNow = Timestamp.now();
            Intrinsics.checkNotNullExpressionValue(timestampNow, "now(...)");
            timestamp2 = timestampNow;
        } else {
            timestamp2 = timestamp;
        }
        this(str7, str8, str9, str10, str11, timestamp2, (i & 64) != 0 ? "" : str6);
    }

    public final String getId() {
        return this.id;
    }

    public final String getEmergencyId() {
        return this.emergencyId;
    }

    public final String getVolunteerId() {
        return this.volunteerId;
    }

    public final String getVolunteerName() {
        return this.volunteerName;
    }

    public final String getStatus() {
        return this.status;
    }

    public final Timestamp getTimestamp() {
        return this.timestamp;
    }

    public final String getEta() {
        return this.eta;
    }
}
