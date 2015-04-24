/*
 * @(#)Car.java 2015. 4. 14.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.car;

/**
 * @author hyeseong.kwon@nhn.com
 *
 *         자동차 클래스 (각 타입의 자동차를 생성하기 위한 부모 클래스)
 * 
 *         type 자동차 타입<br>
 *         number 차량번호<br>
 *         info 자동차 스펙 관리<br>
 *         adjustedFuelEfficiency 보정된 연비
 */
public abstract class Car {
	private String type;
	private String number;
	private CarInfo info;
	private double adjustedFuelEfficiency;

	public Car(String number, CarInfo info) {
		this.number = number;
		this.info = info;
	}

	/**
	 * 총 연료소비량 출력 메소드
	 * 
	 * @return 스트링으로 변환된 정보
	 */
	public String getCarFuelConsumption() {
		return "[" + type + "]\t[" + number + "] 총 연료소비량 : ";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAdjustedFuelEfficiency() {
		return adjustedFuelEfficiency;
	}

	public void setAdjustedFuelEfficiency(double adjustedFuelEfficiency) {
		this.adjustedFuelEfficiency = adjustedFuelEfficiency;
	}

	public String getNumber() {
		return number;
	}

	public CarInfo getInfo() {
		return info;
	}

	public void setInfo(CarInfo info) {
		this.info = info;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Car other = (Car) obj;
		if (number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!number.equals(other.number)) {
			return false;
		}

		return true;
	}
}
