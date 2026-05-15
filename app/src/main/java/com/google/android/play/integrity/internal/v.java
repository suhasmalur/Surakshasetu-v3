package com.google.android.play.integrity.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: com.google.android.play:integrity@@1.1.0 */
/* JADX INFO: loaded from: classes.dex */
public final class v {
    private static final Map a = new HashMap();
    private final Context b;
    private final k c;
    private boolean h;
    private final Intent i;
    private ServiceConnection m;
    private IInterface n;
    private final com.google.android.play.core.integrity.q o;
    private final List e = new ArrayList();
    private final Set f = new HashSet();
    private final Object g = new Object();
    private final IBinder.DeathRecipient k = new IBinder.DeathRecipient() { // from class: com.google.android.play.integrity.internal.n
        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            v.h(this.a);
        }
    };
    private final AtomicInteger l = new AtomicInteger(0);
    private final String d = "IntegrityService";
    private final WeakReference j = new WeakReference(null);

    public v(Context context, k kVar, String str, Intent intent, com.google.android.play.core.integrity.q qVar, q qVar2, byte[] bArr) {
        this.b = context;
        this.c = kVar;
        this.i = intent;
        this.o = qVar;
    }

    public static /* synthetic */ void h(v vVar) {
        vVar.c.d("reportBinderDeath", new Object[0]);
        q qVar = (q) vVar.j.get();
        if (qVar != null) {
            vVar.c.d("calling onBinderDied", new Object[0]);
            qVar.a();
        } else {
            vVar.c.d("%s : Binder has died.", vVar.d);
            Iterator it = vVar.e.iterator();
            while (it.hasNext()) {
                ((l) it.next()).a(vVar.s());
            }
            vVar.e.clear();
        }
        vVar.t();
    }

    static /* bridge */ /* synthetic */ void n(v vVar) {
        vVar.c.d("linkToDeath", new Object[0]);
        try {
            vVar.n.asBinder().linkToDeath(vVar.k, 0);
        } catch (RemoteException e) {
            vVar.c.c(e, "linkToDeath failed", new Object[0]);
        }
    }

    static /* bridge */ /* synthetic */ void o(v vVar) {
        vVar.c.d("unlinkToDeath", new Object[0]);
        vVar.n.asBinder().unlinkToDeath(vVar.k, 0);
    }

    private final RemoteException s() {
        return new RemoteException(String.valueOf(this.d).concat(" : Binder has died."));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void t() {
        synchronized (this.g) {
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                ((TaskCompletionSource) it.next()).trySetException(s());
            }
            this.f.clear();
        }
    }

    public final Handler c() {
        Handler handler;
        synchronized (a) {
            if (!a.containsKey(this.d)) {
                HandlerThread handlerThread = new HandlerThread(this.d, 10);
                handlerThread.start();
                a.put(this.d, new Handler(handlerThread.getLooper()));
            }
            handler = (Handler) a.get(this.d);
        }
        return handler;
    }

    public final IInterface e() {
        return this.n;
    }

    public final void p(l lVar, final TaskCompletionSource taskCompletionSource) {
        synchronized (this.g) {
            this.f.add(taskCompletionSource);
            taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.play.integrity.internal.m
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    this.a.q(taskCompletionSource, task);
                }
            });
        }
        synchronized (this.g) {
            if (this.l.getAndIncrement() > 0) {
                this.c.a("Already connected to the service.", new Object[0]);
            }
        }
        c().post(new o(this, lVar.c(), lVar));
    }

    final /* synthetic */ void q(TaskCompletionSource taskCompletionSource, Task task) {
        synchronized (this.g) {
            this.f.remove(taskCompletionSource);
        }
    }

    public final void r(TaskCompletionSource taskCompletionSource) {
        synchronized (this.g) {
            this.f.remove(taskCompletionSource);
        }
        synchronized (this.g) {
            if (this.l.get() > 0 && this.l.decrementAndGet() > 0) {
                this.c.d("Leaving the connection open for other ongoing calls.", new Object[0]);
            } else {
                c().post(new p(this));
            }
        }
    }

    static /* bridge */ /* synthetic */ void m(v vVar, l lVar) {
        if (vVar.n != null || vVar.h) {
            if (!vVar.h) {
                lVar.run();
                return;
            } else {
                vVar.c.d("Waiting to bind to the service.", new Object[0]);
                vVar.e.add(lVar);
                return;
            }
        }
        vVar.c.d("Initiate binding to the service.", new Object[0]);
        vVar.e.add(lVar);
        vVar.m = new u(vVar, null);
        vVar.h = true;
        if (vVar.b.bindService(vVar.i, vVar.m, 1)) {
            return;
        }
        vVar.c.d("Failed to bind to the service.", new Object[0]);
        vVar.h = false;
        Iterator it = vVar.e.iterator();
        while (it.hasNext()) {
            ((l) it.next()).a(new w());
        }
        vVar.e.clear();
    }
}
