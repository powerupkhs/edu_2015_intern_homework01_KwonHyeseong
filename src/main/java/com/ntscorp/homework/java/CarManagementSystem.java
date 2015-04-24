/*
 * @(#)CarManagementSystem.java 2015. 4. 14.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java;

import java.util.Map;

import com.ntscorp.homework.java.car.Car;
import com.ntscorp.homework.java.manager.CalculationManager;
import com.ntscorp.homework.java.manager.CarManager;
import com.ntscorp.homework.java.manager.CarOrderManager;
import com.ntscorp.homework.java.manager.DataManager;
import com.ntscorp.homework.java.manager.FileInputManager;
import com.ntscorp.homework.java.order.CarOrder;

/**
 * @author hyeseong.kwon@nhn.com
 * 
 *         차량관리시스템 클래스 (메뉴를 통해 관리)
 * 
 *         carManager 자동차쪽 로직 관리자<br>
 *         carOrderManager 운행지시서쪽 로직 관리자<br>
 *         fileInputSystem 파일입력 관리자<br>
 *         calculationManager 연료 소비량 계산 관리자<br>
 */
public class CarManagementSystem {
	private CarManager carManager;
	private CarOrderManager carOrderManager;
	private DataManager fileInputSystem;
	private CalculationManager calculationManager;

	public CarManagementSystem() {
		carManager = new CarManager();
		carOrderManager = new CarOrderManager();
		fileInputSystem = new FileInputManager();
		calculationManager = new CalculationManager(carManager, carOrderManager);
	}

	/**
	 * 메인 작업 메소드 ( 시작 메소드 )
	 */
	public void startMain() {
		String result;
		result = callFileInputOperation(); // 파일 정보를 불러오는 메소드 호출
		System.out.println(result);

		result = getFuelConsumption(); // 연료 소비량을 출력하는 메소드 호출
		System.out.println(result);
	}

	/**
	 * 연료 소비량 관련 작업을 호출하는 메소드
	 */
	public String getFuelConsumption() {
		String sumFuelConsumption = "";
		sumFuelConsumption = sumFuelConsumption + calculationManager.getAllCarFuelConsumption();
		sumFuelConsumption = sumFuelConsumption + calculationManager.getAllOrderFuelConsumption();
		return sumFuelConsumption;
	}

	/**
	 * 파일 입력 관련 작업을 호출하는 메소드
	 */
	public String callFileInputOperation() {
		Map<String, Car> cars = fileInputSystem.readList(FileInputManager.FOLDER_LIST_PATH);
		Map<String, CarOrder> carOrders = fileInputSystem.readOrder(FileInputManager.FOLDER_ORDER_PATH);

		if (cars != null && carOrders != null) {
			carManager.putAll(cars); // list 폴더의 파일을 불러옴
			carOrderManager.putAll(carOrders); // order 폴더의 파일을 불러옴
			return "** 성공적으로 데이터를 불러왔습니다. **\n";
		} else {
			return "!! 데이터를 불러오는데 실패하였습니다. !!\n";
		}
	}

	public void setFileInputSystem(DataManager fileInputSystem) {
		this.fileInputSystem = fileInputSystem;
	}

	public void setCalculationManager(CalculationManager calculationManager) {
		this.calculationManager = calculationManager;
	}
}
