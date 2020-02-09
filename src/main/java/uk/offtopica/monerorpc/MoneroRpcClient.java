package uk.offtopica.monerorpc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Slf4j
@RequiredArgsConstructor
public abstract class MoneroRpcClient {
    protected static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Getter(AccessLevel.PROTECTED)
    @NonNull
    private final URI address;

    protected <T extends MoneroRpcResponseResult> CompletableFuture<MoneroRpcResponse<T>>
    request(MoneroRpcRequest<?> request, TypeReference<MoneroRpcResponse<T>> resultType) throws IOException {
        log.trace("Calling method {}", request.getMethod());

        HttpRequest httpRequest = HttpRequest.newBuilder(address)
                .POST(HttpRequest.BodyPublishers.ofByteArray(OBJECT_MAPPER.writeValueAsBytes(request)))
                .header("Accept", "application/json")
                .build();

        return HttpClient.newHttpClient()
                .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofByteArray())
                .thenApply(HttpResponse::body)
                .thenApply(body -> {
                    try {
                        return OBJECT_MAPPER.readValue(body, resultType);
                    } catch (IOException e) {
                        log.error("Failed to decode JSON or read response body", e);
                        throw new CompletionException(e);
                    }
                })
                .thenApply(response -> {
                    if (response.getError() != null) {
                        throw new CompletionException(new MoneroRpcException(response.getError().getMessage()));
                    }
                    return response;
                });
    }
}
