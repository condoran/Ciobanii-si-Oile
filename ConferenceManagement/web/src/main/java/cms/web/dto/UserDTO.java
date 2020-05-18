package cms.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO implements Serializable {
    private Long id;
    private String username;
    private String name;
    private String emailAddress;
    private String affiliation;
    private String password;
    private String personalWebsite;
    private boolean isChair;
    private boolean isCoChair;
    private boolean isSCMember;
}
