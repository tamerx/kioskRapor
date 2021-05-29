package tr.com.cinigaz.kioskRapor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class KioskKasaVerilenResponse {


    @JsonProperty("id")
    private Long id;

    @JsonProperty("alinanId")
    private Long alinanId;


    @JsonProperty("odenenId")
    private Long odenenId;


}
