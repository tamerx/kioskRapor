package tr.com.cinigaz.kioskRapor.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "kiosk_verilen")
public class KasaVerilenDetay {


    @Id
    @SequenceGenerator(name = "seq_kiosk_verilen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kiosk_verilen")
    private Long id;

    @Column(name = "alinan_id")
    private Long alinanId;
    @Column(name = "odenen_id")
    private Long odenenId;

}
