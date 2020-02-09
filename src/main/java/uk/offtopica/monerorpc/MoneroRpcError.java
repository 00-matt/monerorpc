package uk.offtopica.monerorpc;

import lombok.Data;

@Data
public class MoneroRpcError {
    private Integer code;
    private String message;
}
