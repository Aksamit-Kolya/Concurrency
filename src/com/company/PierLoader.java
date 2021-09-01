package com.company;

public class PierLoader implements Runnable {
    private Tunnel tunnel;
    private Ship.Type shipType;

    public PierLoader(Tunnel tunnel, Ship.Type shipType) {
        this.tunnel = tunnel;
        this.shipType =shipType;
    }

    @Override
    public void run() {
        try {
            while (true) {

                Thread.currentThread().setName("Loader "+shipType);
                //Thread.sleep(500);
                Ship ship = tunnel.get(shipType);
                if(ship!=null)
                    while (ship.countCheck()){
                        Thread.sleep(100);
                        ship.add(10);
                        System.out.println(ship.getCount() + " Loaded ship. " + Thread.currentThread().getName());
                    }

            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.out.println("Thread is interapted");
            //{return;

        }
    }
}
