/*
 * @(#)VehicleTypeEnum.java 2015. 4. 14.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.java.homework.vehicle;

/**
 * @author hyeseong.kwon@nhn.com
 */

/**
 * 차량의 종류를 이넘으로 정의
 */
public enum VehicleTypeEnum {
	BUS("Bus"), TRUCK("Truck");

	final private String name;

	private VehicleTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
