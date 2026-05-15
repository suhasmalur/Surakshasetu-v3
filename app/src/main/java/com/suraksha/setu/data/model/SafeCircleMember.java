package com.suraksha.setu.data.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeCircleMember.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003JE\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/suraksha/setu/data/model/SafeCircleMember;", "", "id", "", "fullName", "relationship", "contactNumber", "priority", "notes", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getContactNumber", "()Ljava/lang/String;", "getFullName", "getId", "getNotes", "getPriority", "getRelationship", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final /* data */ class SafeCircleMember {
    private final String contactNumber;
    private final String fullName;
    private final String id;
    private final String notes;
    private final String priority;
    private final String relationship;

    public SafeCircleMember() {
        this(null, null, null, null, null, null, 63, null);
    }

    public static /* synthetic */ SafeCircleMember copy$default(SafeCircleMember safeCircleMember, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = safeCircleMember.id;
        }
        if ((i & 2) != 0) {
            str2 = safeCircleMember.fullName;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = safeCircleMember.relationship;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = safeCircleMember.contactNumber;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = safeCircleMember.priority;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = safeCircleMember.notes;
        }
        return safeCircleMember.copy(str, str7, str8, str9, str10, str6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getFullName() {
        return this.fullName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRelationship() {
        return this.relationship;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getContactNumber() {
        return this.contactNumber;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPriority() {
        return this.priority;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getNotes() {
        return this.notes;
    }

    public final SafeCircleMember copy(String id, String fullName, String relationship, String contactNumber, String priority, String notes) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(relationship, "relationship");
        Intrinsics.checkNotNullParameter(contactNumber, "contactNumber");
        Intrinsics.checkNotNullParameter(priority, "priority");
        Intrinsics.checkNotNullParameter(notes, "notes");
        return new SafeCircleMember(id, fullName, relationship, contactNumber, priority, notes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SafeCircleMember)) {
            return false;
        }
        SafeCircleMember safeCircleMember = (SafeCircleMember) other;
        return Intrinsics.areEqual(this.id, safeCircleMember.id) && Intrinsics.areEqual(this.fullName, safeCircleMember.fullName) && Intrinsics.areEqual(this.relationship, safeCircleMember.relationship) && Intrinsics.areEqual(this.contactNumber, safeCircleMember.contactNumber) && Intrinsics.areEqual(this.priority, safeCircleMember.priority) && Intrinsics.areEqual(this.notes, safeCircleMember.notes);
    }

    public int hashCode() {
        return (((((((((this.id.hashCode() * 31) + this.fullName.hashCode()) * 31) + this.relationship.hashCode()) * 31) + this.contactNumber.hashCode()) * 31) + this.priority.hashCode()) * 31) + this.notes.hashCode();
    }

    public String toString() {
        return "SafeCircleMember(id=" + this.id + ", fullName=" + this.fullName + ", relationship=" + this.relationship + ", contactNumber=" + this.contactNumber + ", priority=" + this.priority + ", notes=" + this.notes + ')';
    }

    public SafeCircleMember(String id, String fullName, String relationship, String contactNumber, String priority, String notes) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(relationship, "relationship");
        Intrinsics.checkNotNullParameter(contactNumber, "contactNumber");
        Intrinsics.checkNotNullParameter(priority, "priority");
        Intrinsics.checkNotNullParameter(notes, "notes");
        this.id = id;
        this.fullName = fullName;
        this.relationship = relationship;
        this.contactNumber = contactNumber;
        this.priority = priority;
        this.notes = notes;
    }

    public /* synthetic */ SafeCircleMember(String str, String str2, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "Medium" : str5, (i & 32) != 0 ? "" : str6);
    }

    public final String getId() {
        return this.id;
    }

    public final String getFullName() {
        return this.fullName;
    }

    public final String getRelationship() {
        return this.relationship;
    }

    public final String getContactNumber() {
        return this.contactNumber;
    }

    public final String getPriority() {
        return this.priority;
    }

    public final String getNotes() {
        return this.notes;
    }
}
