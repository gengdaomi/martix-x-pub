package com.martix.x.pub.ood.parking;

/**
 * Created by Andrew-Geng on 00:44 2022/9/12
 */
public class Car extends Vehicle {

    public Car() {
        spotsNeeded = 1;
        size = VehicleSize.Compact;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot parkingSpot) {
        return parkingSpot.getVehicleSize() == VehicleSize.Large || parkingSpot.getVehicleSize() == VehicleSize.Compact;
    }

    @Override
    public void print() {
        System.out.println("Car");
    }
}
