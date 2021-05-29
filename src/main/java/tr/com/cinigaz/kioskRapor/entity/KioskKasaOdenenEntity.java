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
@Table(name = "kiosk_kasa_odenen")
public class KioskKasaOdenenEntity extends MainEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "seq_kiosk_kasa_odenen", initialValue = 1, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kiosk_kasa_odenen")
    private Long id;
    @Column(name = "kurum_adi")
    private String kurumAdi;
    @Column(name = "islem_tarihi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date islemTarihi;
    @Column(name = "gunun_tarihi")
    @Temporal(TemporalType.DATE)
    private Date gununTarihi;
    @Column(name = "kiosk_id")
    private Integer kioskId;
    @Column(name = "toplam_alinan")
    private Double toplamAlinan;
    @Column(name = "kontrol_eden")
    private String kontroler;
    @Column(name = "teslim_alan")
    private String teslimAlan;
    @Column(name = "onaylayan")
    private String onaylayan;
}