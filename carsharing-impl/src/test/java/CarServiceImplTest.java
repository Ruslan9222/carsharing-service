import by.ruslan.radzevich.carsharingservice.dto.request.CreateCarRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateCarResponseDto;
import by.ruslan.radzevich.carsharingservice.mapper.CarMapper;
import by.ruslan.radzevich.carsharingservice.service.impl.CarServiceImpl;
import by.ruslan.radzevich.model.entity.Car;
import by.ruslan.radzevich.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    private CreateCarRequestDto requestDto;
    private Car car;
    private CreateCarResponseDto responseDto;

    @BeforeEach
    void setUp() {
        requestDto = CreateCarRequestDto.builder()
            .model("BMW")
            .winCode("1HGCM82633A004352")
            .classCar("sedan")
            .price(java.math.BigDecimal.valueOf(10000))
            .build();

        car = new Car();
        car.setModel("BMW");
        car.setWinCode("1HGCM82633A004352");
        car.setClassCar("sedan");
        car.setPrice(java.math.BigDecimal.valueOf(10000));

        responseDto = CreateCarResponseDto.builder()
            .id(1L)
            .build();
    }

    @Test
    @DisplayName("При уникальном VIN машина сохраняется и возвращается DTO")
    void createCar_success() {
        when(carRepository.existsByWinCode(requestDto.winCode())).thenReturn(false);
        when(carMapper.mapToEntity(requestDto)).thenReturn(car);
        when(carRepository.save(car)).thenReturn(car);
        when(carMapper.mapToResponseDto(car)).thenReturn(responseDto);

        CreateCarResponseDto result = carService.createCar(requestDto);

        assertNotNull(result);
        assertEquals(1L, result.id());
        verify(carRepository).save(car);
    }

    @Test
    @DisplayName("При дубликате VIN выбрасывается IllegalArgumentException и save не вызывается")
    void createCar_duplicateVin_throwsException() {
        when(carRepository.existsByWinCode(requestDto.winCode())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> carService.createCar(requestDto)
        );

        assertEquals("Автомобиль с таким VIN уже существует", exception.getMessage());
        verify(carRepository, never()).save(any(Car.class));
    }
}
