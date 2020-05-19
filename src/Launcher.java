
import java.util.Scanner;
/*
 * @by Cedrick, Nassima, Fred & Baptiste
 */
import static java.lang.Thread.sleep;

public class Launcher {

    public static void main(String[] args) throws InterruptedException {


        menu ();

         int lot = 0;
         while (true) {
             Scanner sc = new Scanner(System.in);
             System.out.println("En combien de Lot souhaité vous optimiser votre traitement: ");

             if (sc.hasNextBigInteger()) {
                 lot = sc.nextInt();
                 //if(lot==0){ lot=1; }
                 break;
             } else {
                 System.out.println("Valeur de lot incorrect.\n");
             }
         }

         ThreadManager threadManager = new ThreadManager();
         threadManager.setInputPath("doto.csv");

         threadManager.setLot(lot);

         ThreadReaderInputFile readFile = new ThreadReaderInputFile(threadManager);
         ThreadWriterOutputFile writerFile = new ThreadWriterOutputFile(threadManager);

         readFile.start();
         writerFile.start();

         while (writerFile.isAlive()) ;


         readFile.interrupt();
         writerFile.interrupt();
        readFile.stop();
        writerFile.stop();

    }

    public static void menu()
    {

        System.out.println("\n*************************************************************************************************");
        System.out.println("*       BIENVENUE SUR NOTRE PROJET JAVA DE TRAITEMENT MULTI-THREADING DE FICHIER PAR LOT        *");
        System.out.println("*                 Edité par Nassima, Cedrick, Jordan , Fred & Baptiste                          *");
        System.out.println("*                           Encadré par M. Dominique KAMTA                                      *");
        System.out.println("*************************************************************************************************\n\n");
    }

}

