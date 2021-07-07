package pl.sima.systemanalysis.playback;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/session")
public class VideoSessionController {

    private final CrmApi crmApi;

    @PostMapping(value = "/start")
    public ResponseEntity<String> getInfo() {
        String info = crmApi.getDeviceInfo();
        return ResponseEntity.ok(info);
    }
}
