package auto_ckz.common.validators;

import auto_ckz.site.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>  {

    @Autowired
    private AccountRepository repository;

    @Override
    public void initialize(UniqueEmail uniqueEmail) { }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return repository.findOneByEmail(email) == null;
    }
}
