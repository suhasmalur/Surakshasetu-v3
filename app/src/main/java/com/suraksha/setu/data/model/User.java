package com.suraksha.setu.data.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: User.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003JE\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/suraksha/setu/data/model/User;", "", "uid", "", "fullName", "phoneNumber", "email", "villageName", "gender", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getFullName", "getGender", "getPhoneNumber", "getUid", "getVillageName", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final /* data */ class User {
    private final String email;
    private final String fullName;
    private final String gender;
    private final String phoneNumber;
    private final String uid;
    private final String villageName;

    public User() {
        this(null, null, null, null, null, null, 63, null);
    }

    public static /* synthetic */ User copy$default(User user, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = user.uid;
        }
        if ((i & 2) != 0) {
            str2 = user.fullName;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = user.phoneNumber;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = user.email;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = user.villageName;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = user.gender;
        }
        return user.copy(str, str7, str8, str9, str10, str6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUid() {
        return this.uid;
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
    public final String getEmail() {
        return this.email;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getVillageName() {
        return this.villageName;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getGender() {
        return this.gender;
    }

    public final User copy(String uid, String fullName, String phoneNumber, String email, String villageName, String gender) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(villageName, "villageName");
        Intrinsics.checkNotNullParameter(gender, "gender");
        return new User(uid, fullName, phoneNumber, email, villageName, gender);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User user = (User) other;
        return Intrinsics.areEqual(this.uid, user.uid) && Intrinsics.areEqual(this.fullName, user.fullName) && Intrinsics.areEqual(this.phoneNumber, user.phoneNumber) && Intrinsics.areEqual(this.email, user.email) && Intrinsics.areEqual(this.villageName, user.villageName) && Intrinsics.areEqual(this.gender, user.gender);
    }

    public int hashCode() {
        return (((((((((this.uid.hashCode() * 31) + this.fullName.hashCode()) * 31) + this.phoneNumber.hashCode()) * 31) + this.email.hashCode()) * 31) + this.villageName.hashCode()) * 31) + this.gender.hashCode();
    }

    public String toString() {
        return "User(uid=" + this.uid + ", fullName=" + this.fullName + ", phoneNumber=" + this.phoneNumber + ", email=" + this.email + ", villageName=" + this.villageName + ", gender=" + this.gender + ')';
    }

    public User(String uid, String fullName, String phoneNumber, String email, String villageName, String gender) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(villageName, "villageName");
        Intrinsics.checkNotNullParameter(gender, "gender");
        this.uid = uid;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.villageName = villageName;
        this.gender = gender;
    }

    public /* synthetic */ User(String str, String str2, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? "" : str6);
    }

    public final String getUid() {
        return this.uid;
    }

    public final String getFullName() {
        return this.fullName;
    }

    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getVillageName() {
        return this.villageName;
    }

    public final String getGender() {
        return this.gender;
    }
}
