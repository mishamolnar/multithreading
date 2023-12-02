package org.example.threads;

import java.math.BigInteger;

public class ComplexCalculation {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
        PowerCalculatingThread first = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread second = new PowerCalculatingThread(base2, power2);
        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            return BigInteger.ONE;
        }
        return first.getResult().add(second.getResult());
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;
    
        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }
    
        @Override
        public void run() {
           BigInteger curr = BigInteger.ONE;
           for (; power.compareTo(BigInteger.ZERO) > 0; power = power.subtract(BigInteger.ONE)) {
               curr = curr.multiply(base);
           }
           result = curr;
        }
    
        public BigInteger getResult() { return result; }
    }
}