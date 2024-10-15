package com.example.task.data.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import io.realm.kotlin.types.RealmList;
import io.realm.kotlin.types.RealmObject;

public final class RealmListJsonAdapterFactory implements JsonAdapter.Factory {

    private static <T extends RealmObject> JsonAdapter<RealmList<T>> newRealmListAdapter(Type type, Moshi moshi) {
        Type elementType = Types.collectionElementType(type, RealmList.class);
        JsonAdapter<T> elementAdapter = moshi.adapter(elementType);
        return new RealmListAdapter<>(elementAdapter);
    }

    @Override
    @Nullable
    public JsonAdapter<?> create(@NonNull Type type, @NonNull Set<? extends Annotation> annotations, @NonNull Moshi moshi) {
        Class<?> rawType = Types.getRawType(type);
        if (rawType == RealmList.class) {
            return newRealmListAdapter(type, moshi).nullSafe();
        }
        return null;
    }
}