import java.util.HashMap;

class CustomModel {
    HashMap<String, String> customCost = new HashMap<String, String>();
    float cost;

    public CustomModel(float _cost, String _risk, String _inconvenience) {
        cost = _cost;
        customCost.put("Risk", _risk);
        customCost.put("Inconvenience", _inconvenience);
    }
}
