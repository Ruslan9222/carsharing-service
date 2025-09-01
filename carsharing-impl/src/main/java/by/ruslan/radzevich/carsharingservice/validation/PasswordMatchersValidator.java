package by.ruslan.radzevich.carsharingservice.validation;

import by.ruslan.radzevich.carsharingservice.anotations.PasswordMatchers;
import by.ruslan.radzevich.carsharingservice.dto.request.CreateUserRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PasswordMatchersValidator implements ConstraintValidator<PasswordMatchers, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        if (obj instanceof CreateUserRequestDto userSignupRequest) {
            String password = userSignupRequest.password();
            String confirmPassword = userSignupRequest.confirmPassword();
            return password != null && password.equals(confirmPassword);
        }
        return false;
    }
}
