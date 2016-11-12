package auto_ckz.domain.client;

import auto_ckz.domain.abstracts.AbstractPersonEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

@Entity
@Data
public class Client extends AbstractPersonEntity { }
