package apps.amaralus.orch.core.common;

public final class BasePath {

    public static final String BASE_URL = "core";
    public static final String PROCESS_URL = "process";
    public static final String INIT_ROUTE = "seda:init-route?concurrentConsumers={{seda.endpointsThreads.init-route}}";
    public static final String HANDLE_ROUTE = "seda:handle-route?concurrentConsumers={{seda.endpointsThreads.handle-route}}";

    private BasePath() {}
}
