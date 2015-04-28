/*
 * @(#)CarManager.java 2015. 4. 15.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.manager;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import com.ntscorp.homework.java.car.Car;

/**
 * @author hyeseong.kwon@nhn.com
 *
 *         자동차 관리 매니저 (자동차를 관리하는(등록,삭제,출력 등) 메소드 클래스, 계산로직은 CalculationManager로
 *         분리)
 * 
 *         cars 자동차들 저장 변수
 */
public class CarManager {
	private Map<String, Car> cars;

	public CarManager() {
		cars = new TreeMap<String, Car>(); // 정렬된 출력을 위해 트리맵 사용
	}

	public Collection<Car> getValues() {
		return cars.values();
	}

	public Car getCar(String carNumber) {
		return cars.get(carNumber);
	}

	public void putAll(Map<String, Car> cars) {
		this.cars = cars;
	}
}
