
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RestTemplate {
    private final String externalApiBaseUrl;  // Например, "https://api.example.com"

    private final org.springframework.web.client.RestTemplate restTemplate;

    public RestTemplate(@Value("${external.api.base-url}") String externalApiBaseUrl,
                        org.springframework.web.client.RestTemplate restTemplate) {
        this.externalApiBaseUrl = externalApiBaseUrl;
        this.restTemplate = restTemplate;
    }

    public String fetchDataFromExternalApi() {
        String apiUrl = externalApiBaseUrl + "/some-endpoint";
        return restTemplate.getForObject(apiUrl, String.class);
    }

    // Другие методы для различных типов запросов (POST, PUT, DELETE и т. д.)
}
