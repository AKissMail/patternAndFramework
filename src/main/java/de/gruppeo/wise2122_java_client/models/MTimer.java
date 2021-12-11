package de.gruppeo.wise2122_java_client.models;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class MTimer implements Runnable {
    private int seconds;
    private String threadName;
    private Thread thread;
    private Label timer;

    /**
     * Benutzerdefinierter Konstruktor
     * mit eigenem Sekundenwert.
     *
     * @param seconds
     */
    public MTimer(int seconds, String threadName, Label timer) {
        this.seconds = seconds;
        this.threadName = threadName;
        this.timer = timer;
    }

    /**
     * Standardkonstruktor mit einem
     * Startwert von insgesamt 10 Sekunden.
     */
    public MTimer() {
        this.seconds = 10;
        this.threadName = "THREAD-TIMER";
    }

    public void start() {
        System.out.println("Starting " + threadName + " ...");

        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);

        try {
            for (int i = seconds; i >= 0; i--) {
                System.out.println(threadName + ": " + i);

                int finalI = i;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        timer.setText(String.valueOf(finalI) + " s");
                    }
                });

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted");
        }
        System.out.println("Thread " +  threadName + " exiting");
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return this.seconds;
    }
}
