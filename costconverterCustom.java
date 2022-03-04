import java.util.HashMap;
import java.util.List;

public class costconverterCustom {
    final static float MINUTES_IN_HOURS = 60;
    static customModelManager manager = new customModelManager();

    public static void main (String[] args) {
        HashMap<String, CustomModel> timeFactorModels = new HashMap<String, CustomModel>();
        timeFactorModels.put("global", new CustomModel(300,"low","high"));
        timeFactorModels.put("model1234", new CustomModel(500,"high","medium"));

        try {
            if (args.length < 3)
                throw new IllegalArgumentException("Need at lest three arguments");

            //Parsing to float to also catch if it's not a number
            float time = Float.parseFloat(args[0]);
            //Can't have negative time
            if (time < 0)
                throw new Exception("Time can't be under zero");

            //Parsing to float to also catch if it's not a number
            float money = Float.parseFloat(args[1]);
            if (money < 0)
                throw new Exception("Money can't be under zero");

            List<String> customArgs = manager.getCustomArgs(args);

            CustomModel curModel = manager.getModel(timeFactorModels, args[2]);

            manager.setCustomValuesOnModel(curModel, customArgs);

            float timeFactor = curModel.cost / MINUTES_IN_HOURS;

            float cost = manager.costCalculator(time , money, timeFactor, curModel);

            System.out.println(Math.round(cost));
        }
        catch(Exception e) {
            System.out.println("Wrong input:");
            System.out.println(e);
        }
    }
}

