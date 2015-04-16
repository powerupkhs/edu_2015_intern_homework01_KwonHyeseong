/*
 * @(#)Truck.java 2015. 4. 14.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.car;

/**
 * @author hyeseong.kwon@nhn.com
 */

/**
 * 자동차 클래스를 상속받은 트럭 클래스 (다형성으로 확장성을 높이기 위해)
 */
public class Truck extends Car {
	public Truck(String number, CarInfo info) {
		super(number, info);
		setType(Truck.class.getSimpleName());
		setAdjustedFuelEfficiency(0.9);
	}
}
