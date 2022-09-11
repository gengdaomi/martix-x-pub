package com.martix.x.pub.ood.parking;

/**
 * Created by Andrew-Geng on 22:45 2022/9/11
 * 停车位
 */
public class ParkingSpot {

    private Vehicle vehicle; //交通工具

    private VehicleSize vehicleSize; //交通工具尺寸

    private int row; //车位所有第几排

    private int spotNumber; //停车位 号

    private Level level; //所在层

    public ParkingSpot(Level level, int row, int spotNumber, VehicleSize vehicleSize) {
        this.level = level;
        this.row = row;
        this.spotNumber = spotNumber;
        this.vehicleSize = vehicleSize;
    }

    /**
     * 判断当前停车位是否为空
     *
     * @return
     */
    public boolean isAvailable() {
        return vehicle == null;
    }

    /**
     * 判断当前位置能否停下该交通工具
     *
     * @param vehicle
     * @return
     */
    public boolean canFitInSpot(Vehicle vehicle) {
        return this.isAvailable() && vehicle.canFitInSpot(this);
    }

    /**
     * 在当前车位停车
     *
     * @param vehicle
     * @return
     */
    public boolean park(Vehicle vehicle) {
        if (!this.canFitInSpot(vehicle)) { //不能停
            return false;
        }

        this.vehicle = vehicle;
        vehicle.parkInSpot(this);

        return true;
    }

    /**
     * 移除一个车辆，空闲一个车位，并通知当前层 有一个新的车位可用
     */
    public void removeVehicle() {
        level.spotFreed();
        this.vehicle = null;
    }

    public int getRow() {
        return row;
    }

    public Level getLevel() {
        return level;
    }

    public VehicleSize getVehicleSize() {
        return vehicleSize;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void print() {
        if (vehicle == null) {
            if (vehicleSize == VehicleSize.Compact) {
                System.out.print("Compact");
            } else if (vehicleSize == VehicleSize.Large) {
                System.out.print("Large");
            } else if (vehicleSize == VehicleSize.Motorcycle) {
                System.out.print("Motorcycle");
            }
        } else {
            vehicle.print();
        }
    }

}
