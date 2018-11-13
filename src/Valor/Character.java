package Valor;

public abstract class Character {
    public int HP, MP, Exp, range;
    public double distance;
    public char name;
    public String Name;
    public int x, y, horde;
    public boolean beAttack;
    public Map map;
    public Character be;

    /**
     * Initial the Character
     * of the Exp,HP,MP and state
     */
    public Character() {
        Exp = 0;
        HP = 100;
        MP = 50;
        beAttack = false;
    }

    /**
     * Judge the Character is alive or not
     * @return is alive
     */
    public boolean IsAlive() {
        if (HP <= 0) {
            map.SetDied(x, y);
            System.out.println("Hero_" + name + " died!");
            return false;
        } else
            return true;
    }

    /**
     * Character this will attack Character be
     * @param be
     */
    public void BeAttack(Character be){
        this.be=be;
    }

    /**
     * Insure the HP,MP,Exp in the normal level
     */
    public void Restrict(){
        if (HP > 100) {
            HP = 100;
        }
        if (MP > 100) {
            MP = 100;
        }
        if (Exp > 100) {
            Exp = 100;
        }
    }
    //-----------------------------------------------------------------------

    /**
     * Show the info of Character
     */
    public void ShowInfo() {
        IsAlive();
        Restrict();
        System.out.println("Hero " + Name + " --- HP: " + HP
                + " MP: " + MP + " Exp: " + Exp);
        if (horde == 1)
            System.out.println("location:(" + x + "," + y + ")---Horde: HoNoO");
        else
            System.out.println("location:(" + x + "," + y + ")---Horde: MiZu");
    }
    //---------------------------------------------------------------------------

    /**
     * let the Character to move in exact direction
     * @param dir
     */
    public void Move(char dir) {
        switch (dir) {
            case 'W':
                Moveup();
                break;
            case 'S':
                Movedown();
                break;
            case 'A':
                Moveleft();
                break;
            case 'D':
                Moveright();
                break;
        }
    }
    //------------------------------------------------------------------------------

    /**
     * get the distance between 2 Characters
     * @param p
     * @return distance
     */
    public double getDistance(Character p) {
        distance = (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y);
        distance = Math.sqrt(distance);
        return distance;
    }

    public synchronized void Moveup() {
        if (x - 1 >= 0) {
            char t = map.field[x - 1][y];
            if (t == '·') {
                map.field[x - 1][y] = name;
                map.field[x][y] = '·';
                x--;
            }
        }
    }

    public synchronized void Movedown() {
        if (x + 1 < 11) {
            char t = map.field[x + 1][y];
            if (t == '·') {
                map.field[x + 1][y] = name;
                map.field[x][y] = '·';
                x++;
            }
        }
    }

    public synchronized void Moveleft() {
        if (y-1>= 0){
            char t = map.field[x][y-1];
            if (t=='·') {
                map.field[x][y-1] = name;
                map.field[x][y] = '·';
                y--;
            }
        }
    }

    public synchronized void Moveright() {
        if (y+1< 11) {
            char t = map.field[x][y+1];
            if (t=='·') {
                map.field[x][y+1] = name;
                map.field[x][y] = '·';
                y++;
            }
        }
    }

