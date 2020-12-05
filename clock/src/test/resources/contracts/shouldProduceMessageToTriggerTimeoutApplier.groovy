package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    label("timeout-applier-clock-supplier")
    input {
        triggeredBy("timeoutApplierClockSupplier()")
    }
    outputMessage {
        sentTo("clock_timeout_applier")
        body([
                [
                        key: "TIMEOUT_APPLIER",
                        buildingId: 1,
                        startTime: "2020-12-03T05:43:07.824713Z",
                        endTime: null
                ],
                [
                        key: "TIMEOUT_APPLIER",
                        buildingId: 2,
                        startTime: "2020-12-03T05:43:07.824713Z",
                        endTime: null
                ]
        ])
    }
}