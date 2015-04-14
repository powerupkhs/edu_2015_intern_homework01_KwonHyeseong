/*
 * @(#)Vehicle.java 2015. 4. 14.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.java.homework.vehicle;

/**
 * @author hyeseong.kwon@nhn.com
 * 
 */

/**
 * 자동차 클래스 (각 타입의 자동차를 생성하기 위한 부모 클래스)
 */
abstract class Vehicle {

	/**
	 * 차량의 종류, id, 차량번호, 연비 멤버 변수
	 */
	private VehicleTypeEnum vehicleType;
	private int uniqueId;
	private String vehicleNumber;
	private double fuelEfficiency;

	public Vehicle() {

	}

	public Vehicle(int id, String vehicleString, double fuelEfficiency, VehicleTypeEnum vehicleType) {
		this.uniqueId = id;
		this.vehicleNumber = vehicleString;
		this.fuelEfficiency = fuelEfficiency;
		this.vehicleType = vehicleType;
	}

	/**
	 * 차량 정보 출력 메소드
	 * 
	 * @return 스트링으로 변환된 정보
	 */
	public abstract String printVehicleInformation();

	public VehicleTypeEnum getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleTypeEnum vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public double getFuelEfficiency() {
		return fuelEfficiency;
	}

	public void setFuelEfficiency(double fuelEfficiency) {
		this.fuelEfficiency = fuelEfficiency;
	}

	// 추후 Vehicle 객체 비교를 위한 오버라이드
	@Override
	public int hashCode() {
		String hashTemp = vehicleNumber + uniqueId + fuelEfficiency;
		return hashTemp.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if ((obj != null) && (obj instanceof Vehicle)) {
			Vehicle vehicle = (Vehicle) obj;
			if (this.uniqueId == vehicle.uniqueId) {
				result = true;
			}
		}
		return result;
	}
}