    /**
     * sleep the thread for 5 seconds
     * let the AI to calculate
     */
    public void delay(){
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Calculate the path between active and passive Character
     * Show the trace of bullet
     */
    public synchronized void CalculatePath(){
        map.flag=false;
        for (int i = 0; i < 11; i++)
            for (int j = 0; j < 11; j++) {
                map.temp[i][j]=map.field[i][j];
            }
            for (int i = 0; i < 11; i++)
                for (int j = 0; j < 11; j++) {
                    map.field[i][j]='·';
                }
            int i=x;  int j=y;
            //first if
            if(x<=be.x) {
                if(y<=be.y){  //case 1
                    while(i<be.x || j<be.y){
                        if(i<be.x){
                            i++;  map.field[i][j]='O';
                            map.field[x][y]=name;
                            map.field[be.x][be.y]=be.name;
                            PrintEffect();
                        }
                        if(j<be.y){
                            j++;  map.field[i][j]='O';
                            map.field[x][y]=name;
                            map.field[be.x][be.y]=be.name;
                            PrintEffect();
                        }
                    }
                }
                else{  //case 2
                    while(i<be.x || j>be.y){
                        if(i<be.x){
                            i++;  map.field[i][j]='O';
                            map.field[x][y]=name;
                            map.field[be.x][be.y]=be.name;
                            PrintEffect();
                        }
                        if(j>be.y){
                            j--;  map.field[i][j]='O';
                            map.field[x][y]=name;
                            map.field[be.x][be.y]=be.name;
                            PrintEffect();
                        }
                    }
                }
                PrintEffect();
            }
            //first else
            else{    //case 3
                if(y<=be.y){
                    while(i>be.x || j<be.y){
                        if(i>be.x){
                            i--;  map.field[i][j]='O';
                            map.field[x][y]=name;
                            map.field[be.x][be.y]=be.name;
                            PrintEffect();
                        }
                        if(j<be.y){
                            j++; map.field[i][j]='O';
                            map.field[x][y]=name;
                            map.field[be.x][be.y]=be.name;
                            PrintEffect();
                        }
                    }
                }
                else{  //case 4
                    while(i>be.x || j>be.y){
                        if(i>be.x){
                            i--;  map.field[i][j]='O';
                            map.field[x][y]=name;
                            map.field[be.x][be.y]=be.name;
                            PrintEffect();
                        }
                        if(j>be.y){
                            j--;  map.field[i][j]='O';
                            map.field[x][y]=name;
                            map.field[be.x][be.y]=be.name;
                            PrintEffect();
                        }
                    }
                }
                PrintEffect();
            }

        for (int m = 0; m < 11; m++)
            for (int n = 0; n < 11; n++) {
                map.field[m][n]=map.temp[m][n];
            }
            delay();
    }

    /**
     * Print the trace of bullet
     */
    public synchronized void PrintEffect(){
     try {
         Thread.sleep(800);}
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        PrintTemp();
    }
    public synchronized void PrintTemp(){
        for (int i = 0; i < 11; i++){
            for (int j = 0; j < 11; j++)
                System.out.print(map.field[i][j]+" ");
            System.out.println();
        }System.out.println("-----------------------");
    }
}

//--------------------------------------------------------------------
interface skill{
    void ActiveSkill();
    void PassiveSkill();
    void SetLocation();
    void Attacking();
    void SpecialSkill();
}
/**
 * HeroA
 */
class HeroA extends Character implements skill,Runnable{
    public HeroA(Map m){
        name='A';range=15;Name="Abdie";map=m;
        x=0; y=1;
    }
    public void SetLocation(){
    }
    public void ActiveSkill(){
        if(Exp>=10){
            MP+=30;  HP+=20;Exp-=10;
            if(be.Exp>10)
                be.Exp-=10;
            else
                be.Exp=0;
        }
    }
    //----------------------------------------------------------
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=2;Exp+=5;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(){
        if(MP>=20){
            be.HP-=20;
            MP-=20;
            Exp+=20;
            be.beAttack=true;
        }
    }

    public void SpecialSkill(){
        if(horde==1 && map.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=10;map.field[3][3]='·';
            }
        }
        if(horde==1 && map.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=10;map.field[3][7]='·';
            }
        }

        if(horde==2 && map.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=10;map.field[7][3]='·';
            }
        }
        if(horde==2 && map.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp += 10;
                map.field[7][7] = '·';
            }
        }
    }

    public synchronized void Move(){
        Movedown();
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
        }
        Moveright();
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
        }
    }
    public synchronized void run(){
        if(map.flag==false){
            delay();
        }
        map.flag=true;
        delay();
        for (int i = 0; i < 5; i++) {
            delay();
            Move();
            delay();
        }
        notifyAll();
    }
}

//--------------------------B-------------------------------
class HeroB extends Character implements skill,Runnable{
    public HeroB(Map m){
        name='B'; range=2;Name="Bacheler"; map=m;
        x=0; y=3;
    }
    public void SetLocation(){
    }
    public void ActiveSkill(){
        if(Exp>=15){
            MP+=35;  HP+=10;  Exp-=15;
            if(be.HP>15)
                be.HP-=15;
            else
                be.HP=1;
        }
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=1;Exp+=7;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(){
        if(MP>=5) MP-=5;
        if(getDistance(be)<=range){
            be.HP-=15;
            Exp+=15;
            be.beAttack=true;
        }
    }
    public void SpecialSkill(){
        if(horde==1 && map.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=12;map.field[3][3]=' ';
            }
        }
        if(horde==1 && map.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=12;map.field[3][7]=' ';
            }
        }

