/*
 * @(#)CarManager.java 2015. 4. 14.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.manager;

import java.util.Iterator;
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

	/**
	 * 전체보기 메소드
	 * 
	 * 차량이 없을경우 메세지를 출력합니다. 각 차량에 대해 출력메소드를 호출합니다.
	 * 
	 * @return 전체 차량 정보 스트링
	 */
	public String getAllImformation() {
		if (cars.size() == 0) {
			return "!! 등록된 차량이 없습니다 !!";
		}

		Iterator<Car> carIterator = cars.values().iterator();
		String sumInformation = "";

		// 전체 차량에 대해 정보출력 함수 호출
		while (carIterator.hasNext()) {
			Car currentCar = carIterator.next();

			sumInformation = sumInformation + currentCar.getCarInformation();
		}

		return sumInformation;
	}

	public Map<String, Car> getCars() {
		return cars;
	}

	public void setCars(Map<String, Car> cars) {
		this.cars = cars;
	}
}
