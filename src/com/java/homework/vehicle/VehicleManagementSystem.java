/*
 * @(#)VehicleManagementSystem.java 2015. 4. 14.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.java.homework.vehicle;

import java.util.Scanner;

/**
 * @author hyeseong.kwon@nhn.com
 */

/**
 * 차량관리 클래스 (메뉴를 통해 관리)
 */
public class VehicleManagementSystem {
	static final int VEHICLE_REGISTRATION_MENU = 1; // 1. 차량등록 메뉴
	static final int VEHICLE_REMOVE_MENU = 2; // 2. 차량삭제 메뉴
	static final int ALL_VEHICLE_PRINT_MENU = 3; // 3. 전체보기 메뉴
	static final int BUS_REGISTRATION_MENU = 11; // 11. 버스등록 메뉴
	static final int TRUCK_REGISTRATION_MENU = 22; // 22. 트럭등록 메뉴
	static final int RETURN_MAIN_MENU = 33; // 33. 이전메뉴
	static final int END_PROGRAM_MENU = 99; // 99. 종료 메뉴

	/**
	 * 차량관리시스템 객체 (모든 작업 관리)
	 */
	VehicleManager vehicleManager;
	Scanner scanner;
	int inputNumber;

	public VehicleManagementSystem() {
		vehicleManager = new VehicleManager();
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
				case VEHICLE_REGISTRATION_MENU: // 1. 차량등록 메뉴
					while (inputNumber != RETURN_MAIN_MENU) { // 33. 이전메뉴 일때까지반복
						showVehicleRegistrasionMenu(); // 자동차 등록 메뉴 호출
					}
					break;

				case VEHICLE_REMOVE_MENU: // 2. 차량삭제 메뉴
					printRemoveMenu();

					inputNumber = scanner.nextInt();

					// ID로 검색 후 삭제
					boolean isSearchVehicle = vehicleManager.removeVehicle(inputNumber);

					if (isSearchVehicle) {
						System.out.println("\n정상적으로 삭제되었습니다.\n");
					} else {
						System.out.println("\nID에 해당하는 차량이 없습니다.\n");
					}
					break;

				case ALL_VEHICLE_PRINT_MENU: // 3. 전체보기 메뉴
					printAllVehicleMenu();
					String vehicleInformation = vehicleManager.printAllVehicle();
					System.out.println(vehicleInformation);
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
	 * 차량등록 메뉴
	 */
	public void showVehicleRegistrasionMenu() throws java.util.InputMismatchException {
		String inputVehicleNumber;
		double inputFuelEfficiency;
		VehicleTypeEnum vehicleType = null;

		printRegistrationMenu();

		inputNumber = scanner.nextInt();
		scanner.nextLine();

		switch (inputNumber) {
		case BUS_REGISTRATION_MENU: // 11. 버스등록 메뉴
			vehicleType = VehicleTypeEnum.BUS;
			break;

		case TRUCK_REGISTRATION_MENU: // 22. 트럭등록 메뉴
			vehicleType = VehicleTypeEnum.TRUCK;
			break;

		default:
			return;
		}

		System.out.print(vehicleType.getName() + "을/를 등록합니다. 차량번호를 입력하세요. : ");
		inputVehicleNumber = scanner.nextLine();
		System.out.print("연비를 입력하세요. : ");
		inputFuelEfficiency = scanner.nextDouble();

		// 차량 추가
		boolean isRegistrationVehicle = vehicleManager.registerVehicle(vehicleType, inputVehicleNumber, inputFuelEfficiency);

		if (isRegistrationVehicle) {
			System.out.println("\n정상적으로 등록되었습니다.\n");
		} else {
			System.out.println("\n등록에 실패했습니다.\n");
		}
	}

	/**
	 * 메인메뉴 프린트문
	 */
	public static void printMainMenu() {
		System.out.println("\n\t<< Menu >>\n");
		System.out.println("\t1. 차량등록");
		System.out.println("\t2. 차량삭제");
		System.out.println("\t3. 전체보기");
		System.out.println("\t99. 종료\n");
		System.out.print("번호를 입력하세요 :  ");
	}

	/**
	 * 차량등록메뉴 프린트문
	 */
	public static void printRegistrationMenu() {
		System.out.println("\n\t<< 차량등록 >>\n");
		System.out.println("\t11. 버스");
		System.out.println("\t22. 트럭");
		System.out.println("\t33. 상위메뉴\n");
		System.out.print("번호를 입력하세요 :  ");
	}

	/**
	 * 차량삭제메뉴 프린트문
	 */
	public static void printRemoveMenu() {
		System.out.println("\n\t<< 차량삭제 >>\n");
		System.out.print("삭제할 차량의 ID를 입력하세요 :  ");
	}

	/**
	 * 전체보기메뉴 프린트문
	 */
	public static void printAllVehicleMenu() {
		System.out.println("\n\t<< 전체보기 >>\n");
	}

	/**
	 * 종료메뉴 프린트문
	 */
	public static void printEndProgramMenu() {
		System.out.println("\n\t차량 관리 시스템을 종료합니다.\n");
	}
}
