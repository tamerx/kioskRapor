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
public class KioskKasaOdenenResponse {


    @JsonProperty("id")
    private int id;

    @JsonProperty("kurumAdi")
    private String kurumAdi;

    @JsonProperty("islemTarihi")
    private Date islemTarihi;

    @JsonProperty("gununTarihi")
    private Date gununTarihi;

    @JsonProperty("kioskKodu")
    private String kioskKodu;

    @JsonProperty("toplamAlinan")
    private Double toplamAlinan;

    @JsonProperty("kontroler")
    private String kontroler;

    @JsonProperty("teslimAlan")
    private String teslimAlan;

    @JsonProperty("teslimEden")
    private String teslimEden;

    @JsonProperty("createdBy")
    private String createdBy;

    @JsonProperty("createdAt")
    private Date createdAt;

    @JsonProperty("updatedBy")
    private String updatedBy;

    @JsonProperty("updatedAt")
    private Date updatedAt;
}