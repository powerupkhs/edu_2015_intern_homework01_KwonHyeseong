/*
 * @(#)CarManagementServlet.java 2015. 4. 28.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ntscorp.homework.java.car.Car;
import com.ntscorp.homework.java.manager.CalculationManager;
import com.ntscorp.homework.java.manager.CarManager;
import com.ntscorp.homework.java.manager.CarOrderManager;
import com.ntscorp.homework.java.order.CarOrder;
import com.ntscorp.homework.java.order.CarOrderInfo;
import com.ntscorp.homework.java.order.FuelConsumptionInfo;

/**
 * 자동차 관리 서블릿
 * 
 * cars 컨텍스트 생성시 파일의 정보를 불러와 객체 저장<br>
 * calculationManager 연료소모량 계산 매니저<br>
 * 
 * @author hyeseong.kwon@nhn.com
 */
public class CarManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, Car> cars;
	private CalculationManager calculationManager;

	@Override
	public void init() {
		cars = (TreeMap<String, Car>) getServletContext().getAttribute("cars");
		calculationManager = new CalculationManager(); 
	}

	/**
	 * 접속하면 차량별 운행거리를 입력하는 jsp로 이동
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/inputFuelConsumptionInfo.jsp");
		rd.forward(request, response);
	}

	/**
	 * 계산버튼을 누를 시 자동차 매니저와 운행 매니저를 만들어 연료소모량 계산 매니저를 통해 계산
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<Car, FuelConsumptionInfo> carFuelConsumptions = new TreeMap<Car, FuelConsumptionInfo>(); // jsp에 정보를 출력해줄 자동차, 연료소모량정보 객체 맵 생성
		
		// 연료소모량 계산 매니저를 통해 자동차별 연료소모량 계산
		for (String carNumber : cars.keySet()) {
			Car currentCar = cars.get(carNumber);
			double driveDistance = Double.parseDouble(request.getParameter(carNumber));
			
			double fuelConsumption = calculationManager.calculateEachCarFuelConsumption(currentCar, driveDistance);
			
			FuelConsumptionInfo fuelConsumptionInfo = new FuelConsumptionInfo(driveDistance, fuelConsumption); // 연료소모량정보 객체 생성(운행거리, 연료소모량)
			carFuelConsumptions.put(currentCar, fuelConsumptionInfo);
		}

		request.setAttribute("carFuelConsumptions", carFuelConsumptions); // jsp에 정보를 출력할 객체를 리퀘스트에 등록

		RequestDispatcher rd = request.getRequestDispatcher("/showFuelConsumption.jsp");
		rd.forward(request, response);
	}
}