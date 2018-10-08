
public class CalculateDelta {

    public static void main(String[] args) {
        Double firstArgument = Double.parseDouble(args[0]);
        Double secondArgument = Double.parseDouble(args[1]);
        Double thirdArgument = Double.parseDouble(args[2]);


        Double delta = secondArgument* secondArgument - 4* firstArgument * thirdArgument;
        System.out.println(delta);

        if(delta == 0){
            Double x = (-1 * secondArgument)/ 2* firstArgument;
            System.out.println("Rozwiazanie to: " + "");
        }else if (delta > 0){

            Double sqrt = Math.sqrt(delta);
            Double x1 = ((-1 * secondArgument ) - sqrt)/ 2 * secondArgument;
            Double x2 = ((-1 * secondArgument ) + sqrt)/ 2 * secondArgument;
            System.out.println("Rozwiązanie to: " + x1 + ", " + x2);
        }else if (delta < 0){
            System.out.println("delta jest ujemna - brak rozwiązań");
        }
    }
}
