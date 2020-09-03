package uk.offtopica.monerorpc.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
public class IntegratedAddressResult {
    /**
     * An integrated address.
     */
    @NonNull
    private String integratedAddress;

    /**
     * The payment id attached to the integrated address. Should not be shown to users.
     */
    @NonNull
    private String paymentId;
}
