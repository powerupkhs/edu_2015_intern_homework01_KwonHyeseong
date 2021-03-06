/*
 * @(#)CalculationManagerTest.java 2015. 4. 22.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.manager;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import com.ntscorp.homework.java.car.Bus;
import com.ntscorp.homework.java.car.Car;
import com.ntscorp.homework.java.car.CarInfo;
import com.ntscorp.homework.java.car.Truck;
import com.ntscorp.homework.java.order.CarOrder;
import com.ntscorp.homework.java.order.CarOrderInfo;

/**
 * @author hyeseong.kwon@nhn.com
 */
public class CalculationManagerTest {
	private static Logger logger = Logger.getLogger(CalculationManagerTest.class.getName());
	
	private CarManager carManager;
	private CarOrderManager carOrderManager;
	private CalculationManager carCalculationManager;
	private CarOrder carOrder;
	private final static double DELTA = 0.001;

	/**
	 * carManager, carOrderManager 객체를 생성하고 비교할 자동차와 지시서 임시 객체를 생성
	 * 
	 * @throws IOException
	 */
	@Before
	public void setUp() throws IOException {
		carManager = new CarManager();
		carOrderManager = new CarOrderManager();
		carCalculationManager = new CalculationManager(carManager, carOrderManager);

		Map<String, Car> cars = new TreeMap<String, Car>();
		cars.put("경기1모1234", new Bus("경기1모1234", new CarInfo(10.0)));
		cars.put("경기2모1234", new Truck("경기2모1234", new CarInfo(8.4)));
		cars.put("경남2요9577", new Bus("경남2요9577", new CarInfo(64.7)));

		Map<String, CarOrder> carOrders = new TreeMap<String, CarOrder>();
		Map<String, CarOrderInfo> driveData = new HashMap<String, CarOrderInfo>();
		driveData.put("경기1모1234", new CarOrderInfo(5));
		driveData.put("경기2모1234", new CarOrderInfo(10));
		driveData.put("경남2요9577", new CarOrderInfo(21));
		carOrder = new CarOrder("20150101");
		carOrder.setDriveData(driveData);
		carOrders.put("20150101", carOrder);

		carManager.putAll(cars);
		carOrderManager.putAll(carOrders);
	}

