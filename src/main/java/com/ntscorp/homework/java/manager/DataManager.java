/*
 * @(#)DataManager.java 2015. 4. 17.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.manager;

import java.util.Map;

import com.ntscorp.homework.java.car.Car;
import com.ntscorp.homework.java.order.CarOrder;

/**
 * @author hyeseong.kwon@nhn.com
 * 
 *         데이터 관리 인터페이스
 */
public interface DataManager {
	/**
	 * 데이터 리드 메소드
	 * 
	 * @param folderRoute
	 *            읽어올 위치
	 * @return 객체집합
	 */
	public Map<String, Car> readList(String folderPath);

	public Map<String, CarOrder> readOrder(String folderPath);
}