        if(horde==2 && map.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=12;map.field[7][3]=' ';
            }
        }
        if(horde==2 && map.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp += 12;map.field[7][7] = ' ';
            }
        }
    }
    public synchronized void Attack_Run(){
        if(x+1<11&&x-1>=0){
            if(map.field[x+1][y]!='·'||map.field[x+1][y]!='$')
                Moveup();
            else if(map.field[x-1][y]!='·'||map.field[x-1][y]!='$')
                Movedown();
        }
        if(y+1<11&&y-1>=0){
            if(map.field[x][y+1]!='·'||map.field[x][y+1]!='$')
                Moveleft();
            else if(map.field[x][y-1]!='·'||map.field[x][y-1]!='$')
                Moveright();
        }
    }
    public synchronized void run(){
        delay(); delay();
        CalculatePath();
        //int t=3;
        while (true){
            delay();
            Movedown();
            delay();
            Moveright();
            delay();
            //Attack_Run();CalculatePath();
        }
    }
}
//---------------------------C--------------------------------

/**
 * This is Character C
 * @see Character
 * @see Map
 * @see Artificial
 * @thorw InterruptedException
 */
class HeroC extends Character implements skill,Runnable{
    /**
     * initial the HeroC
     * @param m
     */
    public HeroC(Map m){
        name='C';range=4;Name="Cahill";
        map=m; x=0; y=5;
    }

    /**
     * Active skill to function on other hero
     * @return void
     */
    public void ActiveSkill(){
        if(Exp>=20){
            MP+=25;  HP+=10;Exp-=20;
            if(be.MP>10)
                be.MP-=10;
            else
                be.MP=0;
        }
    }
    public void SetLocation(){
        x=0; y=5;
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=3;Exp+=2;range=3;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(){
        if(MP>=10) MP-=10;
        if(getDistance(be)<=range){
            be.HP-=20;
            Exp+=20;
            be.beAttack=true;
        }
    }
    public void SpecialSkill(){
        if(horde==1 && map.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=9;map.field[3][3]=' ';
            }
        }
        if(horde==1 && map.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=9;map.field[3][7]=' ';
            }
        }

        if(horde==2 && map.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=9;map.field[7][3]=' ';
            }
        }
        if(horde==2 && map.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp += 9;
                map.field[7][7] = ' ';
            }
        }
    }
    public synchronized void run(){
        delay();
        for (int i = 0; i < 3; i++) {
            delay();
            Movedown();
            delay();
        }
        notifyAll();
    }
}
class HeroD extends Character implements skill,Runnable{
    public HeroD(Map m){
        name='D';range=1;Name="Dahlia";
        x=0; y=7; map=m;
    }
    public void SetLocation(){

    }
    public void ActiveSkill(){
        if(Exp>=20){
            MP+=35;  HP+=5;Exp-=20;
            if(be.Exp>10)
                be.Exp-=10;
            else
                be.Exp=0;
        }
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=2;Exp+=2;MP+=5;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(){
        if(MP>=10) MP-=10;
        if(getDistance(be)<=range) {
            be.HP -= 30;
            Exp += 20;
            be.beAttack=true;
        }
    }
    public void SpecialSkill(){
        if(horde==1 && map.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=10;map.field[3][3]='·';
            }
        }
        if(horde==1 && map.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=10;map.field[3][7]='·';
            }
        }

        if(horde==2 && map.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=10;map.field[7][3]='·';
            }
        }
        if(horde==2 && map.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp += 10;
                map.field[7][7] = '·';
            }
        }
    }
    public synchronized void run(){
    /*    while (!map.flag){
            try {
                wait();
            }catch (InterruptedException e){}
        }*/
        delay();
        while (true){
            Movedown();
            delay();
            Moveleft();
        }
    }
}
//----------------------------E-------------------------------------
class HeroE extends Character implements skill,Runnable{
    public HeroE(Map m){
        name='E';range=1;Name="Earle";
        map=m;x=0; y=9;
    }
    public void SetLocation(){

    }
    public void ActiveSkill(){
        if(Exp>=50){
            MP=0;  HP+=0;Exp-=50;
            if(be.HP>40)
                be.HP-=40;
            else
                be.HP=40;
        }
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=2;Exp+=5;MP+=2;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(){
        if(MP>=20) MP-=20;
        if(getDistance(be)<=range) {
            be.HP -= 35;
            Exp += 20;
            be.beAttack=true;
        }
    }
    public void SpecialSkill(){
        if(horde==1 && map.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=15;map.field[3][3]=' ';
            }
        }
        if(horde==1 && map.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=15;map.field[3][7]=' ';
            }
        }

        if(horde==2 && map.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=15;map.field[7][3]=' ';
            }
        }
        if(horde==2 && map.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp+=15;map.field[7][7] = ' ';
            }
        }
    }

    @Override
    public synchronized void run() {
        if(map.flag==false){
            delay();
        }
        map.flag=true;
        delay();
        for (int i = 0; i < 3; i++) {
           Moveleft();
           delay();
           Movedown();
        }
        notifyAll();
    }
}
