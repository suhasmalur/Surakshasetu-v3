package com.google.firebase.firestore;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseAppLifecycleListener;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.inject.Deferred;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes10.dex */
class FirestoreMultiDbComponent implements FirebaseAppLifecycleListener, FirebaseFirestore.InstanceRegistry {
    private final FirebaseApp app;
    private final Deferred<InteropAppCheckTokenProvider> appCheckProvider;
    private final Deferred<InternalAuthProvider> authProvider;
    private final Context context;
    private final Map<String, FirebaseFirestore> instances = new HashMap();
    private final GrpcMetadataProvider metadataProvider;

    FirestoreMultiDbComponent(Context context, FirebaseApp app, Deferred<InternalAuthProvider> authProvider, Deferred<InteropAppCheckTokenProvider> appCheckProvider, GrpcMetadataProvider metadataProvider) {
        this.context = context;
        this.app = app;
        this.authProvider = authProvider;
        this.appCheckProvider = appCheckProvider;
        this.metadataProvider = metadataProvider;
        this.app.addLifecycleEventListener(this);
    }

    synchronized FirebaseFirestore get(String databaseId) {
        FirebaseFirestore firestore;
        firestore = this.instances.get(databaseId);
        if (firestore == null) {
            firestore = FirebaseFirestore.newInstance(this.context, this.app, this.authProvider, this.appCheckProvider, databaseId, this, this.metadataProvider);
            this.instances.put(databaseId, firestore);
        }
        return firestore;
    }

    @Override // com.google.firebase.firestore.FirebaseFirestore.InstanceRegistry
    public synchronized void remove(String databaseId) {
        this.instances.remove(databaseId);
    }

    @Override // com.google.firebase.FirebaseAppLifecycleListener
    public synchronized void onDeleted(String firebaseAppName, FirebaseOptions options) {
        for (Map.Entry<String, FirebaseFirestore> entry : new ArrayList(this.instances.entrySet())) {
            entry.getValue().terminate();
            Assert.hardAssert(!this.instances.containsKey(entry.getKey()), "terminate() should have removed its entry from `instances` for key: %s", entry.getKey());
        }
    }
}
