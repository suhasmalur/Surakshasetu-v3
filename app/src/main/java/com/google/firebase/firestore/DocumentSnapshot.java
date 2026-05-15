package com.google.firebase.firestore;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.CustomClassMapper;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.v1.Value;
import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
public class DocumentSnapshot {
    private final Document doc;
    private final FirebaseFirestore firestore;
    private final DocumentKey key;
    private final SnapshotMetadata metadata;

    public enum ServerTimestampBehavior {
        NONE,
        ESTIMATE,
        PREVIOUS;

        static final ServerTimestampBehavior DEFAULT = NONE;
    }

    DocumentSnapshot(FirebaseFirestore firestore, DocumentKey key, Document doc, boolean isFromCache, boolean hasPendingWrites) {
        this.firestore = (FirebaseFirestore) Preconditions.checkNotNull(firestore);
        this.key = (DocumentKey) Preconditions.checkNotNull(key);
        this.doc = doc;
        this.metadata = new SnapshotMetadata(hasPendingWrites, isFromCache);
    }

    static DocumentSnapshot fromDocument(FirebaseFirestore firestore, Document doc, boolean fromCache, boolean hasPendingWrites) {
        return new DocumentSnapshot(firestore, doc.getKey(), doc, fromCache, hasPendingWrites);
    }

    static DocumentSnapshot fromNoDocument(FirebaseFirestore firestore, DocumentKey key, boolean fromCache) {
        return new DocumentSnapshot(firestore, key, null, fromCache, false);
    }

    public String getId() {
        return this.key.getDocumentId();
    }

    public SnapshotMetadata getMetadata() {
        return this.metadata;
    }

    public boolean exists() {
        return this.doc != null;
    }

    Document getDocument() {
        return this.doc;
    }

    public Map<String, Object> getData() {
        return getData(ServerTimestampBehavior.DEFAULT);
    }

    public Map<String, Object> getData(ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        UserDataWriter userDataWriter = new UserDataWriter(this.firestore, serverTimestampBehavior);
        if (this.doc == null) {
            return null;
        }
        return userDataWriter.convertObject(this.doc.getData().getFieldsMap());
    }

    public <T> T toObject(Class<T> cls) {
        return (T) toObject(cls, ServerTimestampBehavior.DEFAULT);
    }

    public <T> T toObject(Class<T> cls, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(cls, "Provided POJO type must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Map<String, Object> data = getData(serverTimestampBehavior);
        if (data == null) {
            return null;
        }
        return (T) CustomClassMapper.convertToCustomClass(data, cls, getReference());
    }

    public boolean contains(String field) {
        return contains(FieldPath.fromDotSeparatedPath(field));
    }

    public boolean contains(FieldPath fieldPath) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        return (this.doc == null || this.doc.getField(fieldPath.getInternalPath()) == null) ? false : true;
    }

    public Object get(String field) {
        return get(FieldPath.fromDotSeparatedPath(field), ServerTimestampBehavior.DEFAULT);
    }

    public Object get(String field, ServerTimestampBehavior serverTimestampBehavior) {
        return get(FieldPath.fromDotSeparatedPath(field), serverTimestampBehavior);
    }

    public Object get(FieldPath fieldPath) {
        return get(fieldPath, ServerTimestampBehavior.DEFAULT);
    }

    public Object get(FieldPath fieldPath, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        return getInternal(fieldPath.getInternalPath(), serverTimestampBehavior);
    }

    public <T> T get(String str, Class<T> cls) {
        return (T) get(FieldPath.fromDotSeparatedPath(str), cls, ServerTimestampBehavior.DEFAULT);
    }

    public <T> T get(String str, Class<T> cls, ServerTimestampBehavior serverTimestampBehavior) {
        return (T) get(FieldPath.fromDotSeparatedPath(str), cls, serverTimestampBehavior);
    }

    public <T> T get(FieldPath fieldPath, Class<T> cls) {
        return (T) get(fieldPath, cls, ServerTimestampBehavior.DEFAULT);
    }

