package com.google.firebase.firestore.auth;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Listener;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.InternalTokenResult;

/* JADX INFO: loaded from: classes10.dex */
public final class FirebaseAuthCredentialsProvider extends CredentialsProvider<User> {
    private static final String LOG_TAG = "FirebaseAuthCredentialsProvider";
    private Listener<User> changeListener;
    private boolean forceRefresh;
    private final IdTokenListener idTokenListener = new IdTokenListener() { // from class: com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider$$ExternalSyntheticLambda0
        @Override // com.google.firebase.auth.internal.IdTokenListener
        public final void onIdTokenChanged(InternalTokenResult internalTokenResult) {
            this.f$0.m279x85cee08e(internalTokenResult);
        }
    };
    private InternalAuthProvider internalAuthProvider;
    private int tokenCounter;

    /* JADX INFO: renamed from: lambda$new$0$com-google-firebase-firestore-auth-FirebaseAuthCredentialsProvider, reason: not valid java name */
    /* synthetic */ void m279x85cee08e(InternalTokenResult result) {
        onIdTokenChanged();
    }

    public FirebaseAuthCredentialsProvider(Deferred<InternalAuthProvider> deferredAuthProvider) {
        deferredAuthProvider.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider$$ExternalSyntheticLambda1
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                this.f$0.m280x223cdced(provider);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$1$com-google-firebase-firestore-auth-FirebaseAuthCredentialsProvider, reason: not valid java name */
    /* synthetic */ void m280x223cdced(Provider provider) {
        synchronized (this) {
            this.internalAuthProvider = (InternalAuthProvider) provider.get();
            onIdTokenChanged();
            this.internalAuthProvider.addIdTokenListener(this.idTokenListener);
        }
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized Task<String> getToken() {
        if (this.internalAuthProvider == null) {
            return Tasks.forException(new FirebaseApiNotAvailableException("auth is not available"));
        }
        Task<GetTokenResult> res = this.internalAuthProvider.getAccessToken(this.forceRefresh);
        this.forceRefresh = false;
        final int savedCounter = this.tokenCounter;
        return res.continueWithTask(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f$0.m278x41a0a62f(savedCounter, task);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$getToken$2$com-google-firebase-firestore-auth-FirebaseAuthCredentialsProvider, reason: not valid java name */
    /* synthetic */ Task m278x41a0a62f(int savedCounter, Task task) throws Exception {
        synchronized (this) {
            if (savedCounter != this.tokenCounter) {
                Logger.debug(LOG_TAG, "getToken aborted due to token change", new Object[0]);
                return getToken();
            }
            if (task.isSuccessful()) {
                return Tasks.forResult(((GetTokenResult) task.getResult()).getToken());
            }
            return Tasks.forException(task.getException());
        }
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized void invalidateToken() {
        this.forceRefresh = true;
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized void setChangeListener(Listener<User> changeListener) {
        this.changeListener = changeListener;
        changeListener.onValue(getUser());
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized void removeChangeListener() {
        this.changeListener = null;
        if (this.internalAuthProvider != null) {
            this.internalAuthProvider.removeIdTokenListener(this.idTokenListener);
        }
    }

    private synchronized void onIdTokenChanged() {
        this.tokenCounter++;
        if (this.changeListener != null) {
            this.changeListener.onValue(getUser());
        }
    }

    private synchronized User getUser() {
        String uid;
        uid = this.internalAuthProvider == null ? null : this.internalAuthProvider.getUid();
        return uid != null ? new User(uid) : User.UNAUTHENTICATED;
    }
}
