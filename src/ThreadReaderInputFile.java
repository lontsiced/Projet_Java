import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/*
 * @by Cedrick, Nassima, Fred & Baptiste
 */
public class ThreadReaderInputFile extends Thread
{
     ThreadManager threadManager;


    private int lot;

    public ThreadReaderInputFile( ThreadManager threadManager) {
        this.setName("ThreadReaderInputFile");
        this.threadManager = threadManager;
    }


    public void start() {

        try {


            int i = 0;
            int nbreLot= 0;
            String line;
            ArrayList<String>  mesLots=new ArrayList<String>();

            Threading threading;
            int lot =threadManager.getLot();
            File file = new File(threadManager.getIputPath());

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);


            ThreadGroup threadGroupLot = new ThreadGroup("threadGroupLot");
            if(lot==0)
            {
                while ((line = br.readLine()) != null) {
                    nbreLot++;

                    mesLots.add(line);
                }

                threading = new Threading(mesLots);
                Thread thread = new Thread(threading, "ThreadLot-" + nbreLot);
                threadManager.putMesThread(thread);
                threadManager.putMesThreadings(threading);

            }else {
                while ((line = br.readLine()) != null) {
                    i++;
                    mesLots.add(line);
                    // System.out.println(""+mesLots.size());


                    if (i == lot) {
                        nbreLot++;
                        // System.out.println(""+nbreLot);
                        threading = new Threading(mesLots);

                        Thread thread = new Thread(threadGroupLot, threading, "ThreadLot-" + nbreLot);
                        threadManager.putMesThread(thread);
                        threadManager.putMesThreadings(threading);


                        mesLots = new ArrayList<String>();

                        i = 0;
                    }

                }

                if (mesLots.size() > 0 && mesLots.size() < lot && lot != 0) {

                    nbreLot++;
                    //System.out.println(""+nbreLot);
                    threading = new Threading(mesLots);

                    Thread thread = new Thread(threading, "ThreadLot-" + nbreLot);
                    threadManager.putMesThread(thread);
                    threadManager.putMesThreadings(threading);


                    mesLots = new ArrayList<String>();
                    i = 0;


                }
            }
            System.out.println("\n- "+nbreLot+" traitements en parallele pour chaque lot ("+lot+" / thread)");

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

