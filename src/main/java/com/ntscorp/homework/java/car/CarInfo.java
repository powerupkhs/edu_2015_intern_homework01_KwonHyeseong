/*
 * @(#)CarInfo.java 2015. 4. 17.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.car;

/**
 * @author hyeseong.kwon@nhn.com
 * 
 *         자동차 스펙을 저장하는 클래스 (확장성을 위해 없어지거나 생기기 쉬운 변수들 따로 관리)
 * 
 *         fuelEfficiency 연비
 */
public class CarInfo {
	private double fuelEfficiency;

	public CarInfo(double fuelEfficiency) {
		this.fuelEfficiency = fuelEfficiency;
	}

	public double getFuelEfficiency() {
		return fuelEfficiency;
	}

	public void setFuelEfficiency(double fuelEfficiency) {
		this.fuelEfficiency = fuelEfficiency;
	}
}
