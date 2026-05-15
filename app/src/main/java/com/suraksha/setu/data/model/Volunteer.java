package com.suraksha.setu.data.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.Timestamp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Volunteer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\"\b\u0086\b\u0018\u00002\u00020\u0001B}\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u000fHÆ\u0003J\t\u0010%\u001a\u00020\u000fHÆ\u0003J\t\u0010&\u001a\u00020\u0012HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\bHÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u000bHÆ\u0003J\t\u0010-\u001a\u00020\u000bHÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\u0081\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u0012HÆ\u0001J\u0013\u00100\u001a\u00020\u000b2\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u00020\bHÖ\u0001J\t\u00103\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u001aR\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001aR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017¨\u00064"}, d2 = {"Lcom/suraksha/setu/data/model/Volunteer;", "", "id", "", "fullName", "phoneNumber", "villageName", "age", "", "gender", "isAvailable", "", "isVerified", "skills", "latitude", "", "longitude", "lastActive", "Lcom/google/firebase/Timestamp;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ZZLjava/lang/String;DDLcom/google/firebase/Timestamp;)V", "getAge", "()I", "getFullName", "()Ljava/lang/String;", "getGender", "getId", "()Z", "getLastActive", "()Lcom/google/firebase/Timestamp;", "getLatitude", "()D", "getLongitude", "getPhoneNumber", "getSkills", "getVillageName", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final /* data */ class Volunteer {
    private final int age;
    private final String fullName;
    private final String gender;
    private final String id;
    private final boolean isAvailable;
    private final boolean isVerified;
    private final Timestamp lastActive;
    private final double latitude;
    private final double longitude;
    private final String phoneNumber;
    private final String skills;
    private final String villageName;

    public Volunteer() {
        this(null, null, null, null, 0, null, false, false, null, 0.0d, 0.0d, null, 4095, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final double getLatitude() {
        return this.latitude;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final double getLongitude() {
        return this.longitude;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final Timestamp getLastActive() {
        return this.lastActive;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getFullName() {
        return this.fullName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getVillageName() {
        return this.villageName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getAge() {
        return this.age;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getGender() {
        return this.gender;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getIsAvailable() {
        return this.isAvailable;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getIsVerified() {
        return this.isVerified;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getSkills() {
        return this.skills;
    }

    public final Volunteer copy(String id, String fullName, String phoneNumber, String villageName, int age, String gender, boolean isAvailable, boolean isVerified, String skills, double latitude, double longitude, Timestamp lastActive) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        Intrinsics.checkNotNullParameter(villageName, "villageName");
        Intrinsics.checkNotNullParameter(gender, "gender");
        Intrinsics.checkNotNullParameter(skills, "skills");
        Intrinsics.checkNotNullParameter(lastActive, "lastActive");
        return new Volunteer(id, fullName, phoneNumber, villageName, age, gender, isAvailable, isVerified, skills, latitude, longitude, lastActive);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Volunteer)) {
            return false;
        }
        Volunteer volunteer = (Volunteer) other;
        return Intrinsics.areEqual(this.id, volunteer.id) && Intrinsics.areEqual(this.fullName, volunteer.fullName) && Intrinsics.areEqual(this.phoneNumber, volunteer.phoneNumber) && Intrinsics.areEqual(this.villageName, volunteer.villageName) && this.age == volunteer.age && Intrinsics.areEqual(this.gender, volunteer.gender) && this.isAvailable == volunteer.isAvailable && this.isVerified == volunteer.isVerified && Intrinsics.areEqual(this.skills, volunteer.skills) && Double.compare(this.latitude, volunteer.latitude) == 0 && Double.compare(this.longitude, volunteer.longitude) == 0 && Intrinsics.areEqual(this.lastActive, volunteer.lastActive);
    }

    public int hashCode() {
        return (((((((((((((((((((((this.id.hashCode() * 31) + this.fullName.hashCode()) * 31) + this.phoneNumber.hashCode()) * 31) + this.villageName.hashCode()) * 31) + Integer.hashCode(this.age)) * 31) + this.gender.hashCode()) * 31) + Boolean.hashCode(this.isAvailable)) * 31) + Boolean.hashCode(this.isVerified)) * 31) + this.skills.hashCode()) * 31) + Double.hashCode(this.latitude)) * 31) + Double.hashCode(this.longitude)) * 31) + this.lastActive.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Volunteer(id=").append(this.id).append(", fullName=").append(this.fullName).append(", phoneNumber=").append(this.phoneNumber).append(", villageName=").append(this.villageName).append(", age=").append(this.age).append(", gender=").append(this.gender).append(", isAvailable=").append(this.isAvailable).append(", isVerified=").append(this.isVerified).append(", skills=").append(this.skills).append(", latitude=").append(this.latitude).append(", longitude=").append(this.longitude).append(", lastActive=");
        sb.append(this.lastActive).append(')');
        return sb.toString();
    }

    public Volunteer(String id, String fullName, String phoneNumber, String villageName, int age, String gender, boolean isAvailable, boolean isVerified, String skills, double latitude, double longitude, Timestamp lastActive) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        Intrinsics.checkNotNullParameter(villageName, "villageName");
        Intrinsics.checkNotNullParameter(gender, "gender");
        Intrinsics.checkNotNullParameter(skills, "skills");
        Intrinsics.checkNotNullParameter(lastActive, "lastActive");
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.villageName = villageName;
        this.age = age;
        this.gender = gender;
        this.isAvailable = isAvailable;
        this.isVerified = isVerified;
        this.skills = skills;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lastActive = lastActive;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ Volunteer(String str, String str2, String str3, String str4, int i, String str5, boolean z, boolean z2, String str6, double d, double d2, Timestamp timestamp, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        Timestamp timestampNow;
        String str7 = (i2 & 1) != 0 ? "" : str;
        String str8 = (i2 & 2) != 0 ? "" : str2;
        String str9 = (i2 & 4) != 0 ? "" : str3;
        String str10 = (i2 & 8) != 0 ? "" : str4;
        int i3 = (i2 & 16) != 0 ? 0 : i;
        String str11 = (i2 & 32) != 0 ? "" : str5;
        boolean z3 = (i2 & 64) != 0 ? false : z;
        boolean z4 = (i2 & 128) == 0 ? z2 : false;
        String str12 = (i2 & 256) == 0 ? str6 : "";
        double d3 = (i2 & 512) != 0 ? 0.0d : d;
        double d4 = (i2 & 1024) == 0 ? d2 : 0.0d;
        if ((i2 & 2048) != 0) {
            timestampNow = Timestamp.now();
            Intrinsics.checkNotNullExpressionValue(timestampNow, "now(...)");
        } else {
            timestampNow = timestamp;
        }
        this(str7, str8, str9, str10, i3, str11, z3, z4, str12, d3, d4, timestampNow);
    }

    public final String getId() {
        return this.id;
    }

    public final String getFullName() {
        return this.fullName;
    }

    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    public final String getVillageName() {
        return this.villageName;
    }

    public final int getAge() {
        return this.age;
    }

    public final String getGender() {
        return this.gender;
    }

    public final boolean isAvailable() {
        return this.isAvailable;
    }

    public final boolean isVerified() {
        return this.isVerified;
    }

    public final String getSkills() {
        return this.skills;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final Timestamp getLastActive() {
        return this.lastActive;
    }
}
