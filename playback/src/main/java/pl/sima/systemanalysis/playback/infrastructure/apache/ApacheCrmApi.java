package pl.sima.systemanalysis.playback.infrastructure.apache;

import javax.annotation.PostConstruct;

import lombok.val;
import pl.sima.systemanalysis.playback.CrmApi;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Qualifier("ApacheHttpClient")
@Service
public class ApacheCrmApi implements CrmApi {

    @Value("${connect.timeout.ms}")
    private int connectTimeout;

    @Value("${read.timeout.ms}")
    private int readTimeout;

    @Value("${max.connections.total}")
    private int maxTotal;

    @Value("${max.connections.per.route}")
    private int maxConnectionPerRoute;

    @Value("${connection.request.timeout.ms}")
    private int connectionRequestTimeout;

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

        val connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(maxConnectionPerRoute);

        val httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(connectionManager);
        val client = httpClientBuilder.build();

        val requestFactory = new HttpComponentsClientHttpRequestFactory(client);
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        requestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
        return requestFactory;
    }
}
