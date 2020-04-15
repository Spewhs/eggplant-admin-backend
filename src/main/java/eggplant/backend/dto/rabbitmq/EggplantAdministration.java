package eggplant.backend.dto.rabbitmq;

public class EggplantAdministration {

    private String actionType;

    private Object payload;

    public EggplantAdministration(
            String actionType,
            Object payload
    ) {
        this.actionType = actionType;
        this.payload = payload;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
