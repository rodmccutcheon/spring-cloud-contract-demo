package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    label("dali-status")
    input {
        messageFrom("input")
        messageBody(
                opCode: 1,
                gatewayAddress: "00000000-0000-0000-0000-000000000001",
                packet: "BALLAST_FAILURE;POWER_FAILURE"
        )
    }
    outputMessage {
        sentTo("dali_status_notifications")
        body(
                gateway: "00000000-0000-0000-0000-000000000001",
                values: [
                        [
                               "BALLAST_FAILURE"
                        ],
                        [
                                "POWER_FAILURE"
                        ]
                ]
        )
        headers {
            header("contentType", applicationJson())
        }
    }
}