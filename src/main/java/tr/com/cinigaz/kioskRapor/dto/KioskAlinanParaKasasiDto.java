package tr.com.cinigaz.kioskRapor.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@EqualsAndHashCode

public class KioskAlinanParaKasasiDto {
    private Long tesisatKod;
    private Long alinanBanknot;
}
