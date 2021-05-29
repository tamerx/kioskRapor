package tr.com.cinigaz.kioskRapor.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@EqualsAndHashCode

public class KioskKasaOdenenDto {

    private String teslimAlan;
    private String teslimEden;
    private String kurumAdi;
    private Date islemTarihi;
    private String kioskKodu;
    private Double toplamAlinan;

}
