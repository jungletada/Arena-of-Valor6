package Valor;
/**
 * MapThread
 * a thread to control the print
 */
class MapThread implements Runnable{
    static Map map;
    public boolean flag;
    public Artificial art;

    /**
     * set the map and flag
     * @param m
     */
    public MapThread(Map m){
        this.map=m;
        flag=true;
    }

    /**
     * set the AI
     * @param a
     */
    public void SetArt(Artificial a){
        this.art=a;
    }
    //----------------------------------------------------

    /**
     * delay for a period time
     */
    public void delay(){
        try{
            Thread.sleep(1500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    //---------------------------------------------------
    @Override
    /**
     * print the map and co-operate with Character
     * @Map
     */
    public synchronized void run() {
        while (true)
        {
            if(map.flag==false){
                art.FieldReset();
                delay();
            }
            map.Print_Field_single();
            delay();
        }
    }
}
public class Map {
    char[][] field = new char[11][11];
    char[][] temp = new char[11][11];
    public int thr;
    public boolean flag;

    /**
     * set the flag
     * @param f
     */
    public void Set(boolean f){flag=f;}

    /**
     * initial the map
     */
    public void SetField(){
        for(int i=0;i<11;i++)
            for(int j=0;j<11;j++)
                field[i][j]='·';
        for(int i=2;i<=4;i++) {
            field[i][2]='$';
            field[i][8]='$';
        }
        for(int i=6;i<=8;i++) {
            field[i][2]='%';
            field[i][8]='%';
        }
        field[3][3]='+';
        field[3][7]='+';
        field[7][3]='*';
        field[7][7]='*';

        field[0][1]='A';
        field[0][3]='B';
        field[0][5]='C';
        field[0][7]='D';
        field[0][9]='E';
        field[10][1]='F';
        field[10][3]='G';
        field[10][5]='H';
        field[10][7]='I';
        field[10][9]='J';
        thr=0; Set(true);
    }

    /**
     * print the map and co-operate with Character
     */
    public  void Print_Field_single(){
        synchronized(this){
            if(flag==false){
                try{ Thread.sleep(6500); }
                catch (InterruptedException e){e.printStackTrace();}
            }
            flag=true;
            try{ Thread.sleep(1000); }
              catch (InterruptedException e){e.printStackTrace();}

            for(int i=0;i<11;i++){
                for(int j=0;j<11;j++)
                    System.out.print(field[i][j]+" ");
                System.out.println();
            }
            System.out.println("-----------------------");
        }
    }

    /**
     * Character died
     * @param x
     * @param y
     */
    public void SetDied(int x,int y) {
        field[x][y]='·';
    }
}