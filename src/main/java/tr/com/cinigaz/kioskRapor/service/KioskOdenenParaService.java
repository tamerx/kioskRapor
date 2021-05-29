package tr.com.cinigaz.kioskRapor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import tr.com.cinigaz.kioskRapor.dto.KioskKasaOdenenResponse;
import tr.com.cinigaz.kioskRapor.dto.KioskKasaVerilenResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;


@Service
public class KioskOdenenParaService {


    public List<KioskKasaOdenenResponse> getData(String kioskID, Date islemTarihi) throws IOException {
        URL url = new URL("http://localhost:9510/kiosk/odeme/value/" + kioskID + "/" + islemTarihi);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        int responseCode = con.getResponseCode();


        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            String response = new String();
            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }
            in.close();

            final ObjectMapper objectMapper = new ObjectMapper();

            try {

                List<KioskKasaOdenenResponse> kioskKasaOdenenResponseList = objectMapper.readValue(response, new TypeReference<List<KioskKasaOdenenResponse>>() {
                });
                return kioskKasaOdenenResponseList;
            } catch (JsonMappingException e) {

                e.printStackTrace();

            } catch (JsonProcessingException e) {

                e.printStackTrace();
            }
        } else {
        }

        return null;
    }




}
