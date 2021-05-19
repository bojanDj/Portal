package portal.paypal;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.api.payments.PaymentHistory;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfig {

	@Value("${paypal.client.id}")
	private String clientId;
	@Value("${paypal.client.secret}")
	private String clientSecret;
	@Value("${paypal.mode}")
	private String mode;

	@Bean
	public Map<String, String> paypalSdkConfig() {
		Map<String, String> configMap = new HashMap<>();
		configMap.put("mode", "sandbox");
//                configMap.put("service.EndPoint",
//      "https://api-m.sandbox.paypal.com/v1/oauth2/token");
		return configMap;
	}

	@Bean
	public OAuthTokenCredential oAuthTokenCredential() {
		return new OAuthTokenCredential("ASi00hxDGpOPqgs82IkAR5d6BpAHHt9Kw4v5wVol3Fqu9sRFtCPcJNhHpvOuQb_YrhJtVAv_h3sghKZF", "EEFasykQFtepNBkwIMaZFzL58lX0BYGglyQu5ZkteOkDn00G3AEzJFoABYX_EDXHBHtL05ICAvm7zL79");
	}

	@Bean
	public APIContext apiContext() throws PayPalRESTException {
		APIContext context = new APIContext("A21AAIisDGUkIxS-6L0zoHcJ0qp2cqFTnVJa3YV6XOySeYVgaJbkdwtoIlo797ULsAdILKgRQTHTIBQ_2CrKkEdj02o9UpZ9w");
                APIContext aPIContext = new APIContext("ASi00hxDGpOPqgs82IkAR5d6BpAHHt9Kw4v5wVol3Fqu9sRFtCPcJNhHpvOuQb_YrhJtVAv_h3sghKZF","EEFasykQFtepNBkwIMaZFzL58lX0BYGglyQu5ZkteOkDn00G3AEzJFoABYX_EDXHBHtL05ICAvm7zL79","sandbox");
		context.setConfigurationMap(paypalSdkConfig());
		return aPIContext;
	}

}
