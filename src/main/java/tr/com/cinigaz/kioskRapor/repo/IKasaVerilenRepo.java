package tr.com.cinigaz.kioskRapor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import tr.com.cinigaz.kioskRapor.entity.KasaVerilenDetay;
import tr.com.cinigaz.kioskRapor.entity.KioskAlinanParaKasasiEntity;

import java.util.List;

public interface IKasaVerilenRepo extends JpaRepository<KasaVerilenDetay, Long> {


    String SQL = "Select * From kiosk_alinan_para_kasasi where" +
            " not exists (select 1 from kiosk_verilen where kiosk_verilen.alinan_id = kiosk_alinan_para_kasasi.id)  ";

    @Query(value = SQL, nativeQuery = true)
    List<KioskAlinanParaKasasiEntity> getList(@Param("kioskKodu") String kioskKodu);

}
