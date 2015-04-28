/*
 * @(#)Bus.java 2015. 4. 15.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.car;

/**
 * @author hyeseong.kwon@nhn.com
 * 
 *         자동차 클래스를 상속받은 버스 클래스 (다형성으로 확장성을 높이기 위해)
 */
public class Bus extends Car {
	public Bus(String number, CarInfo info) {
		super(number, info);
		setType(Bus.class.getSimpleName());
		setAdjustedFuelEfficiency(1.0);
	}

	@Override
	public int compareTo(Car car) {
		return this.getNumber().compareTo(car.getNumber());
	}
}
