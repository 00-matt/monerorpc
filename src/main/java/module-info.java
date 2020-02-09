module uk.offtopica.monerorpc {
    requires static lombok;

    requires java.net.http;

    requires com.fasterxml.jackson.databind;
    requires org.slf4j;

    exports uk.offtopica.monerorpc;
    exports uk.offtopica.monerorpc.daemon;
    exports uk.offtopica.monerorpc.wallet;

    opens uk.offtopica.monerorpc to com.fasterxml.jackson.databind;
    opens uk.offtopica.monerorpc.daemon to com.fasterxml.jackson.databind;
    opens uk.offtopica.monerorpc.wallet to com.fasterxml.jackson.databind;
}
