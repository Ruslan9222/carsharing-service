package by.ruslan.radzevich.carsharingservice.validation;

import by.ruslan.radzevich.carsharingservice.anotations.PasswordMatchers;
import by.ruslan.radzevich.carsharingservice.dto.CreateUserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchersValidator implements ConstraintValidator<PasswordMatchers, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        if (obj instanceof CreateUserDto userSignupRequest) {
            String password = userSignupRequest.getPassword();
            String confirmPassword = userSignupRequest.getConfirmPassword();
            return password != null && password.equals(confirmPassword);
        }
        return false;
    }
}
