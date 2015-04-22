package com.ntscorp.homework.java;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ntscorp.homework.java.car.Car;
import com.ntscorp.homework.java.manager.CalculationManager;
import com.ntscorp.homework.java.manager.DataManager;
import com.ntscorp.homework.java.manager.FileInputManager;
import com.ntscorp.homework.java.order.CarOrder;

/*
 * @(#)TestCarManagementSystem.java 2015. 4. 22.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

/**
 * @author hyeseong.kwon@nhn.com
 */
@RunWith(MockitoJUnitRunner.class)
public class TestCarManagementSystem {
	@Mock
	private DataManager fileInputSystem;

	@Mock
	private CalculationManager calculationManager;

	@InjectMocks
	private CarManagementSystem carManagementSystem = new CarManagementSystem();

	/**
	 * 메인시스템에서 연료소비량 계산 메소드를 호출할때 값 반환을 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void showFuelConsumptionTest() throws Exception {
		// given
		when(calculationManager.getAllCarFuelConsumption()).thenReturn("success");
		when(calculationManager.getAllOrderFuelConsumption()).thenReturn(" success");
		String expectedResult = "success success";
		// when 
		String actualResult = carManagementSystem.getFuelConsumption();

		// then
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * 메인시스템에서 파일리드를 호출할때 성공적으로 끝내는 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void callFileInputManagerTest() throws Exception {
		// given
		Map<String, Car> cars = new TreeMap<String, Car>();
		Map<String, CarOrder> carOrders = new TreeMap<String, CarOrder>();
		when(fileInputSystem.readList(FileInputManager.FOLDER_LIST_PATH)).thenReturn(cars);
		when(fileInputSystem.readOrder(FileInputManager.FOLDER_ORDER_PATH)).thenReturn(carOrders);
		String expectedResult = "** 성공적으로 데이터를 불러왔습니다. **\n";
		// when 
		String actualResult = carManagementSystem.callFileInputOperation();

		// then
		assertEquals(expectedResult, actualResult);
	}

	/**
	 * 메인시스템에서 파일리드를 호출할때 실패하는 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void callFileInputManagerNullTest() throws Exception {
		// given
		when(fileInputSystem.readList(FileInputManager.FOLDER_LIST_PATH)).thenReturn(null);
		when(fileInputSystem.readOrder(FileInputManager.FOLDER_ORDER_PATH)).thenReturn(null);
		String expectedResult = "!! 데이터를 불러오는데 실패하였습니다. !!\n";
		// when 
		String actualResult = carManagementSystem.callFileInputOperation();

		// then
		assertEquals(expectedResult, actualResult);
	}
}
