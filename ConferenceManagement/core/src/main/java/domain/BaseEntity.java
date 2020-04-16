package domain;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class BaseEntity<ID extends Serializable> implements Serializable {
    @Id
    @GeneratedValue
    private ID id;

}
