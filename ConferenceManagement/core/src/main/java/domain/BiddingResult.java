package domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BiddingResult implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Proposal proposal;

    @ManyToOne
    private CMSUser CMSUser;

    private boolean accepted;

}
