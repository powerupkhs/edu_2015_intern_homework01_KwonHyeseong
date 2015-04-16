/*
 * @(#)FileInputManager.java 2015. 4. 16.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.TreeMap;

import com.ntscorp.homework.java.car.Car;
import com.ntscorp.homework.java.car.CarInfo;
import com.ntscorp.homework.java.order.CarOrder;
import com.ntscorp.homework.java.order.CarOrderInfo;

/**
 * @author hyeseong.kwon@nhn.com
 *
 *         파일 입력 매니저 (데이터 관리 인터페이스 구현) 폴더에 있는 모든 파일에서 정보를 불러와 객체를 등록하는 매니저
 */
public class FileInputManager implements DataManager {
	static final int REMOVE_EXTENSION_NUMBER = 4; // 파일이름에서 확장자 제거하기 위한 넘버
	public static final String FOLDER_LIST_PATH = "/list"; // list 폴더 경로 (차량 정보 저장)
	public static final String FOLDER_ORDER_PATH = "/order"; // order 폴더 경로 (운행지시서 정보 저장)

	public FileInputManager() {

	}

	/**
	 *  List 폴더 전체의 파일을 읽어오는 메소드
	 * 
	 * @return 전체파일의 Car 객체들 Map
	 */
	@Override
	public Map<String, Car> readList() {
		Map<String, Car> cars = new TreeMap<String, Car>();
		File folder = new File(FOLDER_LIST_PATH);
		File fileList[] = folder.listFiles();

		// 폴더의 파일 하나하나마다 리드 메소드 호출
		for (int i = 0; i < fileList.length; i++) {
			String fileName = fileList[i].getName();

			Map<String, Car> inputCars = readListFromFile(fileName);

			if (inputCars == null) {
				return null;
			}

			cars.putAll(inputCars);
		}

		return cars;
	}

	/**
	 * Order 폴더 전체의 파일을 읽어오는 메소드
	 * 
	 * @return CarOrder 전체 Map
	 */
	@Override
	public Map<String, CarOrder> readOrder() {
		Map<String, CarOrder> carOrders = new TreeMap<String, CarOrder>();
		File folder = new File(FOLDER_ORDER_PATH);
		File fileList[] = folder.listFiles();

		// 폴더의 파일 하나하나마다 리드 메소드 호출
		for (int i = 0; i < fileList.length; i++) {
			String fileName = fileList[i].getName();
			CarOrder newCarOrder = readOrderFromFile(fileName);

			if (newCarOrder == null) {
				return null;
			}

			carOrders.put(newCarOrder.getDate(), newCarOrder);
		}

		return carOrders;
	}

	/**
	 * 파일의 데이터를 Car 객체로 저장
	 *
	 * @param fileName
	 *            읽어올 파일 이름
	 * @return 파일의 Car 객체들 Map
	 */
	public Map<String, Car> readListFromFile(String fileName) {
		BufferedReader input = null;
		String read;
		Map<String, Car> cars = new TreeMap<String, Car>();
		File file = new File(fileName);

		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream(FOLDER_LIST_PATH + "/" + file), "EUC-KR"));
			fileName = fileName.substring(0, fileName.length() - REMOVE_EXTENSION_NUMBER);

			read = input.readLine();

			while (read != null) {
				String fileValue[] = read.split(" ");

				Class<?> getClass = Class.forName(Car.class.getPackage().getName() + "." + fileName);
				Constructor<?> parameter = getClass.getDeclaredConstructor(String.class, CarInfo.class);
				CarInfo newCarInfo = new CarInfo(Double.parseDouble(fileValue[1]));
				Car newCar = (Car) parameter.newInstance(fileValue[0], newCarInfo);
				cars.put(newCar.getNumber(), newCar);

				read = input.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return cars;
	}

	/**
	 * 파일의 데이터를 Order 객체로 저장
	 * 
	 * @param fileName
	 *            읽어올 파일 이름
	 * @return CarOrder 객체
	 */
	public CarOrder readOrderFromFile(String fileName) {
		BufferedReader input = null;
		String read;
		CarOrder newCarOrder = null;
		File file = new File(fileName);

		try {
			input = new BufferedReader(new InputStreamReader(new FileInputStream(FOLDER_ORDER_PATH + "/" + file), "EUC-KR"));
			fileName = fileName.substring(0, fileName.length() - REMOVE_EXTENSION_NUMBER);

			read = input.readLine();

			Class<?> getClass = Class.forName(CarOrder.class.getPackage().getName() + "." + "CarOrder");
			Constructor<?> parameter = getClass.getDeclaredConstructor(String.class);
			newCarOrder = (CarOrder) parameter.newInstance(fileName);
			CarOrderInfo newCarOrderInfo = new CarOrderInfo();

			while (read != null) {
				String fileValue[] = read.split(" ");

				newCarOrderInfo.setDriveDistance(Double.parseDouble(fileValue[1]));
				newCarOrder.addDriveData(fileValue[0], newCarOrderInfo);

				read = input.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return newCarOrder;
	}
}
