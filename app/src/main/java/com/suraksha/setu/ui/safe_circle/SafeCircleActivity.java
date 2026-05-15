package com.suraksha.setu.ui.safe_circle;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.suraksha.setu.R;
import com.suraksha.setu.data.model.SafeCircleMember;
import com.suraksha.setu.databinding.ActivitySafeCircleBinding;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeCircleActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u0011\u001a\u00020\u000fH\u0002J\u0012\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\b\u0010\u0017\u001a\u00020\u000fH\u0002J\u0012\u0010\u0018\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/suraksha/setu/ui/safe_circle/SafeCircleActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/suraksha/setu/ui/safe_circle/SafeCircleAdapter;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "binding", "Lcom/suraksha/setu/databinding/ActivitySafeCircleBinding;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "memberList", "", "Lcom/suraksha/setu/data/model/SafeCircleMember;", "deleteMember", "", "member", "fetchMembers", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "saveMember", "setupRecyclerView", "setupSearch", "showMemberDialog", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SafeCircleActivity extends AppCompatActivity {
    private SafeCircleAdapter adapter;
    private FirebaseAuth auth;
    private ActivitySafeCircleBinding binding;
    private FirebaseFirestore db;
    private List<SafeCircleMember> memberList = new ArrayList();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySafeCircleBinding activitySafeCircleBindingInflate = ActivitySafeCircleBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activitySafeCircleBindingInflate, "inflate(...)");
        this.binding = activitySafeCircleBindingInflate;
        ActivitySafeCircleBinding activitySafeCircleBinding = this.binding;
        ActivitySafeCircleBinding activitySafeCircleBinding2 = null;
        if (activitySafeCircleBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySafeCircleBinding = null;
        }
        setContentView(activitySafeCircleBinding.getRoot());
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseFirestore, "getInstance(...)");
        this.db = firebaseFirestore;
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseAuth, "getInstance(...)");
        this.auth = firebaseAuth;
        setupRecyclerView();
        setupSearch();
        fetchMembers();
        ActivitySafeCircleBinding activitySafeCircleBinding3 = this.binding;
        if (activitySafeCircleBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySafeCircleBinding3 = null;
        }
        activitySafeCircleBinding3.fabAddMember.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.safe_circle.SafeCircleActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SafeCircleActivity.onCreate$lambda$0(this.f$0, view);
            }
        });
        ActivitySafeCircleBinding activitySafeCircleBinding4 = this.binding;
        if (activitySafeCircleBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySafeCircleBinding2 = activitySafeCircleBinding4;
        }
        activitySafeCircleBinding2.toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.safe_circle.SafeCircleActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SafeCircleActivity.onCreate$lambda$1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(SafeCircleActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showMemberDialog(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(SafeCircleActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    private final void setupRecyclerView() {
        this.adapter = new SafeCircleAdapter(new Function1<SafeCircleMember, Unit>() { // from class: com.suraksha.setu.ui.safe_circle.SafeCircleActivity.setupRecyclerView.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SafeCircleMember safeCircleMember) {
                invoke2(safeCircleMember);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SafeCircleMember member) {
                Intrinsics.checkNotNullParameter(member, "member");
                SafeCircleActivity.this.deleteMember(member);
            }
        }, new Function1<SafeCircleMember, Unit>() { // from class: com.suraksha.setu.ui.safe_circle.SafeCircleActivity.setupRecyclerView.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SafeCircleMember safeCircleMember) {
                invoke2(safeCircleMember);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SafeCircleMember member) {
                Intrinsics.checkNotNullParameter(member, "member");
                SafeCircleActivity.this.showMemberDialog(member);
            }
        });
        ActivitySafeCircleBinding activitySafeCircleBinding = this.binding;
        SafeCircleAdapter safeCircleAdapter = null;
        if (activitySafeCircleBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySafeCircleBinding = null;
        }
        activitySafeCircleBinding.rvMembers.setLayoutManager(new LinearLayoutManager(this));
        ActivitySafeCircleBinding activitySafeCircleBinding2 = this.binding;
        if (activitySafeCircleBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySafeCircleBinding2 = null;
        }
        RecyclerView recyclerView = activitySafeCircleBinding2.rvMembers;
        SafeCircleAdapter safeCircleAdapter2 = this.adapter;
        if (safeCircleAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            safeCircleAdapter = safeCircleAdapter2;
        }
        recyclerView.setAdapter(safeCircleAdapter);
    }

    private final void setupSearch() {
        ActivitySafeCircleBinding activitySafeCircleBinding = this.binding;
        if (activitySafeCircleBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySafeCircleBinding = null;
        }
        TextView etSearch = activitySafeCircleBinding.etSearch;
        Intrinsics.checkNotNullExpressionValue(etSearch, "etSearch");
        TextView $this$addTextChangedListener_u24default$iv = etSearch;
        $this$addTextChangedListener_u24default$iv.addTextChangedListener(new TextWatcher() { // from class: com.suraksha.setu.ui.safe_circle.SafeCircleActivity$setupSearch$$inlined$addTextChangedListener$default$1
            /* JADX WARN: Removed duplicated region for block: B:9:0x0071  */
            @Override // android.text.TextWatcher
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void afterTextChanged(android.text.Editable r20) {
                /*
                    r19 = this;
                    r0 = r19
                    r1 = r20
                    r2 = 0
                    java.lang.String r3 = java.lang.String.valueOf(r1)
                    java.util.Locale r4 = java.util.Locale.ROOT
                    java.lang.String r3 = r3.toLowerCase(r4)
                    java.lang.String r4 = "toLowerCase(...)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
                    com.suraksha.setu.ui.safe_circle.SafeCircleActivity r5 = r0.this$0
                    java.util.List r5 = com.suraksha.setu.ui.safe_circle.SafeCircleActivity.access$getMemberList$p(r5)
                    java.lang.Iterable r5 = (java.lang.Iterable) r5
                    r6 = 0
                    java.util.ArrayList r7 = new java.util.ArrayList
                    r7.<init>()
                    java.util.Collection r7 = (java.util.Collection) r7
                    r8 = r5
                    r9 = 0
                    java.util.Iterator r10 = r8.iterator()
                L2a:
                    boolean r11 = r10.hasNext()
                    if (r11 == 0) goto L7e
                    java.lang.Object r11 = r10.next()
                    r13 = r11
                    com.suraksha.setu.data.model.SafeCircleMember r13 = (com.suraksha.setu.data.model.SafeCircleMember) r13
                    r14 = 0
                    java.lang.String r15 = r13.getFullName()
                    java.util.Locale r12 = java.util.Locale.ROOT
                    java.lang.String r12 = r15.toLowerCase(r12)
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r4)
                    java.lang.CharSequence r12 = (java.lang.CharSequence) r12
                    r15 = r3
                    java.lang.CharSequence r15 = (java.lang.CharSequence) r15
                    r16 = r1
                    r1 = 0
                    r17 = r2
                    r2 = 2
                    r18 = r5
                    r5 = 0
                    boolean r12 = kotlin.text.StringsKt.contains$default(r12, r15, r1, r2, r5)
                    if (r12 != 0) goto L71
                    java.lang.String r12 = r13.getRelationship()
                    java.util.Locale r15 = java.util.Locale.ROOT
                    java.lang.String r12 = r12.toLowerCase(r15)
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r4)
                    java.lang.CharSequence r12 = (java.lang.CharSequence) r12
                    r15 = r3
                    java.lang.CharSequence r15 = (java.lang.CharSequence) r15
                    boolean r2 = kotlin.text.StringsKt.contains$default(r12, r15, r1, r2, r5)
                    if (r2 == 0) goto L72
                L71:
                    r1 = 1
                L72:
                    if (r1 == 0) goto L77
                    r7.add(r11)
                L77:
                    r1 = r16
                    r2 = r17
                    r5 = r18
                    goto L2a
                L7e:
                    r16 = r1
                    r17 = r2
                    r18 = r5
                    r5 = 0
                    r1 = r7
                    java.util.List r1 = (java.util.List) r1
                    com.suraksha.setu.ui.safe_circle.SafeCircleActivity r2 = r0.this$0
                    com.suraksha.setu.ui.safe_circle.SafeCircleAdapter r2 = com.suraksha.setu.ui.safe_circle.SafeCircleActivity.access$getAdapter$p(r2)
                    if (r2 != 0) goto L99
                    java.lang.String r2 = "adapter"
                    kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
                    r12 = r5
                    goto L9a
                L99:
                    r12 = r2
                L9a:
                    r12.submitList(r1)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.suraksha.setu.ui.safe_circle.SafeCircleActivity$setupSearch$$inlined$addTextChangedListener$default$1.afterTextChanged(android.text.Editable):void");
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }
        });
    }

    private final void fetchMembers() {
        String uid;
        FirebaseAuth firebaseAuth = this.auth;
        FirebaseFirestore firebaseFirestore = null;
        if (firebaseAuth == null) {
            Intrinsics.throwUninitializedPropertyAccessException("auth");
            firebaseAuth = null;
        }
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null || (uid = currentUser.getUid()) == null) {
            return;
        }
        FirebaseFirestore firebaseFirestore2 = this.db;
        if (firebaseFirestore2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("db");
        } else {
            firebaseFirestore = firebaseFirestore2;
        }
        firebaseFirestore.collection("users").document(uid).collection("safe_circle").addSnapshotListener(new EventListener() { // from class: com.suraksha.setu.ui.safe_circle.SafeCircleActivity$$ExternalSyntheticLambda4
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                SafeCircleActivity.fetchMembers$lambda$4(this.f$0, (QuerySnapshot) obj, firebaseFirestoreException);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fetchMembers$lambda$4(SafeCircleActivity this$0, QuerySnapshot snapshot, FirebaseFirestoreException e) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (e != null) {
            return;
        }
        ActivitySafeCircleBinding activitySafeCircleBinding = null;
        ArrayList objects = snapshot != null ? snapshot.toObjects(SafeCircleMember.class) : null;
        if (objects == null) {
            objects = new ArrayList();
        }
        this$0.memberList = objects;
        SafeCircleAdapter safeCircleAdapter = this$0.adapter;
        if (safeCircleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            safeCircleAdapter = null;
        }
        safeCircleAdapter.submitList(this$0.memberList);
        ActivitySafeCircleBinding activitySafeCircleBinding2 = this$0.binding;
        if (activitySafeCircleBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySafeCircleBinding2 = null;
        }
        activitySafeCircleBinding2.emptyState.setVisibility(this$0.memberList.isEmpty() ? 0 : 8);
        ActivitySafeCircleBinding activitySafeCircleBinding3 = this$0.binding;
        if (activitySafeCircleBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySafeCircleBinding = activitySafeCircleBinding3;
        }
        activitySafeCircleBinding.warningCard.setVisibility((this$0.memberList.size() >= 5 || !(this$0.memberList.isEmpty() ^ true)) ? 8 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showMemberDialog(final SafeCircleMember member) {
        final View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_member, (ViewGroup) null);
        final TextInputEditText etName = (TextInputEditText) dialogView.findViewById(R.id.etMemberName);
        final TextInputEditText etRel = (TextInputEditText) dialogView.findViewById(R.id.etRelationship);
        final TextInputEditText etPhone = (TextInputEditText) dialogView.findViewById(R.id.etMemberPhone);
        final TextInputEditText etNotes = (TextInputEditText) dialogView.findViewById(R.id.etNotes);
        final RadioGroup rgPriority = (RadioGroup) dialogView.findViewById(R.id.rgPriority);
        if (member != null) {
            etName.setText(member.getFullName());
            etRel.setText(member.getRelationship());
            etPhone.setText(member.getContactNumber());
            etNotes.setText(member.getNotes());
            String priority = member.getPriority();
            if (Intrinsics.areEqual(priority, "High")) {
                ((RadioButton) dialogView.findViewById(R.id.rbHigh)).setChecked(true);
            } else if (Intrinsics.areEqual(priority, "Low")) {
                ((RadioButton) dialogView.findViewById(R.id.rbLow)).setChecked(true);
            } else {
                ((RadioButton) dialogView.findViewById(R.id.rbMedium)).setChecked(true);
            }
        }
        new AlertDialog.Builder(this).setView(dialogView).setPositiveButton(member == null ? "ADD" : "UPDATE", new DialogInterface.OnClickListener() { // from class: com.suraksha.setu.ui.safe_circle.SafeCircleActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SafeCircleActivity.showMemberDialog$lambda$6(etName, etRel, etPhone, etNotes, dialogView, rgPriority, this, member, dialogInterface, i);
            }
        }).setNegativeButton("CANCEL", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMemberDialog$lambda$6(TextInputEditText $etName, TextInputEditText $etRel, TextInputEditText $etPhone, TextInputEditText $etNotes, View $dialogView, RadioGroup $rgPriority, SafeCircleActivity this$0, SafeCircleMember $member, DialogInterface dialog, int i) {
        String id;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String name = String.valueOf($etName.getText());
        String rel = String.valueOf($etRel.getText());
        String phone = String.valueOf($etPhone.getText());
        String notes = String.valueOf($etNotes.getText());
        String priority = ((RadioButton) $dialogView.findViewById($rgPriority.getCheckedRadioButtonId())).getText().toString();
        if (name.length() > 0) {
            if (phone.length() > 0) {
                if ($member == null || (id = $member.getId()) == null) {
                    id = "";
                }
                this$0.saveMember(new SafeCircleMember(id, name, rel, phone, priority, notes));
                return;
            }
        }
        Toast.makeText(this$0, "Name and Phone are required", 0).show();
    }

    private final void saveMember(SafeCircleMember member) {
        String uid;
        FirebaseAuth firebaseAuth = this.auth;
        FirebaseFirestore firebaseFirestore = null;
        if (firebaseAuth == null) {
            Intrinsics.throwUninitializedPropertyAccessException("auth");
            firebaseAuth = null;
        }
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null || (uid = currentUser.getUid()) == null) {
            return;
        }
        FirebaseFirestore firebaseFirestore2 = this.db;
        if (firebaseFirestore2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("db");
        } else {
            firebaseFirestore = firebaseFirestore2;
        }
        CollectionReference collection = firebaseFirestore.collection("users").document(uid).collection("safe_circle");
        Intrinsics.checkNotNullExpressionValue(collection, "collection(...)");
        if (member.getId().length() == 0) {
            DocumentReference doc = collection.document();
            Intrinsics.checkNotNullExpressionValue(doc, "document(...)");
            DocumentReference documentReferenceDocument = collection.document(doc.getId());
            String id = doc.getId();
            Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
            documentReferenceDocument.set(SafeCircleMember.copy$default(member, id, null, null, null, null, null, 62, null));
            return;
        }
        collection.document(member.getId()).set(member);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteMember(final SafeCircleMember member) {
        final String uid;
        FirebaseAuth firebaseAuth = this.auth;
        if (firebaseAuth == null) {
            Intrinsics.throwUninitializedPropertyAccessException("auth");
            firebaseAuth = null;
        }
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null || (uid = currentUser.getUid()) == null) {
            return;
        }
        new AlertDialog.Builder(this).setTitle("Remove Member").setMessage("Are you sure you want to remove " + member.getFullName() + " from your Safe Circle?").setPositiveButton("REMOVE", new DialogInterface.OnClickListener() { // from class: com.suraksha.setu.ui.safe_circle.SafeCircleActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                SafeCircleActivity.deleteMember$lambda$7(this.f$0, uid, member, dialogInterface, i);
            }
        }).setNegativeButton("CANCEL", (DialogInterface.OnClickListener) null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteMember$lambda$7(SafeCircleActivity this$0, String uid, SafeCircleMember member, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(uid, "$uid");
        Intrinsics.checkNotNullParameter(member, "$member");
        FirebaseFirestore firebaseFirestore = this$0.db;
        if (firebaseFirestore == null) {
            Intrinsics.throwUninitializedPropertyAccessException("db");
            firebaseFirestore = null;
        }
        firebaseFirestore.collection("users").document(uid).collection("safe_circle").document(member.getId()).delete();
    }
}
