package com.company;

import java.util.Random;

public class ShipGenerator implements Runnable {

    private Tunnel tunnel;
    private int shipCount;

    public ShipGenerator(Tunnel tunnel, int shipCount) {
        this.tunnel = tunnel;
        this.shipCount = shipCount;
    }

    @Override
    public void run() {
        int count = 0;
        while (count < shipCount) {
            Thread.currentThread().setName(" Generator ship");
            count++;
            tunnel.add(new Ship(getRandomSize(), getRandomType()));
        }
    }

    private Ship.Type getRandomType() {
        Random random = new Random();
        return Ship.Type.values()[random.nextInt(Ship.Type.values().length)];
    }

    private Ship.Size getRandomSize() {
        Random random = new Random();
        return Ship.Size.values()[random.nextInt(Ship.Size.values().length)];
    }
}
