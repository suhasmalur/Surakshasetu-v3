package com.google.firebase.firestore.core;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes10.dex */
public class ActivityScope {
    private static final String FRAGMENT_TAG = "FirestoreOnStopObserverFragment";
    private static final String SUPPORT_FRAGMENT_TAG = "FirestoreOnStopObserverSupportFragment";

    /* JADX INFO: Access modifiers changed from: private */
    static class CallbackList {
        private final List<Runnable> callbacks;

        private CallbackList() {
            this.callbacks = new ArrayList();
        }

        void run() {
            for (Runnable callback : this.callbacks) {
                if (callback != null) {
                    callback.run();
                }
            }
        }

        synchronized void add(Runnable callback) {
            this.callbacks.add(callback);
        }
    }

    public static class StopListenerSupportFragment extends Fragment {
        CallbackList callbacks = new CallbackList();

        @Override // androidx.fragment.app.Fragment
        public void onStop() {
            CallbackList callbacksCopy;
            super.onStop();
            synchronized (this.callbacks) {
                callbacksCopy = this.callbacks;
                this.callbacks = new CallbackList();
            }
            callbacksCopy.run();
        }
    }

    public static class StopListenerFragment extends android.app.Fragment {
        CallbackList callbacks = new CallbackList();

        @Override // android.app.Fragment
        public void onStop() {
            CallbackList callbacksCopy;
            super.onStop();
            synchronized (this.callbacks) {
                callbacksCopy = this.callbacks;
                this.callbacks = new CallbackList();
            }
            callbacksCopy.run();
        }
    }

    private static <T> T castFragment(Class<T> fragmentClass, Object fragment, String tag) {
        if (fragment == null) {
            return null;
        }
        try {
            return fragmentClass.cast(fragment);
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag '" + tag + "' is a " + fragment.getClass().getName() + " but should be a " + fragmentClass.getName());
        }
    }

    private static void onActivityStopCallOnce(final Activity activity, final Runnable callback) {
        Assert.hardAssert(!(activity instanceof FragmentActivity), "onActivityStopCallOnce must be called with a *non*-FragmentActivity Activity.", new Object[0]);
        activity.runOnUiThread(new Runnable() { // from class: com.google.firebase.firestore.core.ActivityScope$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                ActivityScope.lambda$onActivityStopCallOnce$0(activity, callback);
            }
        });
    }

    static /* synthetic */ void lambda$onActivityStopCallOnce$0(Activity activity, Runnable callback) {
        StopListenerFragment fragment = (StopListenerFragment) castFragment(StopListenerFragment.class, activity.getFragmentManager().findFragmentByTag(FRAGMENT_TAG), FRAGMENT_TAG);
        if (fragment == null || fragment.isRemoving()) {
            fragment = new StopListenerFragment();
            activity.getFragmentManager().beginTransaction().add(fragment, FRAGMENT_TAG).commitAllowingStateLoss();
            activity.getFragmentManager().executePendingTransactions();
        }
        fragment.callbacks.add(callback);
    }

    private static void onFragmentActivityStopCallOnce(final FragmentActivity activity, final Runnable callback) {
        activity.runOnUiThread(new Runnable() { // from class: com.google.firebase.firestore.core.ActivityScope$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ActivityScope.lambda$onFragmentActivityStopCallOnce$1(activity, callback);
            }
        });
    }

    static /* synthetic */ void lambda$onFragmentActivityStopCallOnce$1(FragmentActivity activity, Runnable callback) {
        StopListenerSupportFragment fragment = (StopListenerSupportFragment) castFragment(StopListenerSupportFragment.class, activity.getSupportFragmentManager().findFragmentByTag(SUPPORT_FRAGMENT_TAG), SUPPORT_FRAGMENT_TAG);
        if (fragment == null || fragment.isRemoving()) {
            fragment = new StopListenerSupportFragment();
            activity.getSupportFragmentManager().beginTransaction().add(fragment, SUPPORT_FRAGMENT_TAG).commitAllowingStateLoss();
            activity.getSupportFragmentManager().executePendingTransactions();
        }
        fragment.callbacks.add(callback);
    }

    public static ListenerRegistration bind(Activity activity, final ListenerRegistration registration) {
        if (activity != null) {
            if (activity instanceof FragmentActivity) {
                Objects.requireNonNull(registration);
                onFragmentActivityStopCallOnce((FragmentActivity) activity, new Runnable() { // from class: com.google.firebase.firestore.core.ActivityScope$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        registration.remove();
                    }
                });
            } else {
                Objects.requireNonNull(registration);
                onActivityStopCallOnce(activity, new Runnable() { // from class: com.google.firebase.firestore.core.ActivityScope$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        registration.remove();
                    }
                });
            }
        }
        return registration;
    }
}
