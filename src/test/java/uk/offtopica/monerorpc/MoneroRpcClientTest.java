package uk.offtopica.monerorpc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.jupiter.MockServerExtension;
import org.mockserver.matchers.MatchType;

import java.net.URI;
import java.util.Map;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

@ExtendWith(MockServerExtension.class)
public abstract class MoneroRpcClientTest<T extends MoneroRpcClient> {
    protected T client;
    protected MockServerClient http;
    private final ObjectMapper objectMapper;

    public MoneroRpcClientTest() {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void setupMoneroRpcClientTest(MockServerClient http) {
        this.http = http;
        http.reset();
        client = createClient(URI.create(String.format("http://localhost:%d/json_rpc", http.getPort())));
    }

    protected void setupMockEndpoint(String method, Object expectedRequestParams, Object response) {
        http.when(
                request()
                        .withMethod("POST")
                        .withPath("/json_rpc")
                        .withBody(json(
                                Map.of(
                                        "method", method,
                                        "params", expectedRequestParams
                                ),
                                MatchType.ONLY_MATCHING_FIELDS
                        ))
        ).respond(
                response()
                        .withBody(responseJson(response))
        );
    }

    protected void setupMockEndpointSuccess(String method, Object expectedRequestParams, Object result) {
        setupMockEndpoint(
                method,
                expectedRequestParams,
                Map.of(
                        "id", 0,
                        "jsonrpc", "2.0",
                        "result", result
                )
        );
    }

    protected void setupMockEndpointError(String method, Object expectedRequestParams, int errorCode,
                                          String errorMessage) {
        setupMockEndpoint(
                method,
                expectedRequestParams,
                Map.of(
                        "id", 0,
                        "jsonrpc", "2.0",
                        "error", Map.of(
                                "code", errorCode,
                                "message", errorMessage
                        )
                )
        );
    }

    private String responseJson(Object object) {
        // The MockClient json() method removes empty strings and objects :(
        try {
            return objectMapper.writer().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract T createClient(URI uri);
}
