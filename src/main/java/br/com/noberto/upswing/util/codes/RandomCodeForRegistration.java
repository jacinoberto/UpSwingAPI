package br.com.noberto.upswing.util.codes;

import br.com.noberto.upswing.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomCodeForRegistration implements IRandomCodeStrategy{
    private final RegistrationRepository registrationRepository;

    @Autowired
    public RandomCodeForRegistration(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public Integer randomCode() {
        Integer registration;
        Random random = new Random(System.currentTimeMillis());
        do {
            registration = random.nextInt((9999 - 1000) + 1000);
        } while (registrationRepository.existsByRegistration(registration));

        return registration;
    }
}
