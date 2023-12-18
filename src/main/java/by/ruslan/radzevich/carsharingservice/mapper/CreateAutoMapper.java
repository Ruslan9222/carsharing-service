package by.ruslan.radzevich.carsharingservice.mapper;

import by.ruslan.radzevich.carsharingservice.dto.NewCarDto;
import by.ruslan.radzevich.carsharingservice.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CreateAutoMapper {
    public Car newAutoDtoToAuto(NewCarDto newCarDto) {
        Car car = new Car();
        car.setModel(newCarDto.getModel());
        car.setWinCode(newCarDto.getWinCode());
        car.setClassCar(newCarDto.getClassCar());
        return car;
    }
}