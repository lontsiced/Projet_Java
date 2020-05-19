import java.io.*;
import java.util.ArrayList;
/*
 * @by Cedrick, Nassima, Fred & Baptiste
 */
public class ThreadWriterOutputFile extends Thread{
     ThreadManager threadManager;
     ArrayList<Thread> mesThreads;
    ArrayList<Threading> mesThreadings;

    public ThreadWriterOutputFile(ThreadManager threadManager) {
        this.setName("WriterOutputFile");
        this.threadManager = threadManager;
    }


    public void run() {

        try {
            //sleep((int) (Math.random() * 2000));
            File file = new File(threadManager.getOutputPath());
            mesThreads = threadManager.getMesThread();
            mesThreadings = threadManager.getMesThreadings();
            //mesThreads = new ArrayList<Thread> ();

//            System.out.println("ok "+ threadManager.getMesThread().size());
            PrintWriter writer = new PrintWriter(file);



            for ( Thread thread:mesThreads)
            {
                thread.start();
            }


            //sleep(2000);
            int time=0;
            do {
                time++;
                  sleep(time);
            }while (!ThreadLotFished());
            //while (threadFinish());


                for (Threading threading: mesThreadings)
                {

                      //  System.out.println("lot: "+threading.getOutputLot().size());

                        ArrayList<String> lot = threading.getOutputLot();
                        for (String rec : lot) { writer.println(rec); } //ECRETURE DANS LE FICHIER DE SORTIE
                }

            writer.close();

            System.out.println("- Données traitées et sauvegardées avec succès sur le lien suivant:\n"+threadManager.getAbsoluPath()+"\n");


            for ( Thread thread:mesThreads)
            {   thread.interrupt();
                thread.stop();
            }


            } catch (InterruptedException | IOException e) {
            Thread.currentThread().interrupt();
        }


    }

    //***************************************Therminier les threadsLot****
    public boolean ThreadLotFished()
    {
        boolean finished= true;

        for (Thread thread:mesThreads)
        {
            if(thread.isAlive())
            {
                finished=false;
                break;
            }
        }


        return finished;
    }

}