    public <T> T get(FieldPath fieldPath, Class<T> cls, ServerTimestampBehavior serverTimestampBehavior) {
        Object obj = get(fieldPath, serverTimestampBehavior);
        if (obj == null) {
            return null;
        }
        return (T) CustomClassMapper.convertToCustomClass(obj, cls, getReference());
    }

    public Boolean getBoolean(String field) {
        return (Boolean) getTypedValue(field, Boolean.class);
    }

    public Double getDouble(String field) {
        Number val = (Number) getTypedValue(field, Number.class);
        if (val != null) {
            return Double.valueOf(val.doubleValue());
        }
        return null;
    }

    public String getString(String field) {
        return (String) getTypedValue(field, String.class);
    }

    public Long getLong(String field) {
        Number val = (Number) getTypedValue(field, Number.class);
        if (val != null) {
            return Long.valueOf(val.longValue());
        }
        return null;
    }

    public Date getDate(String field) {
        return getDate(field, ServerTimestampBehavior.DEFAULT);
    }

    public Date getDate(String field, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(field, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Timestamp timestamp = getTimestamp(field, serverTimestampBehavior);
        if (timestamp != null) {
            return timestamp.toDate();
        }
        return null;
    }

    public Timestamp getTimestamp(String field) {
        return getTimestamp(field, ServerTimestampBehavior.DEFAULT);
    }

    public Timestamp getTimestamp(String field, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(field, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Object maybeTimestamp = getInternal(FieldPath.fromDotSeparatedPath(field).getInternalPath(), serverTimestampBehavior);
        return (Timestamp) castTypedValue(maybeTimestamp, field, Timestamp.class);
    }

    public Blob getBlob(String field) {
        return (Blob) getTypedValue(field, Blob.class);
    }

    public GeoPoint getGeoPoint(String field) {
        return (GeoPoint) getTypedValue(field, GeoPoint.class);
    }

    public DocumentReference getDocumentReference(String field) {
        return (DocumentReference) getTypedValue(field, DocumentReference.class);
    }

    public DocumentReference getReference() {
        return new DocumentReference(this.key, this.firestore);
    }

    private <T> T getTypedValue(String str, Class<T> cls) {
        Preconditions.checkNotNull(str, "Provided field must not be null.");
        return (T) castTypedValue(get(str, ServerTimestampBehavior.DEFAULT), str, cls);
    }

    private <T> T castTypedValue(Object value, String field, Class<T> clazz) {
        if (value == null) {
            return null;
        }
        if (!clazz.isInstance(value)) {
            throw new RuntimeException("Field '" + field + "' is not a " + clazz.getName());
        }
        return clazz.cast(value);
    }

    private Object getInternal(com.google.firebase.firestore.model.FieldPath fieldPath, ServerTimestampBehavior serverTimestampBehavior) {
        Value val;
        if (this.doc != null && (val = this.doc.getField(fieldPath)) != null) {
            UserDataWriter userDataWriter = new UserDataWriter(this.firestore, serverTimestampBehavior);
            return userDataWriter.convertValue(val);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DocumentSnapshot)) {
            return false;
        }
        DocumentSnapshot other = (DocumentSnapshot) obj;
        return this.firestore.equals(other.firestore) && this.key.equals(other.key) && (this.doc != null ? this.doc.equals(other.doc) : other.doc == null) && this.metadata.equals(other.metadata);
    }

    public int hashCode() {
        int hash = this.firestore.hashCode();
        return (((((((hash * 31) + this.key.hashCode()) * 31) + (this.doc != null ? this.doc.getKey().hashCode() : 0)) * 31) + (this.doc != null ? this.doc.getData().hashCode() : 0)) * 31) + this.metadata.hashCode();
    }

    public String toString() {
        return "DocumentSnapshot{key=" + this.key + ", metadata=" + this.metadata + ", doc=" + this.doc + '}';
    }
}
