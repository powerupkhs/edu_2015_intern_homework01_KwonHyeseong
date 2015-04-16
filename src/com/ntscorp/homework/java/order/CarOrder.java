/*
 * @(#)CarOrder.java 2015. 4. 16.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.order;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hyeseong.kwon@nhn.com
 * 
 *         차량 운행 지시서 클래스
 * 
 *         date 운행지시 날짜<br>
 *         driveData 운행정보 맵
 */
public class CarOrder {
	private String date;
	private Map<String, CarOrderInfo> driveData;

	public CarOrder(String date) {
		this.date = date;
		this.driveData = new HashMap<String, CarOrderInfo>();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, CarOrderInfo> getDriveData() {
		return driveData;
	}

	public void setDriveData(Map<String, CarOrderInfo> driveData) {
		this.driveData = driveData;
	}

	/**
	 * 운행데이터 해쉬맵에 해당차량의 운행데이터를 추가하는 메소드
	 * 
	 * @param carNumber
	 *            차량번호
	 * @param runDistance
	 *            운행데이터
	 */
	public void addDriveData(String carNumber, CarOrderInfo carOrderInfo) {
		driveData.put(carNumber, carOrderInfo);
	}

	/**
	 * @return 운행지시날짜를 출력하도록 오버라이드
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + date + "] 총 연료소비량 : ";
	}
}
