package cms.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Section implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private CMSUser sectionChair;

    @OneToMany
    private List<CMSUser> speakers;

    @ManyToOne
    private Conference conference;
}
