package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.p001firebaseauthapi.zzagt;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@22.3.0 */
/* JADX INFO: loaded from: classes10.dex */
public final class zzbj {
    private static long zza = 3600000;
    private static final com.google.android.gms.internal.p001firebaseauthapi.zzap<String> zzb = com.google.android.gms.internal.p001firebaseauthapi.zzap.zza("firebaseAppName", "firebaseUserUid", "operation", "tenantId", "verifyAssertionRequest", "statusCode", "statusMessage", "timestamp");
    private static final zzbj zzc = new zzbj();
    private Task<AuthResult> zzd;
    private Task<String> zze;
    private long zzf = 0;

    public final Task<AuthResult> zza() {
        if (DefaultClock.getInstance().currentTimeMillis() - this.zzf < zza) {
            return this.zzd;
        }
        return null;
    }

    public final Task<String> zzb() {
        if (DefaultClock.getInstance().currentTimeMillis() - this.zzf < zza) {
            return this.zze;
        }
        return null;
    }

    public static zzbj zzc() {
        return zzc;
    }

    private zzbj() {
    }

    private static void zza(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        com.google.android.gms.internal.p001firebaseauthapi.zzap<String> zzapVar = zzb;
        int size = zzapVar.size();
        int i = 0;
        while (i < size) {
            String str = zzapVar.get(i);
            i++;
            editorEdit.remove(str);
        }
        editorEdit.commit();
    }

    public final void zza(Context context) {
        Preconditions.checkNotNull(context);
        zza(context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0));
        this.zzd = null;
        this.zzf = 0L;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void zza(FirebaseAuth firebaseAuth) {
        zzagt zzagtVar;
        String string;
        Preconditions.checkNotNull(firebaseAuth);
        SharedPreferences sharedPreferences = firebaseAuth.getApp().getApplicationContext().getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0);
        if (!firebaseAuth.getApp().getName().equals(sharedPreferences.getString("firebaseAppName", ""))) {
            return;
        }
        if (sharedPreferences.contains("verifyAssertionRequest")) {
            zzagtVar = (zzagt) SafeParcelableSerializer.deserializeFromString(sharedPreferences.getString("verifyAssertionRequest", ""), zzagt.CREATOR);
            String string2 = sharedPreferences.getString("operation", "");
            String string3 = sharedPreferences.getString("tenantId", null);
            string = sharedPreferences.getString("firebaseUserUid", "");
            this.zzf = sharedPreferences.getLong("timestamp", 0L);
            if (string3 != null) {
                firebaseAuth.setTenantId(string3);
                zzagtVar.zzb(string3);
            }
            switch (string2) {
                case "com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN":
                    this.zzd = firebaseAuth.signInWithCredential(com.google.firebase.auth.zzf.zza(zzagtVar));
                    break;
                case "com.google.firebase.auth.internal.NONGMSCORE_LINK":
                    if (firebaseAuth.getCurrentUser().getUid().equals(string)) {
                        this.zzd = firebaseAuth.zza(firebaseAuth.getCurrentUser(), com.google.firebase.auth.zzf.zza(zzagtVar));
                        break;
                    } else {
                        this.zzd = null;
                        break;
                    }
                    break;
                case "com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE":
                    if (firebaseAuth.getCurrentUser().getUid().equals(string)) {
                        this.zzd = firebaseAuth.zzc(firebaseAuth.getCurrentUser(), com.google.firebase.auth.zzf.zza(zzagtVar));
                        break;
                    } else {
                        this.zzd = null;
                        break;
                    }
                    break;
                default:
                    this.zzd = null;
                    break;
            }
            zza(sharedPreferences);
            return;
        }
        if (sharedPreferences.contains("recaptchaToken")) {
            String string4 = sharedPreferences.getString("recaptchaToken", "");
            String string5 = sharedPreferences.getString("operation", "");
            this.zzf = sharedPreferences.getLong("timestamp", 0L);
            switch (string5.hashCode()) {
                case -214796028:
                    if (!string5.equals("com.google.firebase.auth.internal.ACTION_SHOW_RECAPTCHA")) {
                    }
                default:
                    break;
            }
            /*  JADX ERROR: Method code generation error
                java.lang.NullPointerException: Switch insn not found in header
                	at java.base/java.util.Objects.requireNonNull(Objects.java:246)
                	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:246)
                	at jadx.core.dex.regions.SwitchRegion.generate(SwitchRegion.java:88)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:303)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1612)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:272)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:159)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
                	at jadx.core.ProcessClass.process(ProcessClass.java:88)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:126)
                	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
                	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:343)
                */
            /*
                Method dump skipped, instruction units count: 368
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.internal.zzbj.zza(com.google.firebase.auth.FirebaseAuth):void");
        }

        public static void zza(Context context, Status status) {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
            editorEdit.putInt("statusCode", status.getStatusCode());
            editorEdit.putString("statusMessage", status.getStatusMessage());
            editorEdit.putLong("timestamp", DefaultClock.getInstance().currentTimeMillis());
            editorEdit.commit();
        }

        public static void zza(Context context, FirebaseAuth firebaseAuth) {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(firebaseAuth);
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
            editorEdit.putString("firebaseAppName", firebaseAuth.getApp().getName());
            editorEdit.commit();
        }

        public static void zza(Context context, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(firebaseAuth);
            Preconditions.checkNotNull(firebaseUser);
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
            editorEdit.putString("firebaseAppName", firebaseAuth.getApp().getName());
            editorEdit.putString("firebaseUserUid", firebaseUser.getUid());
            editorEdit.commit();
        }

        public static void zza(Context context, zzagt zzagtVar, String str, String str2) {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
            editorEdit.putString("verifyAssertionRequest", SafeParcelableSerializer.serializeToString(zzagtVar));
            editorEdit.putString("operation", str);
            editorEdit.putString("tenantId", str2);
            editorEdit.putLong("timestamp", DefaultClock.getInstance().currentTimeMillis());
            editorEdit.commit();
        }

        public static void zza(Context context, String str, String str2) {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
            editorEdit.putString("recaptchaToken", str);
            editorEdit.putString("operation", str2);
            editorEdit.putLong("timestamp", DefaultClock.getInstance().currentTimeMillis());
            editorEdit.commit();
        }
    }
