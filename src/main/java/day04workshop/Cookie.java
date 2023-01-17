package day04workshop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {
    
    String dirPath = "./data";
    String filename = "cookie.txt";
    List<String> cookieItems = null;

    public void readCookieFile() throws IOException {
        cookieItems = new ArrayList<>();

        File file = new File(dirPath + File.separator + filename);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String readString = "";

        while ((readString = br.readLine()) != null) {
            cookieItems.add(readString);
        }
        br.close();
    }


    public String returnCookie() {
        Random random = new Random();

        if (cookieItems != null) {
            return cookieItems.get(random.nextInt(cookieItems.size()));
        } else {
            return "no cookies found!";
        }
    }

    
    public void showCookies() {
        if (cookieItems != null) {
            cookieItems.forEach(c -> System.out.println(c));
        }
    }

}
