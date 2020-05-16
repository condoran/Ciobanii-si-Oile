package domain;

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
public class Proposal implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String keywords;
    private String topics;

    @ManyToMany
    private List<CMSUser> authors;
}