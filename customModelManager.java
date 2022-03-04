import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class customModelManager {
    List<String> getCustomArgs(String[] args){
        List<String> customArgs = new ArrayList<>();

        for (int i = 3; i < args.length; i++) {
            customArgs.add(args[i]);
        }

        return customArgs;
    }

    float getValueAdjustment(String value){
        switch (value){
            case "low":
                return .1f;
            case "medium":
                return .3f;
            case "high":
                return 1;
            case "none":
                return 0;
            default:
                return 0;
        }
    }

    void setCustomValuesOnModel(CustomModel customModel, List<String> args) throws IllegalArgumentException {
        for (int i = 0; i < args.size(); i++) {
            String[] value = args.get(i).split(":");
            if (customModel.customCost.containsKey(value[0])) {
                if (isValueOkay(value[1])){
                    customModel.customCost.put(value[0], value[1]);
                }
                else {
                    throw new IllegalArgumentException("Not a valid custom value");
                }
            }
            else {
                throw new IllegalArgumentException("Not a valid custom cost");
            }
        }
    }

    boolean isValueOkay(String value){
        switch (value){
            case "low":
            case "medium":
            case "high":
            case "none":
                return true;
            default:
                return false;
        }
    }

    CustomModel getModel(HashMap<String, CustomModel> timeFactorModels, String model){
        if (timeFactorModels.containsKey(model)){
            return timeFactorModels.get(model);
        }
        else{
            System.out.println("The model can't be found, using the global time factor");
            return timeFactorModels.get("global");
        }
    }

    float costCalculator(float time, float money, float timeFactor, CustomModel customModel){
        float relativeCost = 1;
        for (var x: customModel.customCost.keySet()){
            relativeCost += getValueAdjustment(customModel.customCost.get(x));
        }

        return relativeCost * (time * timeFactor + money);
    }
}
