/*
 * @(#)CarOrderManager.java 2015. 4. 17.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.manager;

import java.util.Map;
import java.util.TreeMap;

import com.ntscorp.homework.java.order.CarOrder;

/**
 * @author hyeseong.kwon@nhn.com
 * 
 *         운행지시서 관리 매니저 (운행지시서를 관리하는(등록,삭제,출력 등) 메소드 클래스, 계산로직은
 *         CalculationManager로 분리)
 * 
 *         현재는 운행지시서를 변경하는 기능이 없음
 * 
 *         carOrders 운행지시서들 저장 변수
 */
public class CarOrderManager {
	private Map<String, CarOrder> carOrders;

	public CarOrderManager() {
		carOrders = new TreeMap<String, CarOrder>();
	}

	public Map<String, CarOrder> getCarOrders() {
		return carOrders;
	}

	public void setCarOrders(Map<String, CarOrder> carOrders) {
		this.carOrders = carOrders;
	}
}
