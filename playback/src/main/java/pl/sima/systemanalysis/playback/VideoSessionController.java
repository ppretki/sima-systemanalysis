package pl.sima.systemanalysis.playback;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/session")
public class VideoSessionController {

    private final CrmApi apacheClient;

    private final CrmApi javaClient;

    private final CrmApi springClient;

    public VideoSessionController(@Qualifier("ApacheHttpClient") CrmApi apacheClient,
            @Qualifier("PlainJavaClient") CrmApi javaClient,
            @Qualifier("SpringClientHttp") CrmApi springClient) {
        this.apacheClient = apacheClient;
        this.javaClient = javaClient;
        this.springClient = springClient;
    }

    @PostMapping(value = "/apache/start")
    public ResponseEntity<String> getInfoApache() {
        String info = apacheClient.getDeviceInfo();
        return ResponseEntity.ok(info);
    }

    @PostMapping(value = "/java/start")
    public ResponseEntity<String> getInfoJava() {
        String info = javaClient.getDeviceInfo();
        return ResponseEntity.ok(info);
    }

    @PostMapping(value = "/spring/start")
    public ResponseEntity<String> getInfoSpring() {
        String info = springClient.getDeviceInfo();
        return ResponseEntity.ok(info);
    }

}
