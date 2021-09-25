package pl.sima.systemanalysis.playback.infrastructure.spring;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import pl.sima.systemanalysis.playback.CrmApi;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Qualifier("SpringClientHttp")
@Service
public class SpringCrmApi implements CrmApi {

    @Value("${connect.timeout.ms}")
    private int connectTimeout;

    @Value("${read.timeout.ms}")
    private int readTimeout;

    @Value("${purejava.crm.address:}")
    private String crmURI;

    private RestTemplate restTemplate;

    @PostConstruct
    private void init() {
        restTemplate = new RestTemplate(getClientHttpRequestFactory());
    }

    @Override
    public String getDeviceInfo() {
        return restTemplate.getForObject(crmURI + "/devices/info", String.class);
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        return factory;
    }

}
