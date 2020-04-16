package domain;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class User extends BaseEntity<Long>{
    private String userName;
    private String userPassword;
    private String name;
    private String affiliation;
    private String emailAddress;
    private String personalWebsite;
    private boolean permission;
}
