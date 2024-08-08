package com.eagle.gava.render;

public interface Cancellable {
    boolean isCancelled();

    void cancel();
}
