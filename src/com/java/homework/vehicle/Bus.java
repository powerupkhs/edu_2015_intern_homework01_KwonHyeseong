/*
 * @(#)Bus.java 2015. 4. 14.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.java.homework.vehicle;

/**
 * @author hyeseong.kwon@nhn.com
 */

/**
 * 자동차 클래스를 상속받은 버스 클래스 (다형성으로 확장성을 높이기 위해)
 */
public class Bus extends Vehicle {

	public Bus() {

	}

	public Bus(int id, String vehicleString, double fuelEfficiency) {
		super(id, vehicleString, fuelEfficiency, VehicleTypeEnum.BUS);
	}

	@Override
	public String printVehicleInformation() {
		return new String(getVehicleType().getName() + "\t id : " + getUniqueId() + "\t 차량번호 : " + getVehicleNumber() + "\t 연비 : " + getFuelEfficiency() + "\n");
	}
}
