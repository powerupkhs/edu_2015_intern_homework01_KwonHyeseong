/*
 * @(#)FileInputManagerTest.java 2015. 4. 21.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.homework.java.manager;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.ntscorp.homework.java.car.Bus;
import com.ntscorp.homework.java.car.Car;
import com.ntscorp.homework.java.car.CarInfo;
import com.ntscorp.homework.java.order.CarOrder;
import com.ntscorp.homework.java.order.CarOrderInfo;

/**
 * @author hyeseong.kwon@nhn.com
 */
public class FileInputManagerTest {
	private static FileInputManager fileInputManager;
	private File listFile;
	private File orderFile;

	@BeforeClass
	public static void setUpClass() {
		fileInputManager = new FileInputManager();
	}

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();

	/**
	 * 실제 파일 말고 임시파일을 생성
	 * 
	 * @throws IOException
	 */
	@Before
	public void setUp() throws IOException {
		try {
			testFolder.newFolder("list");
			listFile = testFolder.newFile("/list/Bus.txt");
			FileOutputStream fs = new FileOutputStream(listFile);
			Writer writer = new OutputStreamWriter(fs, "EUC-KR");
			writer.append("경기1모1234 10.0\n");
			writer.append("경기2모1234 8.4\n");
			writer.append("경남2요9577 64.7");
			writer.flush();
			writer.close();

			testFolder.newFolder("order");
			orderFile = testFolder.newFile("/order/20150101.txt");
			fs = new FileOutputStream(orderFile);
			writer = new OutputStreamWriter(fs, "EUC-KR");
			writer.append("서울9가3869 5\n");
			writer.append("충남2요6038 10\n");
			writer.append("경기2모1234 21");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 파일의 데이터를 Car 맵으로 저장하는 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void testReadListFromFile() throws Exception {
		// given
		Map<String, Car> cars = new TreeMap<String, Car>();
		cars.put("경기1모1234", new Bus("경기1모1234", new CarInfo(10.0)));
		cars.put("경기2모1234", new Bus("경기2모1234", new CarInfo(8.4)));
		cars.put("경남2요9577", new Bus("경남2요9577", new CarInfo(64.7)));

		// when 
		Map<String, Car> actualResult = fileInputManager.readListFromFile(listFile.getParentFile().getAbsolutePath(), "Bus.txt");

		// then
		assertEquals(cars, actualResult);
	}

	/**
	 * 파일의 데이터를 CarOrder 객체로 저장하는 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void testReadOrderFromFile() throws Exception {
		// given
		Map<String, CarOrderInfo> driveData = new HashMap<String, CarOrderInfo>();
		driveData.put("서울9가3869", new CarOrderInfo(5));
		driveData.put("충남2요6038", new CarOrderInfo(10));
		driveData.put("경기2모1234", new CarOrderInfo(21));

		CarOrder carOrder = new CarOrder("20150101");
		carOrder.setDriveData(driveData);

		// when
		CarOrder actualResult = fileInputManager.readOrderFromFile(orderFile.getParentFile().getAbsolutePath(), "20150101.txt");

		// then
		assertEquals(carOrder, actualResult);
	}

	/**
	 * list 폴더의 모든 파일을 불러와 Car 맵으로 저장하는 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void testReadList() throws Exception {
		// given
		Map<String, Car> cars = new TreeMap<String, Car>();
		cars.put("경기1모1234", new Bus("경기1모1234", new CarInfo(10.0)));
		cars.put("경기2모1234", new Bus("경기2모1234", new CarInfo(8.4)));
		cars.put("경남2요9577", new Bus("경남2요9577", new CarInfo(64.7)));

		// when 
		Map<String, Car> actualResult = fileInputManager.readList(listFile.getParentFile().getAbsolutePath());

		// then
		assertEquals(cars, actualResult);
	}

	/**
	 * order 폴더의 모든 파일을 불러와 CarOrder 맵으로 저장하는 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	public void testReadOrder() throws Exception {
		// given
		Map<String, CarOrder> carOrders = new TreeMap<String, CarOrder>();
		Map<String, CarOrderInfo> driveData = new HashMap<String, CarOrderInfo>();
		driveData.put("서울9가3869", new CarOrderInfo(5));
		driveData.put("충남2요6038", new CarOrderInfo(10));
		driveData.put("경기2모1234", new CarOrderInfo(21));
		CarOrder carOrder = new CarOrder("20150101");
		carOrder.setDriveData(driveData);
		carOrders.put("20150101", carOrder);

		// when
		Map<String, CarOrder> actualResult = fileInputManager.readOrder(orderFile.getParentFile().getAbsolutePath());

		// then
		assertEquals(carOrders, actualResult);
	}
}
