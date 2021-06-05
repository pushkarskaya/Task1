import org.aeonbits.owner.Config;

public interface ServerConfig extends Config {
    @Key("server.port")
    int port();

    @Key("server.hostname")
    String hostname();

    @Key("server.maxThreads")
    @DefaultValue("42")
    int maxThreads();
}
