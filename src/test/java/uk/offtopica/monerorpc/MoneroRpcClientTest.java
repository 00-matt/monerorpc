package uk.offtopica.monerorpc;

import com.pgssoft.httpclient.HttpClientMock;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Field;
import java.net.URI;

public abstract class MoneroRpcClientTest<T extends MoneroRpcClient> {
    protected final URI uri;
    protected T client;
    protected HttpClientMock http;

    public MoneroRpcClientTest() {
        uri = URI.create("http://localhost:18081/json_rpc");
    }

    @BeforeEach
    void setupMoneroRpcClientTest() throws Exception {
        client = createClient(uri);
        http = new HttpClientMock();

        final Field httpClientField = MoneroRpcClient.class.getDeclaredField("httpClient");
        httpClientField.setAccessible(true);
        httpClientField.set(client, http);
    }

    protected abstract T createClient(URI uri);
}
