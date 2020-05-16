package domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CMSUser implements Serializable {
    @Id
    @Generated
    private Long id;

    private String username;
    private String password;
    private String name;
    private String emailAddress;
    private String affiliation;
    private String personalWebsite;
    private boolean isChair;
    private boolean isCoChair;
    private boolean isAuthor;
    private boolean isPCMember;
    private boolean isSCMember;
    private boolean isSectionChair;

    @ManyToMany
    private List<Conference> conferences;


}
