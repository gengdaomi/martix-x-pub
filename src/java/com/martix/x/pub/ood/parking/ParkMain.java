package com.martix.x.pub.ood.parking;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrew-Geng on 00:49 2022/9/12
 */
public class ParkMain {
    public static void main(String[] args){
        ParkingLot parkingLot = new ParkingLot();

        Vehicle v = null;
        while (v == null || parkingLot.parkVehicle(v)) {
            parkingLot.print();

            int r = ThreadLocalRandom.current().nextInt(0,10);
            if (r < 2) {
                v = new Bus();
            } else if (r < 4) {
                v = new Motorcycle();
            } else {
                v = new Car();
            }

            System.out.print("\nParking a ");
            v.print();

            System.out.println("");
        }

        System.out.println("Parking Failed. Final state: ");
        parkingLot.print();
    }
}
