package br.com.noberto.upswing.util.codes;

import br.com.noberto.upswing.repositories.ClassRepository;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomCodeForClass implements IRandomCodeStrategy{
    private final ClassRepository classRepository;

    public RandomCodeForClass(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public Integer randomCode() {
        Integer code;
        Random random = new Random(System.currentTimeMillis());
        do {
            code = random.nextInt((9999 - 1000) + 1000);
        } while (classRepository.existsByCode(code));

        return code;
    }
}
