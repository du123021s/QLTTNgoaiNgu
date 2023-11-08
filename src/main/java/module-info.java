module qlttnn_4_11 {
    requires javafx.controls;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires org.apache.commons.codec;

    opens view;
    exports view;

}