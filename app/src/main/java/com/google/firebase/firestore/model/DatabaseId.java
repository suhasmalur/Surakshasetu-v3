package com.google.firebase.firestore.model;

import com.google.firebase.firestore.util.Assert;

/* JADX INFO: loaded from: classes10.dex */
public final class DatabaseId implements Comparable<DatabaseId> {
    public static final String DEFAULT_DATABASE_ID = "(default)";
    public static final DatabaseId EMPTY = forDatabase("", "");
    private final String databaseId;
    private final String projectId;

    public static DatabaseId forProject(String projectId) {
        return forDatabase(projectId, "(default)");
    }

    public static DatabaseId forDatabase(String projectId, String databaseId) {
        return new DatabaseId(projectId, databaseId);
    }

    private DatabaseId(String projectId, String databaseId) {
        this.projectId = projectId;
        this.databaseId = databaseId;
    }

    public static DatabaseId fromName(String name) {
        ResourcePath resourceName = ResourcePath.fromString(name);
        boolean z = false;
        if (resourceName.length() > 3 && resourceName.getSegment(0).equals("projects") && resourceName.getSegment(2).equals("databases")) {
            z = true;
        }
        Assert.hardAssert(z, "Tried to parse an invalid resource name: %s", resourceName);
        return new DatabaseId(resourceName.getSegment(1), resourceName.getSegment(3));
    }

    public String getProjectId() {
        return this.projectId;
    }

    public String getDatabaseId() {
        return this.databaseId;
    }

    public String toString() {
        return "DatabaseId(" + this.projectId + ", " + this.databaseId + ")";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DatabaseId that = (DatabaseId) o;
        if (this.projectId.equals(that.projectId) && this.databaseId.equals(that.databaseId)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = this.projectId.hashCode();
        return (result * 31) + this.databaseId.hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(DatabaseId other) {
        int cmp = this.projectId.compareTo(other.projectId);
        return cmp != 0 ? cmp : this.databaseId.compareTo(other.databaseId);
    }
}
