package com.company;

import static java.lang.Thread.State.WAITING;

public class Main {

    public static void main(String[] args) {
        System.out.println("Available number of cores: " + Runtime.getRuntime().availableProcessors());

        Tunnel tunnel = new Tunnel();

        Thread shipGenerator = new Thread(new ShipGenerator(tunnel, 5));
        Thread clothesPierLoader = new Thread(new PierLoader(tunnel, Ship.Type.CLOTHES));
        Thread bananasPierLoader = new Thread(new PierLoader(tunnel, Ship.Type.BANANAS));
        Thread breadPierLoader = new Thread(new PierLoader(tunnel, Ship.Type.BREAD));

        clothesPierLoader.setDaemon(true);
        breadPierLoader.setDaemon(true);
        bananasPierLoader.setDaemon(true);

        shipGenerator.start();
        clothesPierLoader.start();
        bananasPierLoader.start();
        breadPierLoader.start();


        while(     shipGenerator.isAlive()
                || tunnel.getShipsCounter() != 0
                || clothesPierLoader.getState() != WAITING
                || bananasPierLoader.getState() != WAITING
                || breadPierLoader.getState() != WAITING)
        {
            Thread.onSpinWait();
        }

        return;
    }
}
