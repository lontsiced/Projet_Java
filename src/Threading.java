import java.util.ArrayList;
/*
 * @by Cedrick, Nassima, Fred & Baptiste
 */
public class Threading implements Runnable {
    ThreadManager threadManager;
     ArrayList<String> inputLot;
     ArrayList<String> outputLot;
    ArrayList<Thread[]> mesThread = new ArrayList<Thread[]>();



    public Threading(ArrayList<String> lot)
    {
        this.inputLot=lot;
    }


    @Override
    public void run() {
        outputLot = new ArrayList<String>();
        String val="";

        for (String line : inputLot)
        {
            String[] rec= line.split(";");
            val =String.valueOf( Long.valueOf(rec[2]) * 2);
            rec[2]=val;
            outputLot.add("" + rec[0] + ";" + rec[1] + ";" + rec[2]);
        }
       /*    try {
         threadManager.putMesThreadings(this);
            threadManager.putMesThread(Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //System.out.println(Thread.currentThread().getName()+":"+inputLot.size());
        //System.out.println(outputLot.size()+"");
    }
    public ArrayList<String>  getOutputLot()
    {
        return outputLot;
    }
}
