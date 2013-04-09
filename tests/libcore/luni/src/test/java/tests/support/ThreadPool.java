/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.support;

import java.util.LinkedList;
import java.util.logging.Logger;

public class ThreadPool extends ThreadGroup {

    private boolean isAlive;

    private LinkedList<Runnable> taskQueue;

    private int threadID;

    private static int threadPoolID;

    public ThreadPool(int numThreads) {
        super("ThreadPool-" + (threadPoolID++));
        setDaemon(true);

        isAlive = true;

        taskQueue = new LinkedList<Runnable>();
        for (int i = 0; i < numThreads; i++) {
            new PooledThread().start();
        }
    }

    public synchronized void runTask(Runnable task) {
        if (!isAlive) {
            throw new IllegalStateException();
        }
        if (task != null) {
            taskQueue.add(task);
            notify();
        }
    }

    protected synchronized Runnable getTask() throws InterruptedException {
        while (taskQueue.size() == 0) {
            if (!isAlive) {
                return null;
            }
            wait();
        }
        Logger.global.info("1 Task is removed");
        return (Runnable) taskQueue.removeFirst();
    }

    public synchronized void close() {
        if (isAlive) {
            isAlive = false;
            taskQueue.clear();
            interrupt();
        }
    }

    public void join() {
        synchronized (this) {
            isAlive = false;
            notifyAll();
        }

        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);
        for (int i = 0; i < count; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    private class PooledThread extends Thread {

        public PooledThread() {
            super(ThreadPool.this, "PooledThread-" + (threadID++));
        }

        public void run() {
            while (!isInterrupted()) {

                Runnable task = null;
                try {
                    task = getTask();
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }

                if (task == null) {
                    return;
                }

                try {
                    Logger.global.info("Task is run");
                    task.run();

                } catch (Throwable t) {
                    System.err.println(t.getMessage());
                }
            }
        }
    }
}
