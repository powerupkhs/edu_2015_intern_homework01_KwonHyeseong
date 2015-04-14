/*
 * @(#)VehicleManager.java 2015. 4. 14.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.java.homework.vehicle;

/**
 * @author hyeseong.kwon@nhn.com
 */

/**
 * 자동차 관리 시스템 (모든 작업을 관리) (추후에 파일을 통해 불러오거나 저장하는 시스템도 이곳에 구현)
 */
public class VehicleManager {

	/**
	 * 차량을 등록할때마다 증가하는 ID 생성기와 최대차량등록 개수 초기값
	 * 
	 * 전체 자동차 저장 배열과 현재 자동차 개수 변수
	 */
	static int uniqueIdGenerator = 0;
	int maxVehicleNumber = 100;

	private Vehicle totalVehicle[];
	private int countVehicle;

	public VehicleManager() {
		totalVehicle = new Vehicle[maxVehicleNumber];
		countVehicle = 0;
	}

	/**
	 * 차량등록 메소드
	 * 
	 * 차량 배열이 부족하면 2배로 늘립니다. 차량 타입을 확인한 후 객체를 생성하여 추가해줍니다.
	 * 
	 * @param 등록할차량
	 * @return 차량 등록 여부
	 */
	public boolean registerVehicle(VehicleTypeEnum vehicleType, String inputVehicleNumber, double inputFuelEfficiency) {

		Vehicle newVehicle = null;

		// 차량 배열이 부족하면 2배로 늘림
		if (countVehicle == maxVehicleNumber - 1) {
			if (increaseArrayCapacity() == false) {
				return false;
			}
		}

		if (vehicleType == VehicleTypeEnum.BUS) {
			newVehicle = new Bus(VehicleManager.uniqueIdGenerator, inputVehicleNumber, inputFuelEfficiency);
		} else if (vehicleType == VehicleTypeEnum.TRUCK) {
			newVehicle = new Truck(VehicleManager.uniqueIdGenerator, inputVehicleNumber, inputFuelEfficiency);
		}

		totalVehicle[countVehicle] = newVehicle;
		countVehicle++;
		uniqueIdGenerator++;

		return true;
	}

	/**
	 * 차량삭제 메소드
	 * 
	 * 유효하지 않은 인덱스일 경우 false를 리턴합니다. 유효한 인덱스일 경우 배열을 앞으로 이동해서 객체를 삭제합니다.
	 * 
	 * @param 차량의ID
	 * @return 삭제여부
	 */
	public boolean removeVehicle(int inputId) {

		// 유효하지 않은 인덱스일 경우 리턴
		if (inputId < 0 || inputId > uniqueIdGenerator) {
			return false;
		}

		// id로 차량을 검색해서 있으면 삭제
		for (int i = 0; i < countVehicle; i++) {
			if (totalVehicle[i].getUniqueId() == inputId) {
				for (int j = i; j < countVehicle; j++) {
					totalVehicle[j] = totalVehicle[j + 1];
				}

				countVehicle--;
				return true;
			}
		}

		return false;
	}

	/**
	 * 전체보기 메소드
	 * 
	 * 차량이 없을경우 메세지를 출력합니다. 각 차량에 대해 출력메소드를 호출합니다.
	 * 
	 * @return 전체 차량 정보
	 */
	public String printAllVehicle() {

		if (countVehicle == 0) {
			return "!! 등록된 차량이 없습니다 !!";
		}

		String sumInformation = "";

		// 전체 차량에 대해 정보출력 함수 호출
		for (int i = 0; i < countVehicle; i++) {
			sumInformation = sumInformation + totalVehicle[i].printVehicleInformation();
		}

		return sumInformation;
	}

	/**
	 * 배열확장 메소드
	 * 
	 * 차량 배열이 부족하면 2배로 늘립니다. 메모리가 부족할경우 false를 리턴합니다.
	 * 
	 * @return 배열확장 성공 여부
	 */
	private boolean increaseArrayCapacity() {
		try {
			maxVehicleNumber = maxVehicleNumber * 2;

			Vehicle tempTotalVehicle[] = totalVehicle.clone();
			totalVehicle = new Vehicle[maxVehicleNumber];

			for (int i = 0; i < countVehicle; i++) {
				totalVehicle[i] = tempTotalVehicle[i];
			}

			tempTotalVehicle = null; // gc를 위해 null값 지정

			return true;
		} catch (OutOfMemoryError e) {
			return false;
		}
	}

	public Vehicle[] getTotalVehicle() {
		return totalVehicle;
	}

	public void setTotalVehicle(Vehicle[] totalVehicle) {
		this.totalVehicle = totalVehicle;
	}

	public int getCountVehicle() {
		return countVehicle;
	}

	public void setCountVehicle(int countVehicle) {
		this.countVehicle = countVehicle;
	}
}
