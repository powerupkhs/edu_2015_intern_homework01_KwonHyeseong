/*
 * @(#)FuelConsumptionInfo.java 2015. 4. 28.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.order;

/**
 * jsp에 출력할 연료소비량 정보 클래스
 * 
 * driveDistance 운행거리<br>
 * fuelConsumption 연료소비량
 * 
 * @author hyeseong.kwon@nhn.com
 */
public class FuelConsumptionInfo {
	private double driveDistance;
	private double fuelConsumption;

	public FuelConsumptionInfo(double driveDistance, double fuelConsumption) {
		this.driveDistance = driveDistance;
		this.fuelConsumption = fuelConsumption;
	}

	public double getDriveDistance() {
		return driveDistance;
	}

	public void setDriveDistance(double driveDistance) {
		this.driveDistance = driveDistance;
	}

	public double getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}
}
