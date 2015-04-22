/*
 * @(#)CalculationManager.java 2015. 4. 20.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.manager;

import java.util.Iterator;
import java.util.Map;

import com.ntscorp.homework.java.car.Car;
import com.ntscorp.homework.java.order.CarOrder;
import com.ntscorp.homework.java.order.CarOrderInfo;

/**
 * @author hyeseong.kwon@nhn.com
 * 
 *         연료 소비량 계산 클래스 (계산에 관련된 로직을 분리)
 * 
 *         carManager 시스템과 카매니저 객체 연동<br>
 *         carOrderManager 시스템과 카오더매니저 객체 연동
 * 
 *         연료 소비량 = 운행거리 / 보정된연비로 계산한다. (보정된 연비 : Bus는 연비*1.0, Truck은 연비*0.9로계산)
 */
public class CalculationManager {
	private CarManager carManager;
	private CarOrderManager carOrderManager;

	public CalculationManager(CarManager carManager, CarOrderManager carOrderManager) {
		this.carManager = carManager;
		this.carOrderManager = carOrderManager;
	}

	/**
	 * 전체차량의 연료소비량 계산 메소드
	 * 
	 * 차량별로 운행지시서 전체에 검색하여 총 연료 소비량을 계산
	 * 
	 * @return 전체차량별 연료소비량 스트링
	 */
	public String getAllCarFuelConsumption() {
		Iterator<Car> carIterator = carManager.getValues().iterator();
		String sumCarFuelConsumption = "";

		while (carIterator.hasNext()) {
			Car currentCar = carIterator.next();

			double currentCarFuelConsumption = calculateEachCarFuelConsumption(currentCar);
			sumCarFuelConsumption = sumCarFuelConsumption + currentCar.getCarFuelConsumption() + Math.round(currentCarFuelConsumption * 100.0) / 100.0 + " 입니다.\n";
		}

		return sumCarFuelConsumption + "\n";
	}

	/**
	 * 차량하나의 연료소비량 계산 메소드
	 * 
	 * 차량을 운행지시서 전체에 검색하여 총 연료 소비량을 계산
	 * 
	 * @param inputCar
	 *            연료소비량을 계산할 차량
	 * @return 해당 차량의 총 연료 소비량
	 */
	public double calculateEachCarFuelConsumption(Car inputCar) {
		double totalFuelConsumption = 0.0;
		double fuelConsumption = 0.0;

		Iterator<CarOrder> carOrdersIterator = carOrderManager.getValues().iterator();

		while (carOrdersIterator.hasNext()) {
			CarOrder currentCarOrder = carOrdersIterator.next();
			Map<String, CarOrderInfo> driveData = currentCarOrder.getDriveData();
			CarOrderInfo carOrderInfo = driveData.get(inputCar.getNumber());

			if (carOrderInfo != null) {
				Double runDistance = driveData.get(inputCar.getNumber()).getDriveDistance();
				fuelConsumption = runDistance.doubleValue() / (inputCar.getInfo().getFuelEfficiency() * inputCar.getAdjustedFuelEfficiency());
				totalFuelConsumption = totalFuelConsumption + fuelConsumption;
			}
		}

		return totalFuelConsumption;
	}

	/**
	 * 전체운행지시서의 연료소비량 계산 메소드
	 * 
	 * 운행지시서안에 있는 차량 전체를 검색하여 총 연료 소비량을 계산
	 * 
	 * @return 전체운행지시서별 연료소비량 스트링
	 */
	public String getAllOrderFuelConsumption() {
		Iterator<CarOrder> carOrderIterator = carOrderManager.getValues().iterator();
		String sumCarOrderFuelConsumption = "";

		while (carOrderIterator.hasNext()) {
			CarOrder currentCarOrder = carOrderIterator.next();

			double currentCarFuelConsumption = calculateEachOrderFuelConsumption(currentCarOrder);
			sumCarOrderFuelConsumption = sumCarOrderFuelConsumption + currentCarOrder + Math.round(currentCarFuelConsumption * 100.0) / 100.0 + " 입니다.\n";
		}

		return sumCarOrderFuelConsumption + "\n";
	}

	/**
	 * 운행지시서 하나의 연료소비량 계산 메소드
	 * 
	 * 운행지시서 하나에 입력된 차량 전체를 검색하여 총 연료 소비량을 계산
	 * 
	 * @param inputCarOrder
	 *            연료소비량을 계산할 운행지시서
	 * @return 해당 운행지시서의 총 연료 소비량
	 */
	public double calculateEachOrderFuelConsumption(CarOrder inputCarOrder) {
		double totalFuelConsumption = 0.0;
		double runDistance = 0.0;
		double fuelConsumption = 0.0;

		Iterator<String> carOrderIterator = inputCarOrder.getDriveData().keySet().iterator();

		while (carOrderIterator.hasNext()) {
			String currentCarNumber = carOrderIterator.next();

			Car currentCar = carManager.getCar(currentCarNumber);

			if (currentCar == null) {
				continue;
			}

			runDistance = inputCarOrder.getDriveData().get(currentCarNumber).getDriveDistance();

			fuelConsumption = runDistance / (currentCar.getInfo().getFuelEfficiency() * currentCar.getAdjustedFuelEfficiency());
			totalFuelConsumption = totalFuelConsumption + fuelConsumption;
		}

		return totalFuelConsumption;
	}
}
