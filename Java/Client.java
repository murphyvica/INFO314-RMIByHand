import java.net.*;  
import java.io.*;

public class Client {

    /**
     * This method name and parameters must remain as-is
     */

    public static int add(int lhs, int rhs){
        try (Socket s = new Socket("localhost", 10314)) {
            RemoteMethod add = new RemoteMethod("add", new Object[]{lhs, rhs});
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(add);
            oos.flush();

            ObjectInputStream ins = new ObjectInputStream(s.getInputStream());
            RemoteResult ret = (RemoteResult) ins.readObject();
            Object[] argss = ret.getArgs();
            int result = (Integer) argss[0];
            return result;

        } catch (IOException ex) {
            System.out.println(" Server refused to connect");
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        return -1;
    }

    /**
     * This method name and parameters must remain as-is
     */

    public static int divide(int num, int denom) {
        try (Socket s = new Socket("localhost", 10314)) {
            RemoteMethod divide = new RemoteMethod("divide", new Object[]{num, denom});
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(divide);
            oos.flush();

            ObjectInputStream ins = new ObjectInputStream(s.getInputStream());
            RemoteResult ret = (RemoteResult) ins.readObject();
            Object[] argss = ret.getArgs();
            String type = ret.getType();
            if (type.equals("int")) {
                int result = (Integer) argss[0];
                return result;
            } else if (type.equals("Ex")) {
                ArithmeticException ex = (ArithmeticException) argss[0];
                throw ex;
            }

        } catch (IOException ex) {
            System.out.println(" Server refused to connect");
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        return -1;
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static String echo(String message) {
        try (Socket s = new Socket("localhost", 10314)) {
            RemoteMethod echo = new RemoteMethod("echo", new Object[]{message});
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(echo);
            oos.flush();

            ObjectInputStream ins = new ObjectInputStream(s.getInputStream());
            RemoteResult ret = (RemoteResult) ins.readObject();
            Object[] argss = ret.getArgs();
            String result = (String) argss[0];
            return result;

        } catch (IOException ex) {
            System.out.println(" Server refused to connect");
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        return "";
    }

    // Do not modify any code below this line
    // --------------------------------------
    String server = "localhost";
    public static final int PORT = 10314;
    
    public static void main(String... args) {

        // All of the code below this line must be uncommented
        // to be successfully graded.
        System.out.print("Testing... ");

         if (add(2, 4) == 6)
            System.out.print(".");
        else
            System.out.print("X"); 

        try {
            divide(1, 0);
            System.out.print("X");
        }
        catch (ArithmeticException x) {
            System.out.print(".");
        }

        if (echo("Hello").equals("You said Hello!"))
            System.out.print(".");
        else
            System.out.print("X");
        
        System.out.println(" Finished");
    }
}