package com.google.firebase.firestore.core;

import com.google.firebase.firestore.model.Document;
import java.util.List;

/* JADX INFO: loaded from: classes10.dex */
public abstract class Filter {
    public abstract String getCanonicalId();

    public abstract List<Filter> getFilters();

    public abstract List<FieldFilter> getFlattenedFilters();

    public abstract boolean matches(Document document);
}
