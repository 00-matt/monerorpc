open module uk.offtopica.monerorpc {
    exports uk.offtopica.monerorpc;
    exports uk.offtopica.monerorpc.daemon;
    exports uk.offtopica.monerorpc.wallet;

    requires static lombok;

    requires java.net.http;

    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j;
}
