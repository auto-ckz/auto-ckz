package auto_ckz.domain.client;

import auto_ckz.domain.abstracts.AbstractPersonEntity;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Client extends AbstractPersonEntity { }
