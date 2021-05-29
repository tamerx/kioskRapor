package tr.com.cinigaz.kioskRapor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@EqualsAndHashCode

public class BankResponse {


    @JsonProperty("id")
    private int id;

    @JsonProperty("bankaAdi")
    private String bankaAdi;

    @JsonProperty("bankaAciklama")
    private String bankaAciklama;

    @JsonProperty("createdBy")
    private String createdBy;

    @JsonProperty("createdAt")
    private Date createdAt;

    @JsonProperty("updatedBy")
    private String updatedBy;

    @JsonProperty("updatedAt")
    private Date updatedAt;

}
