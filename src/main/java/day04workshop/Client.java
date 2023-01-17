package day04workshop;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    
    public static void main(String[] args) throws IOException {
        
        Socket socket = new Socket("localhost", 12345);

        try (OutputStream os = socket.getOutputStream()) {
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            InputStream is = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            Console cons = System.console();
            String readInput = "";

            while (!readInput.equalsIgnoreCase("close")) {
                readInput = cons.readLine("enter <get-cookie> to get a random cookie or <close> to quit\n");
                dos.writeUTF(readInput);
                dos.flush();

                String line = dis.readUTF();
                System.out.println(line);
            }

            dis.close();
            bis.close();
            is.close();

        } catch (EOFException ex) {
            socket.close();
        }
    }
}
