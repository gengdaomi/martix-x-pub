package com.martix.x.pub.ood.parking;

/**
 * Created by Andrew-Geng on 00:50 2022/9/12
 * 停车场
 */
public class ParkingLot {

    private Level[] levels;
    private final int NUM_LEVELS = 5; //默认5层

    public ParkingLot() {
        levels = new Level[NUM_LEVELS];

        for (int i = 0; i < NUM_LEVELS; i++) {
            levels[i] = new Level(i, 30); //默认每层30个车位
        }
    }

    /* Park the vehicle in a spot (or multiple spots). Return false if failed. */

    /**
     * 停一辆车在一个车位(或者多个车位)，false表示失败
     * @param vehicle
     * @return
     */
    public boolean parkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            if (level.parkVehicle(vehicle)) {
                return true;
            }
        }

        return false;
    }

    public void print() {
        for (int i = 0; i < levels.length; i++) {
            System.out.print("Level" + i + ": ");
            levels[i].print();
            System.out.println("");
        }

        System.out.println("");
    }

}
