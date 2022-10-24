public class CalculatorException extends Exception {
    CalculatorException(){
        mesg = "";
    }
    CalculatorException(String input){
        mesg = input;
    }
    private String mesg;
    public String toString() {
        return "CalculatorException: "+mesg;
    }
}
