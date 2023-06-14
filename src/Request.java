import java.io.Serializable;

public class Request<T extends Action> implements Serializable {
    private T action;

    public Request(T action) {
        this.action = action;
    }

    public T getAction() {
        return action;
    }

    @Override
    public String toString() {
        return action.toString();
    }
}
