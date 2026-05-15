package com.suraksha.setu.ui.safe_circle;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.suraksha.setu.R;
import com.suraksha.setu.data.model.SafeCircleMember;
import com.suraksha.setu.databinding.ItemSafeCircleMemberBinding;
import com.suraksha.setu.ui.safe_circle.SafeCircleAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeCircleAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0011\u0012B-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\u00020\u00062\n\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/suraksha/setu/ui/safe_circle/SafeCircleAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/suraksha/setu/data/model/SafeCircleMember;", "Lcom/suraksha/setu/ui/safe_circle/SafeCircleAdapter$MemberViewHolder;", "onDeleteClick", "Lkotlin/Function1;", "", "onItemClick", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DiffCallback", "MemberViewHolder", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class SafeCircleAdapter extends ListAdapter<SafeCircleMember, MemberViewHolder> {
    private final Function1<SafeCircleMember, Unit> onDeleteClick;
    private final Function1<SafeCircleMember, Unit> onItemClick;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SafeCircleAdapter(Function1<? super SafeCircleMember, Unit> onDeleteClick, Function1<? super SafeCircleMember, Unit> onItemClick) {
        super(new DiffCallback());
        Intrinsics.checkNotNullParameter(onDeleteClick, "onDeleteClick");
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        this.onDeleteClick = onDeleteClick;
        this.onItemClick = onItemClick;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemSafeCircleMemberBinding binding = ItemSafeCircleMemberBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "inflate(...)");
        return new MemberViewHolder(this, binding);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MemberViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        SafeCircleMember item = getItem(position);
        Intrinsics.checkNotNullExpressionValue(item, "getItem(...)");
        holder.bind(item);
    }

    /* JADX INFO: compiled from: SafeCircleAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/suraksha/setu/ui/safe_circle/SafeCircleAdapter$MemberViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/suraksha/setu/databinding/ItemSafeCircleMemberBinding;", "(Lcom/suraksha/setu/ui/safe_circle/SafeCircleAdapter;Lcom/suraksha/setu/databinding/ItemSafeCircleMemberBinding;)V", "bind", "", "member", "Lcom/suraksha/setu/data/model/SafeCircleMember;", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class MemberViewHolder extends RecyclerView.ViewHolder {
        private final ItemSafeCircleMemberBinding binding;
        final /* synthetic */ SafeCircleAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MemberViewHolder(SafeCircleAdapter this$0, ItemSafeCircleMemberBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.this$0 = this$0;
            this.binding = binding;
        }

        public final void bind(final SafeCircleMember member) {
            int color;
            Intrinsics.checkNotNullParameter(member, "member");
            this.binding.tvMemberName.setText(member.getFullName());
            this.binding.tvRelationship.setText(member.getRelationship());
            this.binding.tvPhoneNumber.setText(member.getContactNumber());
            String priority = member.getPriority();
            if (Intrinsics.areEqual(priority, "High")) {
                color = R.color.safety_red;
            } else {
                color = Intrinsics.areEqual(priority, "Medium") ? R.color.primary_green : R.color.secondary_brown;
            }
            this.binding.priorityIndicator.setBackgroundColor(ContextCompat.getColor(this.binding.getRoot().getContext(), color));
            this.binding.btnCall.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.safe_circle.SafeCircleAdapter$MemberViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SafeCircleAdapter.MemberViewHolder.bind$lambda$0(member, this, view);
                }
            });
            ImageButton imageButton = this.binding.btnDelete;
            final SafeCircleAdapter safeCircleAdapter = this.this$0;
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.safe_circle.SafeCircleAdapter$MemberViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SafeCircleAdapter.MemberViewHolder.bind$lambda$1(safeCircleAdapter, member, view);
                }
            });
            MaterialCardView root = this.binding.getRoot();
            final SafeCircleAdapter safeCircleAdapter2 = this.this$0;
            root.setOnClickListener(new View.OnClickListener() { // from class: com.suraksha.setu.ui.safe_circle.SafeCircleAdapter$MemberViewHolder$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SafeCircleAdapter.MemberViewHolder.bind$lambda$2(safeCircleAdapter2, member, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$0(SafeCircleMember member, MemberViewHolder this$0, View it) {
            Intrinsics.checkNotNullParameter(member, "$member");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + member.getContactNumber()));
            this$0.binding.getRoot().getContext().startActivity(intent);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$1(SafeCircleAdapter this$0, SafeCircleMember member, View it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(member, "$member");
            this$0.onDeleteClick.invoke(member);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void bind$lambda$2(SafeCircleAdapter this$0, SafeCircleMember member, View it) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(member, "$member");
            this$0.onItemClick.invoke(member);
        }
    }

    /* JADX INFO: compiled from: SafeCircleAdapter.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/suraksha/setu/ui/safe_circle/SafeCircleAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/suraksha/setu/data/model/SafeCircleMember;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DiffCallback extends DiffUtil.ItemCallback<SafeCircleMember> {
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(SafeCircleMember oldItem, SafeCircleMember newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getId(), newItem.getId());
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(SafeCircleMember oldItem, SafeCircleMember newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }
    }
}
