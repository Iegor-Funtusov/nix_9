package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import ua.com.alevel.config.security.SecurityService;
import ua.com.alevel.facade.AuthValidatorFacade;
import ua.com.alevel.web.dto.request.register.AuthDto;

@Service
public class AuthValidatorFacadeImpl implements AuthValidatorFacade {

    private final SecurityService securityService;

    public AuthValidatorFacadeImpl(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AuthDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuthDto dto = (AuthDto) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (dto.getEmail().length() < 6 || dto.getEmail().length() > 32) {
            errors.rejectValue("email", "Size.authForm.email");
        }

        if (securityService.existsByEmail(dto.getEmail())) {
            errors.rejectValue("email", "Duplicate.authForm.email");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (dto.getPassword().length() < 8 || dto.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.authForm.password");
        }
        if (!dto.getPasswordConfirm().equals(dto.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.authForm.passwordConfirm");
        }
    }
}
