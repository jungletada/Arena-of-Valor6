package Valor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main{
    public static void delay(){
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    //------------------------main-------------------------------
    public static void main(String[]args)
    {
        int lines = 0;
        final int MAX = 65535;
        String[] order = new String[MAX];
        Map map = new Map();
        map.SetField();

        Artificial AI = new Artificial(map);
        MapThread Print = new MapThread(map);

        //-----------------------------------------------------
        HeroA A = new HeroA(map);
        HeroB B = new HeroB(map);
        HeroC C = new HeroC(map);
        HeroD D = new HeroD(map);
        HeroE E = new HeroE(map);
        HeroF F = new HeroF(map);
        HeroG G = new HeroG(map);
        HeroH H = new HeroH(map);
        HeroI I = new HeroI(map);
        HeroJ J = new HeroJ(map);
        //-----------------------------------------------------
        B.BeAttack(H);
        AI.SetAI(A, B, C, D, E);
        AI.SetMan(F, G, H, I, J);
        Print.SetArt(AI);
        map.Print_Field_single();
        //-----------------------------------------------------
        Thread PrintMap = new Thread(Print);
        Thread AIrun = new Thread(AI);
        PrintMap.start();
        new Thread(A).start();  new Thread(B).start();
        new Thread(C).start();  new Thread(D).start();
        new Thread(E).start();
        //AIrun.start();
        //-----------------------------------------------------
        /**
         * read the file to order the Character
         */
        File file = new File("D:\\java_test\\thread_test\\src\\Valor\\System.txt");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((order[lines] = reader.readLine()) != null)
            {
                /**
                 * Character move in different direction
                 */
                if (order[lines].contains("MOVE")) {
                    char on = order[lines].charAt(5);
                    char dir = order[lines].charAt(7);
                    switch (on) {
                        case 'F': F.Move(dir);break;
                        case 'G': G.Move(dir);break;
                        case 'H': H.Move(dir);break;
                        case 'I': I.Move(dir);break;
                        case 'J': J.Move(dir);break;
                    }
                }
                //---------------------------------------------
                else if (order[lines].contains("ATTACK"))
                {
                    map.flag=false;
                    char on = order[lines].charAt(7);
                    char be = order[lines].charAt(9);
                    Character p = C;
                    switch (be) {
                        case 'A': p = A;break;
                        case 'B': p = B;break;
                        case 'C': p = C;break;
                        case 'D': p = D;break;
                        case 'E': p = E;break;
                    }
                    switch (on) {
                        case 'F':
                            F.BeAttack(p);
                            F.CalculatePath();
                            break;
                        case 'G':
                            G.BeAttack(p);
                            G.CalculatePath();
                            break;
                        case 'H':
                            H.BeAttack(p);
                            H.CalculatePath();
                            break;
                        case 'I':
                            I.BeAttack(p);
                            I.CalculatePath();
                            break;
                        case 'J':
                            J.BeAttack(p);
                            J.CalculatePath();
                            break;
                    }
                    AI.FieldReset();
                }
                lines++;  delay();
            }
        }
        catch (IOException e) { e.printStackTrace();}
    }
}
