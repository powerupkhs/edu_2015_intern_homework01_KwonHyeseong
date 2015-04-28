/*
 * @(#)FileInputManager.java 2015. 4. 27.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.listener;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ntscorp.homework.java.car.Car;
import com.ntscorp.homework.java.manager.DataManager;
import com.ntscorp.homework.java.manager.FileInputManager;

/**
 * 컨텍스트 생성시 파일에서 Car정보를 가져오는 리스너
 * 
 * fileInputSystem 파일관리시스템
 * 
 * @author hyeseong.kwon@nhn.com
 */
public class FileInputListener implements ServletContextListener {
	private DataManager fileInputSystem;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		fileInputSystem = new FileInputManager();

		Map<String, Car> cars = fileInputSystem.readList(context.getRealPath("/") + "/WEB-INF/list");

		context.setAttribute("cars", cars);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}
}
