package com.example.rhansen.logic.internal.utils;

import java.util.concurrent.Executor;

public class ThreadExecutor implements Executor {
    public void execute(Runnable r) {
        new Thread(r).start();
    }
}
