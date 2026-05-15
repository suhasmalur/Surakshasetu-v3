package com.google.protobuf;

/* JADX INFO: loaded from: classes10.dex */
@CheckReturnValue
interface SchemaFactory {
    <T> Schema<T> createSchema(Class<T> cls);
}
