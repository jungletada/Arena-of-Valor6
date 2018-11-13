package Valor;

public class HeroI extends Character implements skill{
    public HeroI(Map m){
        name='I';range=1;Name="Imogene";
        x=10;  y=7;  map=m;
    }
    public void SetLocation() {
    }
    public void ActiveSkill(){
        if(Exp>=25){
            MP+=20;  HP+=10; Exp-=25;
            if(be.MP>10)
                be.MP-=10;
            else
                be.MP=0;
            be.range=2;
        }
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=2;Exp+=2;MP=40;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(){
        if(MP>=10) MP-=10;
        if(getDistance(be)<=range) {
            be.HP -= 20;
            Exp += 20;
            be.beAttack=true;
        }
    }
    public void SpecialSkill(){
        if(horde==1 && map.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=15;map.field[3][3]='·';
            }
        }
        if(horde==1 && map.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=15;map.field[3][7]='·';
            }
        }

        if(horde==2 && map.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=15;map.field[7][3]='·';
            }
        }
        if(horde==2 && map.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp+=15;map.field[7][7] = '·';
            }
        }
    }
}
