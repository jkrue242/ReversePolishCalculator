import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.util.Scanner;
import java.util.Stack;
import java.lang.Math;
import java.util.regex.Pattern;

public class RPNCalculator {
    private Stack<Double> stack;
    private Pattern identToken;
    private Pattern binopToken;

    public RPNCalculator() {
        // Initialize stack to be an empty Stack to hold double data values
        stack = new Stack<Double>();

        // these are for processing text input
        identToken = Pattern.compile("\\p{Alpha}+");
        binopToken = Pattern.compile("\\+|\\*|-|\\/|\\^");
    }

    public double enter(double x) {
        stack.push(x);
        return x;
    }

    public double add() {
        return stack.push(stack.pop() + stack.pop());
    }

    public double subtract() {
        double x = stack.pop();
        double y = stack.pop();
        return stack.push(y-x);
    }

    public double multiply() {
        double x = stack.pop();
        double y = stack.pop();
        return stack.push(x*y);
    }

    public double divide() {
        double x = stack.pop();
        double y = stack.pop();
        return stack.push(y/x);
    }

    public double power() {
        double x = stack.pop();
        double y = stack.pop();
        return stack.push(Math.pow(y,x));
    }

    public double exp() {
        return stack.push(Math.exp(stack.pop()));
    }

    public double log() {
        return stack.push(Math.log(stack.pop()));
    }

    public double sin() {
       return stack.push(Math.sin(stack.pop()));
    }

    public double cos() {
        return stack.push(Math.cos(stack.pop()));
    }

    public double sqrt() {
        return stack.push(Math.sqrt(stack.pop()));
    }

    public double pop() {
        return stack.pop();
    }

    public void clear() {
        stack.clear();
    }

    public void processLine(String line) throws CalculatorException {
        Scanner linescan = new Scanner(line);
        while ( linescan.hasNext() ) {
            if ( linescan.hasNextDouble() ) {
                enter(linescan.nextDouble());
            }
            else if ( linescan.hasNext(binopToken) )
            {
                String binop = linescan.next(binopToken);
                switch ( binop ) {
                    case "+": add(); break;
                    case "-": subtract(); break;
                    case "*": multiply(); break;
                    case "/": divide(); break;
                    case "^": power(); break;
                    default:
                        try
                        {
                            throw new CalculatorException(binop);
                        }
                        catch(CalculatorException e)
                        {
                            System.out.println("Unknown operation: "+binop);
//                            throw e;
                        }
                }
            }
            else if ( linescan.hasNext(identToken) )
            {
                String func = linescan.next(identToken);
                switch ( func ) {
                    case "exp": exp(); break;
                    case "log": log(); break;
                    case "sin": sin(); break;
                    case "cos": cos(); break;
                    case "sqrt": sqrt(); break;
                    case "inspect":
                        System.out.println("Stack: " + stack); break;
                    case "end": case "break": case "stop": case "quit":
                        System.exit(0); break;
                    default:
                        try
                        {
                            throw new CalculatorException(func);
                        }
                        catch(CalculatorException e)
                        {
                            System.out.println("Unknown function: "+func);
//                            throw e;
                        }
                }
            }
            else
                try
                {
                    throw new CalculatorException(line);
                }
                catch(CalculatorException e)
                {
                    System.out.println("Cannot Process  \""+line+"\"");
//                            throw e;
                }
        }
    }
    public static void main(String[] args) {
        Scanner scnr = null;
        if ( args.length > 0 ) {
            try {
                scnr = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.out.println("Could not find file \"" + args[0] + "\"");
                System.out.println("Exception: " + e);
                System.exit(1);
            } catch (IOError e) {
                System.out.println("IOException opening file \"" + args[0] + "\"");
                System.out.println("Exception: " + e);
                System.exit(1);
            }
        } else {
            scnr = new Scanner(System.in);
            // Some helpful instructions
            System.out.println("RPN calculator");
            System.out.println("Separate inputs by spaces");
            System.out.println("Enter \"stop\", \"quit\", \"end\" to end program" +
                    " and \"inspect\" to see the state of the stack");
            System.out.println("Operators: +, -, *, /, ^");
            System.out.println("Functions: sin, cos, exp, log, sqrt");
        }

        RPNCalculator calc = new RPNCalculator();
        while ( scnr.hasNextLine() ) {
            // process each line separately
            try {
                String line = scnr.nextLine();
                System.out.println("Processing line: " + line);
                calc.processLine(line);
                System.out.println("Result is " + calc.pop());
            }
            catch (CalculatorException e) {
                System.out.println("" + e);
            }
            catch (Exception e) {
                System.out.println("Exception: " + e);
            }
            calc.clear();
        }
    }
}

