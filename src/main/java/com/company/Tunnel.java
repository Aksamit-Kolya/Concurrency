package com.company;

import java.util.ArrayList;
import java.util.List;


public class Tunnel {

    private List<Ship> store;
    private static final int maxShipsInTunnel = 5;
    private static final int minShipsInTunnel = 0;
    private int shipsCounter = 0;

    public Tunnel() {
        store = new ArrayList<>();
    }

    public synchronized boolean add(Ship element) {

        try {
            while (shipsCounter == maxShipsInTunnel) {
                wait();
            }
                store.add(element);
                String info = String.format("%s + The ship arrived in the tunnel: %s %s %s", store.size(), element.getType(), element.getSize(), Thread.currentThread().getName());
                System.out.println(info);
                shipsCounter++;

                notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public synchronized Ship get(Ship.Type shipType) {

        try {
            while (shipsCounter == minShipsInTunnel) {wait();}
            for (Ship ship : store) {
                if (ship.getType() == shipType) {
                    shipsCounter--;
                    System.out.println(store.size() - 1 + " - The ship is taken from the tunnel: " + Thread.currentThread().getName());
                    store.remove(ship);

                    notifyAll();

                    return ship;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getShipsCounter(){
        return shipsCounter;
    }
}
