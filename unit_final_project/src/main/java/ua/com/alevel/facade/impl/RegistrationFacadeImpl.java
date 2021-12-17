package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.RegistrationFacade;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.service.PersonalCrudService;
import ua.com.alevel.web.dto.request.register.AuthDto;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final PersonalCrudService personalService;

    public RegistrationFacadeImpl(PersonalCrudService personalService) {
        this.personalService = personalService;
    }

    @Override
    public void registration(AuthDto dto) {
        Personal personal = new Personal();
        personal.setEmail(dto.getEmail());
        personal.setPassword(dto.getPassword());
        personalService.create(personal);
    }
}
