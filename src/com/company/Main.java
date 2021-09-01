package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.State.WAITING;

public class Main {

    public static void main(String[] args) {
        System.out.println("Available number of cores: " + Runtime.getRuntime().availableProcessors());

        Tunnel tunnel = new Tunnel();

       /*
        ShipGenerator shipGenerator = new ShipGenerator(tunnel, 10);

        PierLoader pierLoader1 = new PierLoader(tunnel, Ship.Type.CLOTHES);
        PierLoader pierLoader2 = new PierLoader(tunnel, Ship.Type.BANANAS);
        PierLoader pierLoader3 = new PierLoader(tunnel, Ship.Type.BREAD);*/


        /*ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        service.execute(shipGenerator);
        service.execute(pierLoader1);
        service.execute(pierLoader2);
        service.execute(pierLoader3);
*/
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

        //Thread.State clothesPierLoaderCondition = clothesPierLoader.getState();
        //Thread.State bananasPierLoaderCondition = bananasPierLoader.getState();
        //Thread.State breadPierLoaderCondition = breadPierLoader.getState();

        while(     shipGenerator.isAlive()
                || tunnel.getShipsCounter() != 0
                || clothesPierLoader.getState() != WAITING
                || bananasPierLoader.getState() != WAITING
                || breadPierLoader.getState() != WAITING)
        {
            Thread.onSpinWait();
            //clothesPierLoaderCondition = clothesPierLoader.getState();
            //bananasPierLoaderCondition = bananasPierLoader.getState();
            //breadPierLoaderCondition = breadPierLoader.getState();
           /* System.out.println(clothesPierLoader.getState() +
                    "\n" + bananasPierLoader.getState() +
                    "\n" + breadPierLoader.getState());*/
        }

        //shipGenerator.interrupt();
        /*System.out.println(clothesPierLoader.getState() +
                "\n" + bananasPierLoader.getState() +
                "\n" + breadPierLoader.getState());


        while(clothesPierLoader.isAlive()) {clothesPierLoader.interrupt();System.out.println(clothesPierLoader.getState());}
        while(breadPierLoader.isAlive()) {breadPierLoader.interrupt();System.out.println(breadPierLoader.getState());}
        while(bananasPierLoader.isAlive()) {bananasPierLoader.interrupt();System.out.println(bananasPierLoader.getState());}*/
        /*clothesPierLoader.interrupt();
        breadPierLoader.interrupt();
        bananasPierLoader.interrupt();*/
        //clothesPierLoader

        /*System.out.println(shipGenerator.isAlive());
        System.out.println(clothesPierLoader.isAlive());
        System.out.println(bananasPierLoader.isAlive());
        System.out.println(breadPierLoader.isAlive());*/



        return;
    }
}
