package uk.offtopica.monerorpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Used for requests where the parameters are an array and not an object of key-value pairs.
 *
 * @param <T> Type of the element inside the array.
 */
public class MoneroRpcRequestParamsArray<T> extends ArrayList<T> implements MoneroRpcRequestParams {
    public MoneroRpcRequestParamsArray(int initialCapacity) {
        super(initialCapacity);
    }

    public MoneroRpcRequestParamsArray() {
    }

    public MoneroRpcRequestParamsArray(Collection<? extends T> c) {
        super(c);
    }

    @SafeVarargs
    public static <U> MoneroRpcRequestParamsArray<U> of(U... ts) {
        return new MoneroRpcRequestParamsArray<>(Arrays.asList(ts));
    }
}
