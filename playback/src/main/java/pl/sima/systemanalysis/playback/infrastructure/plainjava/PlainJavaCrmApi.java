package pl.sima.systemanalysis.playback.infrastructure.plainjava;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import pl.sima.systemanalysis.playback.CrmApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlainJavaCrmApi implements CrmApi {

    @Value("${purejava.crm.address:}")
    private String crmURI;

    @SneakyThrows
    @Override
    public String getDeviceInfo() {
        URL url = new URL(crmURI + "/devices/info");
        HttpURLConnection c = (HttpURLConnection) url.openConnection();
        c.setRequestMethod("GET");
        c.setDoOutput(true);
        c.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        c.disconnect();
        return content.toString();
    }
}
