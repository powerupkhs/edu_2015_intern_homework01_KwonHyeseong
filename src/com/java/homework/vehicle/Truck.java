/*
 * @(#)Truck.java 2015. 4. 14.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.java.homework.vehicle;

/**
 * @author hyeseong.kwon@nhn.com
 */

/**
 * 자동차 클래스를 상속받은 트럭 클래스 (다형성으로 확장성을 높이기 위해)
 */
public class Truck extends Vehicle {

	public Truck() {

	}

	public Truck(int id, String vehicleString, double fuelEfficiency) {
		super(id, vehicleString, fuelEfficiency, VehicleTypeEnum.TRUCK);
	}

	@Override
	public String printVehicleInformation() {
		return new String(getVehicleType().getName() + "\t id : " + getUniqueId() + "\t 차량번호 : " + getVehicleNumber() + "\t 연비 : " + getFuelEfficiency() + "\n");
	}
}
