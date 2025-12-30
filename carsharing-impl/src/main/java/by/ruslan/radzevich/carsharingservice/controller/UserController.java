package by.ruslan.radzevich.carsharingservice.controller;

import by.ruslan.radzevich.carsharingservice.dto.request.AuthRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.request.CreateUserRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateUserResponseDto;
import by.ruslan.radzevich.carsharingservice.service.impl.UserServiceImpl;
import by.ruslan.radzevich.model.entity.Card;
import by.ruslan.radzevich.repository.CardRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "UserController", description = "Контроллер для управления пользователями")
@RequestMapping("/user")
public class UserController {


    private final UserServiceImpl userService;

    private final CardRepository cardRepository;

    @Operation(
            description = "API предназначено для добавления пользователя в базу"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
    })


    @PostMapping("/registration")
    public CreateUserResponseDto create(@RequestBody @Valid CreateUserRequestDto dto) {
        return userService.create(dto);
    }

    @Operation(
            description = "API предназначено для добавления авторизаций пользователя "
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "409", description = "Conflict",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
    })

    @PostMapping("/login")
    public String login(@RequestBody AuthRequestDto authRequestDto) {
        return userService.authenticate(authRequestDto.username(), authRequestDto.password());
    }

//    @PostMapping("/card")
//    public ResponseEntity<Card> addCardFromUser(@RequestBody CreateCardDto createCardDto) {
//        Card cardToCard = createCardMapper.createCardToCard(createCardDto);
//        cardRepository.save(cardToCard);
//        return ResponseEntity.ok(cardToCard);
//    }

    @DeleteMapping("/card/{id}")
    public ResponseEntity<Card> deleteCardById(@PathVariable Long id) {
        cardRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

//    @SneakyThrows
//    @PutMapping("/{id}/driversLicense")
//    public ResponseEntity<User> updateDriversLicense(@RequestBody Long id,
//                                                     @RequestPart("driversLicense") MultipartFile driversLicense) {
//        User user = userRepository.findById(id).orElseThrow();
//        user.setDriversLicense(driversLicense.getBytes());
//        User save = userRepository.save(user);
//        return ResponseEntity.ok(save);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity.BodyBuilder updateEmailById(
//            @RequestBody UpdateEmailDto updateEmailDtoDto,
//            @PathVariable("id") long id) {
//        userService.updateEmailWithCustomQuery(id, String.valueOf(updateEmailDtoDto));
//        return ResponseEntity.ok();
//    }

//    @GetMapping("/all")
//    public ResponseEntity<List<UserView>> findAll() {
//        return ResponseEntity.ok(userRepository.findAllBy());
//    }
}
