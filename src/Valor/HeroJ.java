package Valor;

public class HeroJ extends Character implements skill{
    public HeroJ(Map m){
        name='J';range=4;Name="Jacobson";
        x=10;  y=9; map=m;
    }
    public void SetLocation(){

    }
    public void ActiveSkill(){
        if(Exp>=10){
            MP+=25;  HP+=10; Exp-=10;
        }
    }
    public void PassiveSkill(){
        if(beAttack==true) {
            HP+=5;Exp+=5;
            System.out.println("Passive skill launched!");
        }
    }
    public void Attacking(){
        if(MP>=30) MP-=30;
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
