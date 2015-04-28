DROP TABLE IF EXISTS drive_data;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS car_order;

/*자동차 정보 테이블(차량번호, 차량타입, 보정된 연비, 연비)*/
CREATE TABLE car (
	number VARCHAR(10) NOT NULL,
	car_type VARCHAR(15) NOT NULL,
	adjusted_fuel_efficiency DOUBLE NOT NULL,
	fuel_efficiency DOUBLE NOT NULL,
	PRIMARY KEY (number)
);

/*운행지시서 테이블(운행날짜)*/
CREATE TABLE car_order (
	order_date DATE NOT NULL,
	PRIMARY KEY (order_date)
);

/*운행정보 테이블(운행날짜, 차량번호, 운행거리)*/
CREATE TABLE drive_data (
	order_date DATE NOT NULL,
	car_number VARCHAR(10) NOT NULL,
	drive_distance DOUBLE NOT NULL,
	PRIMARY KEY (order_date, car_number),
	CONSTRAINT FK_drive_data_1 FOREIGN KEY (order_date) REFERENCES car_order (order_date) ON DELETE CASCADE,
	CONSTRAINT FK_drive_data_2 FOREIGN KEY (car_number) REFERENCES car (number) ON DELETE CASCADE
);