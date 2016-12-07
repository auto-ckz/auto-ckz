package auto_ckz.common.validators;

import auto_ckz.domain.abstracts.AbstractPersonEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AbstractPersonEntity.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
