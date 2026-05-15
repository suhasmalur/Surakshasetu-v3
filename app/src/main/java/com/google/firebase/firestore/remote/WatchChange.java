package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public abstract class WatchChange {

    public enum WatchTargetChangeType {
        NoChange,
        Added,
        Removed,
        Current,
        Reset
    }

    private WatchChange() {
    }

    public static final class DocumentChange extends WatchChange {
        private final DocumentKey documentKey;
        private final MutableDocument newDocument;
        private final List<Integer> removedTargetIds;
        private final List<Integer> updatedTargetIds;

        public DocumentChange(List<Integer> updatedTargetIds, List<Integer> removedTargetIds, DocumentKey key, MutableDocument document) {
            super();
            this.updatedTargetIds = updatedTargetIds;
            this.removedTargetIds = removedTargetIds;
            this.documentKey = key;
            this.newDocument = document;
        }

        public List<Integer> getUpdatedTargetIds() {
            return this.updatedTargetIds;
        }

        public List<Integer> getRemovedTargetIds() {
            return this.removedTargetIds;
        }

        public MutableDocument getNewDocument() {
            return this.newDocument;
        }

        public DocumentKey getDocumentKey() {
            return this.documentKey;
        }

        public String toString() {
            return "DocumentChange{updatedTargetIds=" + this.updatedTargetIds + ", removedTargetIds=" + this.removedTargetIds + ", key=" + this.documentKey + ", newDocument=" + this.newDocument + '}';
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            DocumentChange that = (DocumentChange) o;
            if (!this.updatedTargetIds.equals(that.updatedTargetIds) || !this.removedTargetIds.equals(that.removedTargetIds) || !this.documentKey.equals(that.documentKey)) {
                return false;
            }
            if (this.newDocument != null) {
                return this.newDocument.equals(that.newDocument);
            }
            if (that.newDocument == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int result = this.updatedTargetIds.hashCode();
            return (((((result * 31) + this.removedTargetIds.hashCode()) * 31) + this.documentKey.hashCode()) * 31) + (this.newDocument != null ? this.newDocument.hashCode() : 0);
        }
    }

    public static final class ExistenceFilterWatchChange extends WatchChange {
        private final ExistenceFilter existenceFilter;
        private final int targetId;

        public ExistenceFilterWatchChange(int targetId, ExistenceFilter existenceFilter) {
            super();
            this.targetId = targetId;
            this.existenceFilter = existenceFilter;
        }

        public int getTargetId() {
            return this.targetId;
        }

        public ExistenceFilter getExistenceFilter() {
            return this.existenceFilter;
        }

        public String toString() {
            return "ExistenceFilterWatchChange{targetId=" + this.targetId + ", existenceFilter=" + this.existenceFilter + '}';
        }
    }

    public static final class WatchTargetChange extends WatchChange {
        private final Status cause;
        private final WatchTargetChangeType changeType;
        private final ByteString resumeToken;
        private final List<Integer> targetIds;

        public WatchTargetChange(WatchTargetChangeType changeType, List<Integer> targetIds) {
            this(changeType, targetIds, WatchStream.EMPTY_RESUME_TOKEN, null);
        }

        public WatchTargetChange(WatchTargetChangeType changeType, List<Integer> targetIds, ByteString resumeToken) {
            this(changeType, targetIds, resumeToken, null);
        }

        public WatchTargetChange(WatchTargetChangeType changeType, List<Integer> targetIds, ByteString resumeToken, Status cause) {
            super();
            Assert.hardAssert(cause == null || changeType == WatchTargetChangeType.Removed, "Got cause for a target change that was not a removal", new Object[0]);
            this.changeType = changeType;
            this.targetIds = targetIds;
            this.resumeToken = resumeToken;
            if (cause != null && !cause.isOk()) {
                this.cause = cause;
            } else {
                this.cause = null;
            }
        }

        public WatchTargetChangeType getChangeType() {
            return this.changeType;
        }

        public List<Integer> getTargetIds() {
            return this.targetIds;
        }

        public ByteString getResumeToken() {
            return this.resumeToken;
        }

        public Status getCause() {
            return this.cause;
        }

        public String toString() {
            return "WatchTargetChange{changeType=" + this.changeType + ", targetIds=" + this.targetIds + '}';
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            WatchTargetChange that = (WatchTargetChange) o;
            if (this.changeType != that.changeType || !this.targetIds.equals(that.targetIds) || !this.resumeToken.equals(that.resumeToken)) {
                return false;
            }
            if (this.cause != null) {
                if (that.cause != null && this.cause.getCode().equals(that.cause.getCode())) {
                    return true;
                }
                return false;
            }
            if (that.cause == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int result = this.changeType.hashCode();
            return (((((result * 31) + this.targetIds.hashCode()) * 31) + this.resumeToken.hashCode()) * 31) + (this.cause != null ? this.cause.getCode().hashCode() : 0);
        }
    }
}
