Use BlogApplication;
DROP TABLE IF EXISTS BikeWeather;
CREATE TABLE BikeWeather(BikeWeatherId INT AUTO_INCREMENT, Date TIMESTAMP, BikeCount INT, AirTemperature 
DECIMAL, CONSTRAINT pk_BikeWeather_BikeWeatherId PRIMARY KEY (BikeWeatherId));
