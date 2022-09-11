package com.martix.x.pub.ood.parking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew-Geng on 22:43 2022/9/11
 * 交通工具
 */
public abstract class Vehicle {
    protected List<ParkingSpot> parkingSpotList = new ArrayList<>();

    protected String licensePlate;

    protected int spotsNeeded; //需要多少个停车位

    protected VehicleSize size;

    public List<ParkingSpot> getParkingSpotList() {
        return parkingSpotList;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getSpotsNeeded() {
        return spotsNeeded;
    }

    public VehicleSize getSize() {
        return size;
    }

    /**
     * 选择一个停车位停车
     *
     * @param parkingSpot
     */
    public void parkInSpot(ParkingSpot parkingSpot) {
        parkingSpotList.add(parkingSpot);
    }

    /**
     * 清空停车位
     */
    public void clearSpots() {
        for (ParkingSpot parkingSpot : parkingSpotList) {
            parkingSpot.removeVehicle();
        }

        parkingSpotList.clear();
    }

    /**
     * 判断是否能停进去
     *
     * @param parkingSpot
     * @return
     */
    public abstract boolean canFitInSpot(ParkingSpot parkingSpot);

    /**
     * 打印结果
     */
    public abstract void print();

}
