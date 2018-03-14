package thread.producerCustomer.pc2;

class Consumer implements Runnable {
    private Counter computer = null;

    public Consumer(Counter computer) {
        this.computer = computer;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            computer.consumeComputer(i);
        }
    }
}