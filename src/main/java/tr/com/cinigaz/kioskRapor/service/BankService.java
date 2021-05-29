package tr.com.cinigaz.kioskRapor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import tr.com.cinigaz.kioskRapor.dto.BankResponse;
import tr.com.cinigaz.kioskRapor.dto.KioskAlinanParaKasasiResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class BankService {


    private final String URL = "http://localhost:9510/kiosk/verilen/";


    public List<BankResponse> getBankList() throws IOException {
        java.net.URL obj = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
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

                List<BankResponse> bankResponseList = objectMapper.readValue(response, new TypeReference<List<BankResponse>>() {
                });
                return bankResponseList;
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
