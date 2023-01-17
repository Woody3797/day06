package day04workshop;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        String dirPath = "./data";
        File newDirectory = new File(dirPath);

        if(newDirectory.exists()) {
            System.out.println("Directory already exists");
        } else {
            newDirectory.mkdir();
        }

        Cookie cookie = new Cookie();
        cookie.readCookieFile();
        //cookie.showCookies();

        ServerSocket ss = new ServerSocket(12345);
        Socket s = ss.accept(); // establish connection and wait for client to connect

        try (InputStream is = s.getInputStream()) {
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            OutputStream os = s.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);
            String line = "";

            while (!line.equals("close")) {
                line = dis.readUTF();

                if (line.equalsIgnoreCase("get-cookie")) {
                    String cookieValue = cookie.returnCookie();
                    dos.writeUTF(cookieValue);
                    dos.flush();
                }
            }
            dos.close();
            bos.close();
            os.close();
            dis.close();
            bis.close();
            is.close();
        } catch (EOFException ex) {
            s.close();
            ss.close();
        }
    }
}
