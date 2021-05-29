package tr.com.cinigaz.kioskRapor.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@EqualsAndHashCode

@Entity
@Table(name = "kiosk_alinan_para_kasasi")

public class KioskAlinanParaKasasiEntity extends MainEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "seq_kiosk_alinan_para_kasasi", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kiosk_alinan_para_kasasi")
    private Long id;
    @Column(name = "kiosk_kodu", length = 20)
    private String kioskKodu;
    @Column(name = "sozlesme_kod")
    private Long sozlesmeKod;
    @Column(name = "kasa_tarihi")
    @Temporal(TemporalType.DATE)
    private Date kasaTarihi;
    @Column(name = "alim_sirasi")
    private Integer alimSirasi;
    @Column(name = "alim_zamani", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date alimZamani;
    @Column(name = "alinan_banknot")
    private Long alinanBanknot;
}
