package com.martix.x.pub.ood.parking;

/**
 * Created by Andrew-Geng on 00:47 2022/9/12
 */
public class Bus extends Vehicle {

    public Bus() {
        spotsNeeded = 5;
        size = VehicleSize.Large;
    }

    public boolean canFitInSpot(ParkingSpot parkingSpot) {
        return parkingSpot.getVehicleSize() == VehicleSize.Large;
    }

    public void print() {
        System.out.print("Bus");
    }
}
