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

public class KioskAlinanParaKasasiResponse {



    @JsonProperty("id")
    private int id;

    @JsonProperty("alimSirasi")
    private int alimSirasi;

    @JsonProperty("sozlesmeKod")
    private String sozlesmeKod;

    @JsonProperty("kioskKodu")
    private String kioskKodu;

    @JsonProperty("kasaTarihi")
    private Date kasaTarihi;

    @JsonProperty("alimZamani")
    private Date alimZamani;

    @JsonProperty("alinanBanknot")
    private Long alinanBanknot;


    @JsonProperty("createdBy")
    private String createdBy;

    @JsonProperty("createdAt")
    private Date createdAt;

    @JsonProperty("updatedBy")
    private String updatedBy;

    @JsonProperty("updatedAt")
    private Date updatedAt;

//
//    {
//        "createdBy":null, "createdAt":null, "updatedBy":null, "updatedAt":null, "id":1, "kioskKodu":
//        "MKI01", "sozlesmeKod":30043664, "kasaTarihi":"2021-02-11", "alimSirasi":1, "alimZamani":
//        "2021-02-11T08:52:25.546+0000", "alinanBanknot":50
//    }


}
