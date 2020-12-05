package com.rodmccutcheon.ingestion;

import com.rodmccutcheon.ingestion.BaseContractTestMessaging;
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
	public void validate_shouldParseInputPacketAndSendDaliStatusMessage() throws Exception {
		// given:
			ContractVerifierMessage inputMessage = contractVerifierMessaging.create(
					"{\"opCode\":1,\"gatewayAddress\":\"00000000-0000-0000-0000-000000000001\",\"packet\":\"BALLAST_FAILURE;POWER_FAILURE\"}"
						, headers()
			);

		// when:
			contractVerifierMessaging.send(inputMessage, "input");

		// then:
			ContractVerifierMessage response = contractVerifierMessaging.receive("dali_status_notifications");
			assertThat(response).isNotNull();

		// and:
			assertThat(response.getHeader("contentType")).isNotNull();
			assertThat(response.getHeader("contentType").toString()).isEqualTo("application/json");

		// and:
			DocumentContext parsedJson = JsonPath.parse(contractVerifierObjectMapper.writeValueAsString(response.getPayload()));
			assertThatJson(parsedJson).field("['gateway']").isEqualTo("00000000-0000-0000-0000-000000000001");
			assertThatJson(parsedJson).array("['values']").array().arrayField().isEqualTo("BALLAST_FAILURE").value();
			assertThatJson(parsedJson).array("['values']").array().arrayField().isEqualTo("POWER_FAILURE").value();
	}

}
