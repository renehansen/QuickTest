package com.example.rhansen.logic.internal.utils;

import java.util.concurrent.Executor;

public class SynchronousExecutor implements Executor {
    public void execute(Runnable r) {
        r.run();
    }
}
