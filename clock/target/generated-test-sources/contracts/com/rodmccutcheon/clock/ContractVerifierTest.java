package com.rodmccutcheon.clock;

import com.rodmccutcheon.clock.BaseContractTestMessaging;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import javax.inject.Inject;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;

import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.*;
import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.springframework.cloud.contract.verifier.messaging.util.ContractVerifierMessagingUtil.headers;
import static org.springframework.cloud.contract.verifier.util.ContractVerifierUtil.fileToBytes;

@SuppressWarnings("rawtypes")
public class ContractVerifierTest extends BaseContractTestMessaging {
	@Inject ContractVerifierMessaging contractVerifierMessaging;
	@Inject ContractVerifierObjectMapper contractVerifierObjectMapper;

	@Test
	public void validate_shouldProduceMessageToTriggerTimeoutApplier() throws Exception {
		// when:
			timeoutApplierClockSupplier();

		// then:
			ContractVerifierMessage response = contractVerifierMessaging.receive("clock_timeout_applier");
			assertThat(response).isNotNull();

		// and:
			DocumentContext parsedJson = JsonPath.parse(contractVerifierObjectMapper.writeValueAsString(response.getPayload()));
			assertThatJson(parsedJson).array().contains("['key']").isEqualTo("TIMEOUT_APPLIER");
			assertThatJson(parsedJson).array().contains("['buildingId']").isEqualTo(1);
			assertThatJson(parsedJson).array().contains("['startTime']").isEqualTo("2020-12-03T05:43:07.824713Z");
			assertThatJson(parsedJson).array().contains("['endTime']").isNull();
			assertThatJson(parsedJson).array().contains("['buildingId']").isEqualTo(2);
	}

}
