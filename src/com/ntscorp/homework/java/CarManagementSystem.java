/*
 * @(#)CarManagementSystem.java 2015. 4. 14.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java;

import java.util.Map;
import java.util.Scanner;

import com.ntscorp.homework.java.car.Car;
import com.ntscorp.homework.java.manager.CalculationManager;
import com.ntscorp.homework.java.manager.CarManager;
import com.ntscorp.homework.java.manager.CarOrderManager;
import com.ntscorp.homework.java.manager.DataManager;
import com.ntscorp.homework.java.manager.FileInputManager;
import com.ntscorp.homework.java.order.CarOrder;

/**
 * @author hyeseong.kwon@nhn.com
 * 
 *         차량관리시스템 클래스 (메뉴를 통해 관리)
 * 
 *         carManager 자동차쪽 로직 관리자<br>
 *         carOrderManager 운행지시서쪽 로직 관리자<br>
 *         fileInputSystem 파일입력 관리자<br>
 *         calculationManager 연료 소비량 계산 관리자<br>
 *         inputNumber 메뉴입력 변수
 */
public class CarManagementSystem {
	static final int FILE_READ_MENU = 1; // 1. 파일정보 불러오기 메뉴
	static final int ALL_CAR_PRINT_MENU = 2; // 2. 전체보기 메뉴
	static final int FUEL_CONSUMPTION_PRINT_MENU = 3; // 3. 연료소비량 보기 메뉴
	static final int EACH_CAR_FUEL_CONSUMPTION_MENU = 14; // 14. 차량별 연료소비량메뉴
	static final int EACH_DAY_FUEL_CONSUMPTION_MENU = 24; // 24. 일자별 연료소비량 메뉴
	static final int RETURN_MAIN_MENU = 33; // 33. 이전메뉴
	static final int END_PROGRAM_MENU = 99; // 99. 종료 메뉴

	private CarManager carManager;
	private CarOrderManager carOrderManager;
	private DataManager fileInputSystem;
	private CalculationManager calculationManager;
	private Scanner scanner;
	private int inputNumber;

	public CarManagementSystem() {
		carManager = new CarManager();
		carOrderManager = new CarOrderManager();
		fileInputSystem = new FileInputManager();
		calculationManager = new CalculationManager(carManager, carOrderManager);
		scanner = new Scanner(System.in);
		inputNumber = 0;
	}

	/**
	 * 메인메뉴
	 */
	public void showMainMenu() {
		try {
			while (inputNumber != END_PROGRAM_MENU) { // 99. 종료메뉴 일때까지반복
				printMainMenu();

				inputNumber = scanner.nextInt();

				switch (inputNumber) {
				case FILE_READ_MENU: // 1. 파일정보 불러오기 메뉴
					readObjectFromFolder();
					break;

				case ALL_CAR_PRINT_MENU: // 2. 전체보기 메뉴
					printAllCarMenu();
					String carInformation = carManager.getAllImformation();
					System.out.println(carInformation);
					break;

				case FUEL_CONSUMPTION_PRINT_MENU: // 3. 연료소비량 출력 메뉴
					while (inputNumber != RETURN_MAIN_MENU) { // 33. 이전메뉴 일때까지반복
						showFuelConsumptionPrintMenu(); // 자동차 등록 메뉴 호출
					}
					break;

				case END_PROGRAM_MENU: // 99. 종료 메뉴
					if (scanner != null) {
						scanner.close();
					}
					break;
				}
			}
		} catch (java.util.InputMismatchException e) {
			System.out.println("!! 올바른 값을 넣어주세요 !!");
		} finally {
			printEndProgramMenu();

			if (scanner != null) {
				scanner.close();
			}
		}
	}

	/**
	 * 
	 * 연료소비량 출력 메뉴
	 * 
	 * @throws java.util.InputMismatchException
	 */
	public void showFuelConsumptionPrintMenu() throws java.util.InputMismatchException {
		printFuelConsumptionMenu();

		inputNumber = scanner.nextInt();
		scanner.nextLine();

		String sumFuelConsumption = "0";

		switch (inputNumber) {
		case EACH_CAR_FUEL_CONSUMPTION_MENU: // 14. 차량별 연료소비량 보기 메뉴
			sumFuelConsumption = calculationManager.getAllCarFuelConsumption();
			break;

		case EACH_DAY_FUEL_CONSUMPTION_MENU: // 24. 일자별 연료소비량 보기 메뉴
			sumFuelConsumption = calculationManager.getAllOrderFuelConsumption();
			break;

		default:
			return;
		}

		System.out.println(sumFuelConsumption);
	}

	/**
	 * 폴더의 데이터를 객체로 저장하는 메소드
	 */
	public void readObjectFromFolder() {
		Map<String, Car> cars = fileInputSystem.readList();
		Map<String, CarOrder> carOrders = fileInputSystem.readOrder();

		if (cars != null && carOrders != null) {
			carManager.setCars(cars); // list 폴더의 파일을 불러옴
			carOrderManager.setCarOrders(carOrders); // order 폴더의 파일을 불러옴
			System.out.println("** 성공적으로 데이터를 불러왔습니다. **");
		} else {
			System.out.println("!! 데이터를 불러오는데 실패하였습니다. !!");
		}
		System.out.println("\n\n");
	}

	/**
	 * 메인메뉴 프린트문
	 */
	public static void printMainMenu() {
		System.out.println("\n\t<< Menu >>\n");
		System.out.println("\t1. 파일정보 불러오기");
		System.out.println("\t2. 전체보기");
		System.out.println("\t3. 연료소비량 출력");
		System.out.println("\t99. 종료\n");
		System.out.print("번호를 입력하세요 :  ");
	}

	/**
	 * 연료소비량 보기 메뉴 프린트문
	 */
	public static void printFuelConsumptionMenu() {
		System.out.println("\n\t<< 연료소비량 보기 >>\n");
		System.out.println("\t14. 차량별 연료소비량 보기");
		System.out.println("\t24. 일자별 연료소비량 보기");
		System.out.println("\t33. 상위메뉴\n");
		System.out.print("번호를 입력하세요 :  ");
	}

	/**
	 * 전체보기메뉴 프린트문
	 */
	public static void printAllCarMenu() {
		System.out.println("\n\t<< 전체보기 >>\n");
	}

	/**
	 * 종료메뉴 프린트문
	 */
	public static void printEndProgramMenu() {
		System.out.println("\n\t차량 관리 시스템을 종료합니다.\n");
	}

	public CarManager getCarManager() {
		return carManager;
	}

	public void setCarManager(CarManager carManager) {
		this.carManager = carManager;
	}

	public CarOrderManager getCarOrderManager() {
		return carOrderManager;
	}

	public void setCarOrderManager(CarOrderManager carOrderManager) {
		this.carOrderManager = carOrderManager;
	}

	public DataManager getFileInputSystem() {
		return fileInputSystem;
	}

	public void setFileInputSystem(DataManager fileInputSystem) {
		this.fileInputSystem = fileInputSystem;
	}

	public CalculationManager getCalculationManager() {
		return calculationManager;
	}

	public void setCalculationManager(CalculationManager calculationManager) {
		this.calculationManager = calculationManager;
	}
}
