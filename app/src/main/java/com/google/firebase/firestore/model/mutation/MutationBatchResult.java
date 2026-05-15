package com.google.firebase.firestore.model.mutation;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import com.google.protobuf.ByteString;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public final class MutationBatchResult {
    private final MutationBatch batch;
    private final SnapshotVersion commitVersion;
    private final ImmutableSortedMap<DocumentKey, SnapshotVersion> docVersions;
    private final List<MutationResult> mutationResults;
    private final ByteString streamToken;

    private MutationBatchResult(MutationBatch batch, SnapshotVersion commitVersion, List<MutationResult> mutationResults, ByteString streamToken, ImmutableSortedMap<DocumentKey, SnapshotVersion> docVersions) {
        this.batch = batch;
        this.commitVersion = commitVersion;
        this.mutationResults = mutationResults;
        this.streamToken = streamToken;
        this.docVersions = docVersions;
    }

    public static MutationBatchResult create(MutationBatch batch, SnapshotVersion commitVersion, List<MutationResult> mutationResults, ByteString streamToken) {
        Assert.hardAssert(batch.getMutations().size() == mutationResults.size(), "Mutations sent %d must equal results received %d", Integer.valueOf(batch.getMutations().size()), Integer.valueOf(mutationResults.size()));
        ImmutableSortedMap<DocumentKey, SnapshotVersion> docVersions = DocumentCollections.emptyVersionMap();
        List<Mutation> mutations = batch.getMutations();
        for (int i = 0; i < mutations.size(); i++) {
            SnapshotVersion version = mutationResults.get(i).getVersion();
            docVersions = docVersions.insert(mutations.get(i).getKey(), version);
        }
        return new MutationBatchResult(batch, commitVersion, mutationResults, streamToken, docVersions);
    }

    public MutationBatch getBatch() {
        return this.batch;
    }

    public SnapshotVersion getCommitVersion() {
        return this.commitVersion;
    }

    public List<MutationResult> getMutationResults() {
        return this.mutationResults;
    }

    public ByteString getStreamToken() {
        return this.streamToken;
    }

    public ImmutableSortedMap<DocumentKey, SnapshotVersion> getDocVersions() {
        return this.docVersions;
    }
}
