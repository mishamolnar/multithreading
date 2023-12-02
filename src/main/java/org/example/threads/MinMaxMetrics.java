package org.example.threads;

public class MinMaxMetrics {
    private volatile long min;
    private volatile long max;
    
    // Add all necessary member variables

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {
        // Add code here
        this.min = Long.MAX_VALUE;
        this.max = Long.MIN_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public synchronized void addSample(long newSample) {
        // Add code here
        if (newSample < this.min) {
            this.min = newSample;
        }
        if (newSample > this.max) {
            this.max = newSample;
        }
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        return this.min;
        
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        return this.max;
    }
}
