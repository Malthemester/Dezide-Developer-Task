import java.util.HashMap;

public class costconverter {
    final static float MINUTES_IN_HOURS = 60;
    public static void main (String[] args) {
        HashMap<String, Integer>  timeFactorModels = new HashMap<String, Integer>();
        timeFactorModels.put("global", 300);
        timeFactorModels.put("model1234", 500);

        try {
            if (args.length != 3)
                throw new IllegalArgumentException("Need three arguments");

            //Parsing to float to also catch if it's not a number
            float time = Float.parseFloat(args[0]);
            //Can't have negative time
            if (time < 0)
                throw new Exception("Time can't be under zero");

            //Parsing to float to also catch if it's not a number
            float money = Float.parseFloat(args[1]);
            if (money < 0)
                throw new Exception("Money can't be under zero");

            float timeFactor = getTimeFactor(timeFactorModels, args[2]) / MINUTES_IN_HOURS;

            float cost = costCalculator(time , money, timeFactor);

            System.out.println(Math.round(cost));
        }
        catch(Exception e) {
            System.out.println("Wrong input:");
            System.out.println(e);
            return;
        }
    }

    static float getTimeFactor(HashMap<String, Integer> timeFactorModels, String model){

        if (timeFactorModels.containsKey(model)){
            return timeFactorModels.get(model);
        }
        else{
            System.out.println("The model can't be found, using the global time factor");
            return timeFactorModels.get("global");
        }
    }

    static float costCalculator(float time, float money, float timeFactor){
        return  time * timeFactor + money;
    }
}
