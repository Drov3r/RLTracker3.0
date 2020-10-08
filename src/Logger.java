import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Logger {
    public static void main(String[] args ) {
        while (true){
        int[] playlist = {10, 11, 13, 27, 28, 29, 34};
        String[] playlistNames = {"Solo", "Twos", "Threes", "Hoops", "Rumble", "DropShot", "Tournaments"};
        int[] current = new int[7];
        int i = 0;
        int j = 0;

        for (String x : playlistNames) {
            int num;
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(
                        x + ".txt"));
                String line = reader.readLine();
                String l = line;
                while (line != null) {
                    //System.out.println(line);
                    // read next line
                    line = reader.readLine();
                    if (line != null) {
                        l = line;

                    }
                }
                //System.out.println(playlistNames[j]);
                if (l.contains(",")) {
                    num = removeComma(l);
                } else num = Integer.parseInt(l);
                current[j] = num;
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            j++;
        }


        for (int x : playlist) {
            Tracker mmr = new Tracker(x);
            String rank = mmr.getMmr();
            int num;
            if (rank.contains(",")) {
                num = removeComma(rank);
            } else num = Integer.parseInt(rank);

            if (num != current[i]) {
                try (FileWriter fw = new FileWriter(playlistNames[i] + ".txt", true);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {
                    out.println(num);
                    current[i] = removeComma(rank);
                } catch (IOException e) {
                    //exception handling left as an exercise for the reader
                }

            try (FileWriter fw = new FileWriter(playlistNames[i] + "time.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));

                out.println(dtf.format(now));

            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
        }
            i++;
        }
            try {

                TimeUnit.MINUTES.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public static int removeComma(String rank){
        char []t=rank.toCharArray();
        char []r=new char[t.length-1];
        boolean found=false;
        for (int k = 0; k < t.length-1 ; k++) {
            if (t[k]==','){
                found=true;
            }
            if (found){
                t[k]=t[k+1];
            }
            //t[t.length-1]= 48;

            for (int m = 0; m <r.length ; m++) {
                r[m]=t[m];

            }
        }
        String l= new String(r);
        return Integer.parseInt(l);
    }
}
