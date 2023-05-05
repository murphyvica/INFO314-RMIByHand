import java.io.Serializable;

public class RemoteResult implements Serializable {
    private String type;
    private Object[] args;

    public RemoteResult(String type, Object[] args) {
        this.args = args;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Object[] getArgs() {
        return args;
    }
}