	/**
	 * 입력받은 하나의 Car 객체에 대해 연료소비량을 구하는 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCalculateEachCarFuelConsumption() throws Exception {
		// given
		Bus bus = new Bus("경남2요9577", new CarInfo(64.7));
		double expectedResult = 0.32457496136012365; // 21 / (64.7 * 1.0) = 운행거리 / (연비*보정된연비)
		
		// when 
		double actualResult = carCalculationManager.calculateEachCarFuelConsumption(bus);

		// then
		assertEquals(expectedResult, actualResult, DELTA);
		logger.info("<calculateEachCarFuelConsumption>  기대값 : " + expectedResult + "   실제값 : " + actualResult + "\n");
	}

	

	/**
	 * 입력받은 하나의 Car 객체에 대해 연료소비량을 계산하는 테스트<br>
	 * (Car 객체는 존재하는데 운행지시서 기록에는 해당차가 없어서 NULL이 발생하는 경우)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCalculateEachCarFuelConsumptionNull() throws Exception {
		// given
		Map<String, CarOrder> carOrders = new TreeMap<String, CarOrder>();
		Map<String, CarOrderInfo> driveData = new HashMap<String, CarOrderInfo>();
		driveData.put("경기1모1234", new CarOrderInfo(5));
		driveData.put("경기2모1234", new CarOrderInfo(10));
		carOrder = new CarOrder("20150101");
		carOrder.setDriveData(driveData);
		carOrders.put("20150101", carOrder);
		carOrderManager.putAll(carOrders);

		Bus bus = new Bus("경남2요9577", new CarInfo(64.7));
		double expectedResult = 0.0;

		// when 
		double actualResult = carCalculationManager.calculateEachCarFuelConsumption(bus);

		// then
		assertEquals(expectedResult, actualResult, DELTA);
		logger.info("<calculateEachCarFuelConsumptionNull>  기대값 : " + expectedResult + "   실제값 : " + actualResult + "\n");
	}

	/**
	 * 전체 자동차 객체의 연료소비량을 계산하는 테스트
	 * 
	 * 각 차량별 운행거리 / (연비*보정된연비)<br>
	 * 0.5 = Math.round((5 / (10.0 * 1.0)) * 100.0) / 100.0<br>
	 * 1.32 = Math.round((10 / (8.4 * 0.9)) * 100.0) / 100.0<br>
	 * 0.32 = Math.round((21 / (64.7 * 1.0)) * 100.0) / 100.0
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllCarFuelConsumption() throws Exception {
		// given
		String expectedResult = "[Bus]\t[경기1모1234] 총 연료소비량 : 0.5 입니다.\n" + "[Truck]\t[경기2모1234] 총 연료소비량 : 1.32 입니다.\n" + "[Bus]\t[경남2요9577] 총 연료소비량 : 0.32 입니다.\n\n";

		// when 
		String actualResult = carCalculationManager.getAllCarFuelConsumption();

		// then
		assertEquals(expectedResult, actualResult);
		logger.info("<getAllCarFuelConsumption>  기대값 : " + expectedResult + "   실제값 : " + actualResult);
	}

	/**
	 * 입력받은 CarOrder 하나에 기록된 차량들 연료소비량을 계산하는 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCalculateEachOrderFuelConsumption() throws Exception {
		// given
		double expectedResult = 2.147326284111446; // (5 / (10.0 * 1.0)) + (10 / (8.4 * 0.9)) + (21 / (64.7 * 1.0)) = 각 차량마다 운행거리 / (연비*보정된연비)

		// when 
		double actualResult = carCalculationManager.calculateEachOrderFuelConsumption(carOrder);

		// then
		assertEquals(expectedResult, actualResult, DELTA);
		logger.info("<calculateEachOrderFuelConsumption>  기대값 : " + expectedResult + "   실제값 : " + actualResult + "\n");
	}

	/**
	 * 입력받은 CarOrder 하나에 기록된 차량들 연료소비량을 계산하는 테스트<br>
	 * (운행지시서에는 기록되어있는데 자동차 객체에는 없어서 NULL이 발생할때)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCalculateEachOrderFuelConsumptionNull() throws Exception {
		// given
		Map<String, Car> cars = new TreeMap<String, Car>();
		cars.put("경기1모1234", new Bus("경기1모1234", new CarInfo(10.0)));
		cars.put("경기2모1234", new Truck("경기2모1234", new CarInfo(8.4)));
		carManager.putAll(cars);
		double expectedResult = 1.8227513227513226; // (5 / (10.0 * 1.0)) + (10 / (8.4 * 0.9)) = 각 차량마다 운행거리 / (연비*보정된연비)

		// when 
		double actualResult = carCalculationManager.calculateEachOrderFuelConsumption(carOrder);

		// then
		assertEquals(expectedResult, actualResult, DELTA);
		logger.info("<calculateEachOrderFuelConsumptionNull>  기대값 : " + expectedResult + "   실제값 : " + actualResult + "\n");
	}

	/**
	 * 전체 운행지시서의 연료소비량을 계산하는 테스트
	 * 
	 * 각 차량별 운행거리 / (연비*보정된연비)<br>
	 * 2.15 = Math.round(((5 / (10.0 * 1.0)) + (10 / (8.4 * 0.9)) + (21 / (64.7 * 1.0))) * 100.0) / 100.0
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllOrderFuelConsumption() throws Exception {
		// given
		String expectedResult = "[20150101] 총 연료소비량 : " + 2.15 + " 입니다.\n\n";

		// when 
		String actualResult = carCalculationManager.getAllOrderFuelConsumption();

		// then
		assertEquals(expectedResult, actualResult);
		logger.info("<getAllOrderFuelConsumption>  기대값 : " + expectedResult + "   실제값 : " + actualResult);
	}
}
