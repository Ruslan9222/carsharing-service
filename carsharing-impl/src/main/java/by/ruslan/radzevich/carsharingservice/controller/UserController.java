package by.ruslan.radzevich.carsharingservice.controller;

import by.ruslan.radzevich.carsharingservice.configuration.JWTTokenProvider;
import by.ruslan.radzevich.carsharingservice.dto.AuthRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.CreateCardDto;
import by.ruslan.radzevich.carsharingservice.dto.request.CreateUserRequestDto;
import by.ruslan.radzevich.carsharingservice.mapper.UserMapper;
import by.ruslan.radzevich.carsharingservice.service.UserService;
import by.ruslan.radzevich.model.entity.Card;
import by.ruslan.radzevich.model.entity.User;
import by.ruslan.radzevich.repository.CardRepository;
import by.ruslan.radzevich.repository.UserRepository;
import by.ruslan.radzevich.view.UserView;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final JWTTokenProvider tokenProvider;
//    private final CreateCardMapper createCardMapper;
    private final CardRepository cardRepository;
    private final UserMapper userMapper;


    @PostMapping
    public ResponseEntity<User> registration(@RequestBody @Valid CreateUserRequestDto dto) {
        User userDtoToUser = userMapper.createUserDtoToUser(dto);
        User user = userService.create(userDtoToUser);
        return ResponseEntity.ok(user);
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

    @GetMapping("/all")
    public ResponseEntity<List<UserView>> findAll() {
        return ResponseEntity.ok(userRepository.findAllBy());
    }
}
