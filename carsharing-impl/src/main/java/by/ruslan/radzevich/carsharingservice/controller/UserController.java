package by.ruslan.radzevich.carsharingservice.controller;

import by.ruslan.radzevich.carsharingservice.configuration.JWTTokenProvider;
import by.ruslan.radzevich.carsharingservice.dto.AuthRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.request.CreateUserRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.response.CreateUserResponseDto;
import by.ruslan.radzevich.carsharingservice.service.impl.UserServiceImpl;
import by.ruslan.radzevich.model.entity.Card;
import by.ruslan.radzevich.repository.CardRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {


    private final UserServiceImpl userService;
    private final JWTTokenProvider tokenProvider;
    private final CardRepository cardRepository;


    @PostMapping("/registration")
    public CreateUserResponseDto create(@RequestBody @Valid CreateUserRequestDto dto) {
        return userService.create(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequestDto dto) {

        UserDetails userDetails = userService.loadUserByUsername(dto.getUsername());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(dto.getPassword(), userDetails.getPassword())) {
            String token = tokenProvider.generateToken(userDetails.getUsername(),
                    userDetails.getAuthorities());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
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
