package webservices;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class ApplicationMain extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(uniteEnsrestapi.class);
        classes.add(modulBesinessApi.class);
        return classes;
    }
}

