/*
 * @(#)VehicleProjectMain.java 2015. 4. 14.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.java.homework.vehicle;

/**
 * @author hyeseong.kwon@nhn.com
 */

/**
 * 메인 클래스
 * 
 * 관리 시스템을 통해 메뉴 호출
 */
public class VehicleProjectMain {
	public static void main(String[] args) {

		VehicleManagementSystem vehicleManagementSystem = new VehicleManagementSystem();
		vehicleManagementSystem.showMainMenu();
	}
}
