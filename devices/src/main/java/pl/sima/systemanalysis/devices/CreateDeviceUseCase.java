package pl.sima.systemanalysis.devices;

import java.util.UUID;

import pl.sima.systemanalysis.common.Workload;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.stereotype.Service;

@Service
public class CreateDeviceUseCase {

    @NewSpan("createDevice")
    public UUID createDevice(@SpanTag(key = "active_invocations") Integer numberOfActive, @SpanTag(key = "use_cpu") float useCpu) {
        Workload.useCpu(useCpu);
        return UUID.randomUUID();
    }

}
