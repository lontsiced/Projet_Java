import java.io.File;
import java.util.*;
/*
 * @by Cedrick, Nassima, Fred & Baptiste
 */
public class ThreadManager extends Thread {

     ArrayList<Thread> mesThreads = new ArrayList<Thread>();
    ArrayList<Threading> mesThreadings = new ArrayList<Threading>();
     ThreadGroup threadGroup = new ThreadGroup("");
    static String PATH;
    static  String ABSOLU_PATH;
    static int LOT;


//****************************************Mes Thread********************************
    public synchronized void putMesThread (Thread thread) throws InterruptedException {
        mesThreads.add(thread);
        //System.out.println(line[0]+" "+this.recData.size());
    }
    public synchronized ArrayList<Thread> getMesThread () throws InterruptedException {
        //System.out.println("Add listTread");
        return mesThreads;
        //System.out.println(line[0]+" "+this.recData.size());
    }
    public synchronized void putMesThreadings (Threading threading) throws InterruptedException {
        mesThreadings.add(threading);
        //System.out.println(line[0]+" "+this.recData.size());
    }
    public synchronized ArrayList<Threading> getMesThreadings () throws InterruptedException {
        //System.out.println("Add listTread");
        return mesThreadings;
        //System.out.println(line[0]+" "+this.recData.size());
    }
    //********************************Mon Map*************************
    public synchronized ThreadGroup getThreadingMap() throws InterruptedException {
        return threadGroup;
    }

    public synchronized void setThreadGroup (ThreadGroup monthreadGroup) throws InterruptedException {
        threadGroup= monthreadGroup ;
        //System.out.println(line[0]+" "+this.recData.size());
    }



    public synchronized void setInputPath (String inputPath) throws InterruptedException {
        PATH=inputPath;
        //System.out.println(line[0]+" "+this.recData.size());
    }

    public synchronized String getIputPath () throws InterruptedException {
        return PATH;
        //System.out.println(line[0]+" "+this.recData.size());
    }
    public synchronized String getAbsoluPath () throws InterruptedException {
        return (new File(PATH.split(".csv")[0]+"_output.csv").getAbsolutePath());
        //System.out.println(line[0]+" "+this.recData.size());
    }

    public synchronized String getOutputPath () throws InterruptedException {
        return PATH.split(".csv")[0]+"_output.csv";

    }
    public synchronized void setLot (int lot) throws InterruptedException {
        LOT=lot;
        //System.out.println(line[0]+" "+this.recData.size());
    }

    public synchronized int getLot () throws InterruptedException {
        return LOT;
        //System.out.println(line[0]+" "+this.recData.size());
    }



}
