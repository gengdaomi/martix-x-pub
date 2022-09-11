package com.martix.x.pub.ood.parking;

/**
 * Created by Andrew-Geng on 22:47 2022/9/11
 * 停车场 层 的定义
 */
public class Level {
    private int floor; //第几层

    private ParkingSpot[] parkingSpots; //每层的车位

    private int availableSpots = 0; // 空闲车位数

    private static final int SPOTS_PER_ROW = 10; //默认每排10个

    public Level(int floor, int numberSpots) {
        this.floor = floor;
        parkingSpots = new ParkingSpot[numberSpots];

        int largerSpots = numberSpots / 4;
        int motoCycleSpots = numberSpots / 4;
        int compactSpots = numberSpots - largerSpots - motoCycleSpots;

        for (int i = 0; i < numberSpots; i++) {
            VehicleSize vehicleSize = VehicleSize.Motorcycle;
            if (i < largerSpots) {
                vehicleSize = VehicleSize.Large;
            } else if (i < largerSpots + compactSpots) {
                vehicleSize = VehicleSize.Compact;
            }

            int row = i / SPOTS_PER_ROW;
            parkingSpots[i] = new ParkingSpot(this, row, i, vehicleSize);
        }

        availableSpots = numberSpots;
    }

    /**
     * 尝试找一个停车位给车辆 如果找不到则返回false
     * @param vehicle
     * @return
     */
    public boolean parkVehicle(Vehicle vehicle) {
        if (this.getAvailableSpots() < vehicle.getSpotsNeeded()) {
            return false;
        }

        int spotNumber = findAvailableSpots(vehicle);
        if (spotNumber < 0) {
            return false;
        }

        return parkStartingAtSpot(spotNumber, vehicle);
    }

    /* Park a vehicle starting at the spot spotNumber, and continuing until vehicle.spotsNeeded. */

    /**
     * 停一辆车，从指定的停车位开始找，直到找到可停的地方
     * @param spotNumber
     * @param vehicle
     * @return
     */
    private boolean parkStartingAtSpot(int spotNumber, Vehicle vehicle) {
        vehicle.clearSpots();
        boolean success = true;

        for (int i = spotNumber; i < spotNumber + vehicle.spotsNeeded; i++) {
            success &= parkingSpots[i].park(vehicle);
        }

        availableSpots -= vehicle.spotsNeeded;
        return success;
    }

    /**
     * 找一个停车位给此车辆，返回车位的索引值，没找到的话，-1表示失败
     * @param vehicle
     * @return
     */
    private int findAvailableSpots(Vehicle vehicle) {
        int spotsNeeded = vehicle.getSpotsNeeded();
        int lastRow = -1;
        int spotsFound = 0;

        for (int i = 0; i < parkingSpots.length; i++) {
            ParkingSpot parkingSpot = parkingSpots[i];

            if (lastRow != parkingSpot.getRow()) {
                spotsFound = 0;
                lastRow = parkingSpot.getRow();
            }

            if (parkingSpot.canFitInSpot(vehicle)) {
                spotsFound++;
            } else {
                spotsFound = 0;
            }

            if (spotsFound == spotsNeeded) {
                return i - (spotsNeeded - 1);
            }
        }

        return -1;
    }

    public void print() {
        int lastRow = -1;

        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getRow() != lastRow) {
                System.out.print("  ");
                lastRow = parkingSpot.getRow();
            }

            parkingSpot.print();
        }
    }

    /**
     * 当一辆车走后，空闲一个停车位出来
     */
    public void spotFreed() {
        availableSpots++;
    }


    public int getAvailableSpots() {
        return availableSpots;
    }
}
