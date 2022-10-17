package cl.falabella.mserv.producto.presentation.controller.constant;

public final class Routes {

    private static final String VERSION = "/v1";

    public static class Productos {
        private static final String PREFIX = "/productos";

        public static final String GET_ALL_PRODUCTS     = VERSION + PREFIX;
        public static final String GET_PRODUCT_BY_ID    = VERSION + PREFIX + "/{id}";
        public static final String GET_PRODUCT_BY_SKU   = VERSION + PREFIX + "/sku/{sku}";
        public static final String POST                 = VERSION + PREFIX;
        public static final String PUT                  = VERSION + PREFIX + "/{id}";
        public static final String DELETE               = VERSION + PREFIX + "/{id}";
    }
}