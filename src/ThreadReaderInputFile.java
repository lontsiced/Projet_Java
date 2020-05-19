import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
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
            ArrayList<String>  mesLotsComplet=new ArrayList<String>();

            Threading threading;
            int lot =threadManager.getLot();
            File file = new File(threadManager.getIputPath());

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                mesLotsComplet.add(line);
            }
            System.out.println("nobre de data: "+mesLotsComplet.size());
            int lotInitial= lot;
            int nbreLineParLot= (Integer) (mesLotsComplet.size()/lot);

            int reste =   mesLotsComplet.size() % lot; String texte="";

            if(reste!=0 )  { lot= lot+1; texte=texte+ "\n\t* 1 dernier LOT de ["+reste+"] enregistrements";}

            System.out.println("nobre de data: "+nbreLineParLot);
                line="";

            if(nbreLineParLot==0)
            {

                    nbreLot++;

                    mesLots.add(line);


                threading = new Threading(mesLotsComplet);
                Thread thread = new Thread(threading, "ThreadLot-" + nbreLot);
                threadManager.putMesThread(thread);
                threadManager.putMesThreadings(threading);

            }else {
                for( String data : mesLotsComplet){
                    i++;
                    mesLots.add(data);
                    // System.out.println(""+mesLots.size());


                    if (i == nbreLineParLot) {
                        nbreLot++;
                        // System.out.println(""+nbreLot);
                        threading = new Threading(mesLots);

                        Thread thread = new Thread( threading, "ThreadLot-" + nbreLot);
                        threadManager.putMesThread(thread);
                        threadManager.putMesThreadings(threading);

                        mesLots = new ArrayList<String>();
                        i = 0;
                    }

                }

                if (mesLots.size() > 0 && mesLots.size() < nbreLineParLot && nbreLineParLot != 0) {

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
            System.out.println("\n [**************************** SORTIE *******************************] \n- "
                    +nbreLot+" traitements en parallele sur ["+mesLotsComplet.size()+"] enregistrements au total: \n\t* "+lotInitial+" LOT de ["+nbreLineParLot+"] enregistrements/LOT"+texte+"");

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

