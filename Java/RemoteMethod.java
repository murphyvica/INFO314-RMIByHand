import java.io.Serializable;

public class RemoteMethod implements Serializable {
    private String method;
    private Object[] args;

    public RemoteMethod(String name, Object[] args) {
        this.method = name;
        this.args = args;
    }

    public String getName() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }
}
