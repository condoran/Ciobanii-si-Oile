package cms.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProposalDTO implements Serializable {
    private Long id;
    private String name;
    private String keywords;
    private String topics;
    private File abstractPaper;
    private File fullPaper;
    private String status;
    private ConferenceDTO conference;
}
