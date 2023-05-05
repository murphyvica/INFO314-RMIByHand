import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        try (ServerSocket sock = new ServerSocket(10314)) {
            while (true) {
            Socket s = sock.accept();
            ObjectInputStream ins = new ObjectInputStream(s.getInputStream());
            RemoteMethod data = (RemoteMethod) ins.readObject();
            String method = data.getName();
            Object[] argss = data.getArgs();

            Object result = -1;
            String type = "ans";

            if (method.equals("add")) {
                int l = (Integer) argss[0];
                int r = (Integer) argss[1];
                result = add(l, r);
                type = "int";
            } else if (method.equals("divide")) {
                int n = (Integer) argss[0];
                int d = (Integer) argss[1];
                try { 
                    result = divide(n, d);
                    type = "int";
                } catch (ArithmeticException ex) {
                    result = ex;
                    type = "Ex";
                } 
            } else if (method.equals("echo")) {
                String str = (String) argss[0];
                result = echo(str);
                type = "String";
            }

            RemoteResult res = new RemoteResult(type, new Object[]{result});
            ObjectOutputStream outs = new ObjectOutputStream(s.getOutputStream());
            outs.writeObject(res);
            outs.flush();
        }

        } catch (Exception er) {
            er.printStackTrace();
        }
    }

    // Do not modify any code below tihs line
    // --------------------------------------
    public static String echo(String message) { 
        return "You said " + message + "!";
    }
    public static int add(int lhs, int rhs) {
        return lhs + rhs;
    }
    public static int divide(int num, int denom) {
        if (denom == 0)
            throw new ArithmeticException();

        return num / denom;
    }
}