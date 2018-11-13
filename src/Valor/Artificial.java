package Valor;

public class Artificial implements Runnable{
    public Map map;
    public Character active,passive;
    public double []dis=new double[45];
    public Artificial(Map m){map=m;}
    public Character []role=new Character[10];
    public void SetAI(HeroA A,HeroB B,HeroC C,HeroD D,HeroE E){
        role[0]=A; role[1]=B; role[2]=C; role[3]=D; role[4]=E;
    }
    public void SetMan(HeroF F,HeroG G,HeroH H,HeroI I,HeroJ J){
        role[5]=F; role[6]=G; role[7]=H; role[8]=I; role[9]=J;
    }
    public void FieldReset(){
        for(int i=0;i<11;i++)
            for(int j=0;j<11;j++)
                map.field[i][j]='·';
        for (int i = 0; i < 10; i++) {
            map.field[role[i].x][role[i].y]=role[i].name;
        }
        for(int i=2;i<=4;i++) {
            map.field[i][2]='$';
            map.field[i][8]='$';
        }
        for(int i=6;i<=8;i++) {
            map.field[i][2]='%';
            map.field[i][8]='%';
        }
        map.field[3][3]='+';
        map.field[3][7]='+';
        map.field[7][3]='*';
        map.field[7][7]='*';
    }
    public void GetEnemy(){
        int d=0;  double min=20;
        for (int i = 0; i < 5; i++) {
            for (int j = 5; j < 10; j++) {
                {
                    dis[d]=role[i].getDistance(role[j]);
                    if(dis[d]<min){
                        min=dis[d];
                        active=role[i];
                        passive=role[j];
                    }
                }
            }
        }
    }
    public void Run(Character p){
        if(p.x-1>=0){
            if(map.field[p.x-1][p.y]!='·'){
                p.Movedown(); p.Movedown();
            }
        }
        if(p.x+1<11){
            if(map.field[p.x+1][p.y]!='·'){
                p.Moveup(); p.Moveup();
            }
        }
        if(p.y+1<11){
            if(map.field[p.x][p.y+1]!='·'){
                p.Moveleft(); p.Moveleft();
            }
        }
        if(p.y-1>=0){
            if(map.field[p.x][p.y-1]!='·'){
                p.Moveright(); p.Moveright();
            }
        }
    }
    public void Runaway(){
        for (int i = 0; i < 5; i++){
            if(role[i].HP<=50){
                Run(role[i]);
            }
        }
    }
    public void run(){
        role[2].BeAttack(role[7]);
        role[2].CalculatePath();

        for (int i = 0; i < 3; i++) {
            role[0].Movedown();
            role[0].delay();
            role[0].Moveright();
        }

        role[3].BeAttack(role[8]);
        role[3].CalculatePath();

        for (int i = 0; i < 3; i++) {
            role[1].Movedown();
            role[1].delay();
            role[1].Moveright();
        }
        for (int i = 0; i < 3; i++) {
            role[2].Moveright();
            role[2].delay();
            role[2].Movedown();
        }
        GetEnemy();
        Runaway();
    }
}
