/*
 * @(#)TestCarOrder.java 2015. 4. 21.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.order;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * @author hyeseong.kwon@nhn.com
 * 
 * 
 */
public class TestCarOrder {
	/**
	 * 차량지시서 클래스(CarOrder) 클래스에 운행 정보를 등록하는 메소드 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void addDriveDataTest() throws Exception {
		// given
		Map<String, CarOrderInfo> driveData = new HashMap<String, CarOrderInfo>();
		driveData.put("서울1가4829", new CarOrderInfo(10));
		CarOrder carOrder = new CarOrder("20150101");

		// when
		carOrder.addDriveData("서울1가4829", new CarOrderInfo(10));

		// then
		assertEquals(driveData, carOrder.getDriveData());
	}
}
