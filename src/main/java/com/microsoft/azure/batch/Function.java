package com.microsoft.azure.batch;

interface Function<T, R> {
    R apply(T t);
}