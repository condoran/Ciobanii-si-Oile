package cms.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Conference implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Date startDate;
    private Date endDate;
    private String callForPapers;
    private Date abstractPaperDeadline;
    private Date fullPaperDeadline;
    private Date biddingDeadline;
    private String password;

    @OneToOne
    private CMSUser chair;

    @OneToOne
    private CMSUser firstCoChair;

    @OneToOne
    private CMSUser secondCoChair;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "conference")
    private List<Section> sections = new ArrayList<>();
}
