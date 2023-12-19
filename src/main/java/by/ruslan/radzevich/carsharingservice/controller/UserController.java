package by.ruslan.radzevich.carsharingservice.controller;

import by.ruslan.radzevich.carsharingservice.configuration.JWTTokenProvider;
import by.ruslan.radzevich.carsharingservice.dto.AuthRequestDto;
import by.ruslan.radzevich.carsharingservice.dto.CreateCardDto;
import by.ruslan.radzevich.carsharingservice.dto.CreateUserDto;
import by.ruslan.radzevich.carsharingservice.dto.UpdateEmailDto;
import by.ruslan.radzevich.carsharingservice.mapper.CreateCardMapper;
import by.ruslan.radzevich.carsharingservice.model.Card;
import by.ruslan.radzevich.carsharingservice.model.User;
import by.ruslan.radzevich.carsharingservice.repository.CardRepository;
import by.ruslan.radzevich.carsharingservice.repository.UserRepository;
import by.ruslan.radzevich.carsharingservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    private final JWTTokenProvider tokenProvider;
    private final CreateCardMapper createCardMapper;
    private final CardRepository cardRepository;


    @SneakyThrows
    @PostMapping
    public ResponseEntity<User> create(@RequestPart("driver") CreateUserDto dto,
                                       @RequestPart("driversLicense") MultipartFile driversLicense) {
        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setDriversLicense(driversLicense.getBytes());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        User newUser = userService.create(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequestDto dto) {

        UserDetails userDetails = userService.loadUserByUsername(dto.getUsername());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(dto.getPassword(), userDetails.getPassword())) {
            String token = tokenProvider.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/card")
    public ResponseEntity<Card> addCardFromUser(@RequestBody CreateCardDto createCardDto){
        Card cardToCard = createCardMapper.createCardToCard(createCardDto);
        cardRepository.save(cardToCard);
        return ResponseEntity.ok(cardToCard);
    }
    @DeleteMapping("/card/{id}")
    public ResponseEntity<Card> deleteCardById(@PathVariable Long id) {
        cardRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @SneakyThrows
    @PutMapping("/{id}/driversLicense")
    public ResponseEntity<User> updateDriversLicense(@RequestBody Long id,
                                                     @RequestPart("driversLicense") MultipartFile driversLicense) {
        User user = userRepository.findById(id).orElseThrow();
        user.setDriversLicense(driversLicense.getBytes());
        User save = userRepository.save(user);
        return ResponseEntity.ok(save);
    }

    @PutMapping("/{id}")
    public ResponseEntity.BodyBuilder updateEmailById(
            @RequestBody UpdateEmailDto updateEmailDtoDto,
            @PathVariable("id") long id) {
        userService.updateEmailWithCustomQuery(id, String.valueOf(updateEmailDtoDto));
        return ResponseEntity.ok();
    }
}