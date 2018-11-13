package Valor;

public class HeroF extends Character implements skill{
    public HeroF(Map m){
        name='F';range=2;Name="Fairchild";
        x=10;  y=1; map=m;
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
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=2;Exp+=5;range=3;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(){
        if(MP>=20) MP-=20;
        if(getDistance(be)<=range) {
            be.HP -= 25;
            Exp += 25;
            be.beAttack=true;
        }
    }
    public void SpecialSkill(){
        if(horde==1 && map.field[3][3]=='+'){
            if((x==2&&y==3)||(x==3&&y==4)||(x==4&&y==3)){
                Exp+=7;map.field[3][3]='·';
            }
        }
        if(horde==1 && map.field[3][7]=='+'){
            if((x==2&&y==7)||(x==3&&y==6)||(x==4&&y==7)){
                Exp+=7;map.field[3][7]='·';
            }
        }

        if(horde==2 && map.field[7][3]=='*'){
            if((x==6&&y==3)||(x==7&&y==4)||(x==8&&y==3)){
                Exp+=7;map.field[7][3]='·';
            }
        }
        if(horde==2 && map.field[7][7]=='*') {
            if ((x==6&&y==7)||(x==7&&y==6)||(x==8&&y==7)){
                Exp+=7;map.field[7][7] = '·';
            }
        }
    }
}