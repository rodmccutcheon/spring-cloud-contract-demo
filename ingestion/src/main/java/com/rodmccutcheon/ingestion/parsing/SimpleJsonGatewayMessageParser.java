package com.rodmccutcheon.ingestion.parsing;

import com.rodmccutcheon.ingestion.data.DaliStatusNotifications;
import com.rodmccutcheon.ingestion.data.GatewayNotification;
import com.rodmccutcheon.ingestion.data.InputPacket;
import com.rodmccutcheon.ingestion.data.SceneNotification;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Log4j2
public class SimpleJsonGatewayMessageParser implements Function<InputPacket, Collection<GatewayNotification>> {

    private static final int DALI_OP_CODE = 1;
    private static final int SCENE_OP_CODE = 2;

    @Override
    public Collection<GatewayNotification> apply(InputPacket inputPacket) {
        if (inputPacket.getOpCode() == DALI_OP_CODE) {
            DaliStatusNotifications daliStatusNotifications = new DaliStatusNotifications(inputPacket.getGatewayAddress());
            Set<DaliStatusNotifications.DaliStatus> daliStatuses = Arrays.stream(inputPacket.getPacket().split(";")).map(DaliStatusNotifications.DaliStatus::valueOf).collect(Collectors.toUnmodifiableSet());
            daliStatusNotifications.setValues(daliStatuses);
            return Collections.singleton(daliStatusNotifications);
        } else if (inputPacket.getOpCode() == SCENE_OP_CODE) {
            return Collections.singleton(new SceneNotification(inputPacket.getGatewayAddress(), inputPacket.getPacket()));
        } else {
            log.warn("Was not able to parse input from the source stream, invalid op code: {}", inputPacket.getOpCode());
            return null;
        }
    }

}
