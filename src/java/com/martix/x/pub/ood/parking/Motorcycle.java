package com.martix.x.pub.ood.parking;

/**
 * Created by Andrew-Geng on 00:42 2022/9/12
 * 摩托车
 */
public class Motorcycle extends Vehicle {

    public Motorcycle() {
        spotsNeeded = 1;
        size = VehicleSize.Motorcycle;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot parkingSpot) {
        return true;
    }

    @Override
    public void print() {
        System.out.println("Motorcycle");
    }
}
