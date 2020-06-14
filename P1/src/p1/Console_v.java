/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

/**
 *
 * package p1;

import java.io.*;
import java.util.*;

/**
 *
 * @author Carlos.torra
 */
import java.io.*;
import java.util.*;
public class Console_v implements Observer{
   
    static final String START_ANS = "\033[1~";  
    static final String END_ANS = "\033[4~";
    //static final String SPACE_ANS = "\033[ ";
    static final String BLANK_SPACE_ANS = "\033[@";
    static final String RIGHT_ANS = "\033[C";
    static final String LEFT_ANS= "\033[D";
    static final String INSERT_ANS = "\033[4h";
    static final String BACKSPACE_ANS = "\b";
    static final String DELETE_ANS = "\033[P";
   
    private Line_m linia;
   //Seqüències
    static final int SEC_BACKSPACE = 127;
    static final int SEC_SCAPE = 2000;
    static final int SEC_START = 2000;
    static final int SEC_RIGHT = 2001;
    static final int SEC_LEFT = 2002;
    static final int SEC_END = 2003;
    static final int SEC_INSERT = 2004;
    static final int SEC_DELETE = 2005;
    static final int CHARACTER = 2006;
    static final int FINAL = 2007;
public Console_v(Line_m linea){
        this.setRaw();
        this.linia=linea;
    }

 
public void update(Observable obs, Object obj){
        int value = (int)obj;
        switch(value){
            case CHARACTER:
                boolean bol = this.linia.getInsert();
                this.PrintCharacter(bol, this.linia.getChar());
                break;
           
            case SEC_BACKSPACE:
                this.Backspace();
                break;
            case SEC_START:
                this.updateCursor(this.linia.getCursor());
                break;
            case SEC_RIGHT:
                this.Right();
                break;
            case SEC_LEFT:
                this.Left();
                break;
            case SEC_END:
                this.updateCursor(this.linia.getCursor());
                break;
            case SEC_INSERT:
                this.Insert();
                break;
            case SEC_DELETE:
                this.Delete();
                break;
            case FINAL:
                this.unsetRaw();
                break;
            default:
                break;
        }
    }
public void setRaw(){
        String cmd[] = {"/bin/sh", "-c", "stty -echo raw </dev/tty"};
        try{
            Runtime.getRuntime().exec(cmd);
        }
        catch(IOException e){
            e.printStackTrace();
        }
 }
public void unsetRaw(){
        String cmd[] = {"/bin/sh", "-c", "stty -echo cooked </dev/tty"};
        try{
            Runtime.getRuntime().exec(cmd);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
public void updateCursor(int Pos){
        int aux = Pos + 1;
        System.out.print("\033["+aux+"G");
}
public void PrintCharacter(boolean ins, char car){
    if(ins){
        System.out.print(car);
    }else{
        System.out.print(BLANK_SPACE_ANS);
        System.out.print(car);
    }
}
public void Backspace(){
        System.out.print(DELETE_ANS);
}
public void Right(){
        System.out.print(RIGHT_ANS);
}
public void Left(){
        System.out.print(LEFT_ANS);
}
public void Insert(){
        System.out.print(INSERT_ANS);
}
public void Delete(){
        System.out.print(DELETE_ANS);
}
}