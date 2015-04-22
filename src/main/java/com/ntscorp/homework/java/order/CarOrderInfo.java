/*
 * @(#)CarOrderInfo.java 2015. 4. 16.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.order;

/**
 * @author hyeseong.kwon@nhn.com
 * 
 *         운행지시서 스펙 클래스 (지금은 운행지시서에 거리만 들어있지만 다른 변경사항에 유연하기 위해)
 * 
 *         driveDistance 운행거리
 */
public class CarOrderInfo {
	private double driveDistance;

	public CarOrderInfo(double driveDistance) {
		this.driveDistance = driveDistance;
	}

	public double getDriveDistance() {
		return driveDistance;
	}

	public void setDriveDistance(double driveDistance) {
		this.driveDistance = driveDistance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(driveDistance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarOrderInfo other = (CarOrderInfo) obj;
		if (Double.doubleToLongBits(driveDistance) != Double.doubleToLongBits(other.driveDistance))
			return false;
		return true;
	}
}
