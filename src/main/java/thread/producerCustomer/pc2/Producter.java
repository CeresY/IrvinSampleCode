package thread.producerCustomer.pc2;

class Producter implements Runnable {
    private Counter computer = null;

    public Producter(Counter computer) {
        this.computer = computer;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            computer.productComputer(i);
        }
    }
}