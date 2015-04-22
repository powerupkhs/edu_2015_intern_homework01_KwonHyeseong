/*
 * @(#)CarProjectMain.java 2015. 4. 14.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java;

/**
 * @author hyeseong.kwon@nhn.com
 *
 *         메인 클래스
 * 
 *         관리 시스템을 생성하고 메인메뉴를 출력한다.
 */
public class CarProjectMain {
	public static void main(String[] args) {
		CarManagementSystem carManagementSystem = new CarManagementSystem();
		carManagementSystem.startMain();
	}
}