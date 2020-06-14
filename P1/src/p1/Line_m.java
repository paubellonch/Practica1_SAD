/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import java.util.ArrayList;
import java.util.Observable;
/**
 *
 * @author carlos.torra
 */
public class Line_m extends Observable {
    Line_m linea;
    static final int RIGHT=2001;
    static final int LEFT=2002;
    static final int START=2000;
    static final int INSERT=2004;
    static final int DELETE=2005;
    static final int BACKSPACE=127;
    static final int ESC=2000;
    static final int END=2003;
    static final int CHARACTER=2006;
    static final int FINAL=2007;
    int Cursor,Length;
    boolean Insert;
    ArrayList<Integer> Bufferline;
    char car;
   
   public Line_m(){
        this.Cursor=0;
        this.Length=0;
        this.Insert=false;
        this.Bufferline=new ArrayList<>();
       
    }
    public void enter(){
        this.setChanged();
        this.notifyObservers(FINAL);
    }
    public char getChar(){
        return this.car;
    }
    public int getCursor(){
        return this.Cursor;
    }
    public boolean getInsert(){
        return this.Insert;
    }
    public void Right(){
        if(this.Cursor<this.Bufferline.size()){
            this.Cursor++;
        }
        this.setChanged();
        this.notifyObservers(RIGHT);
       
    }
   public void Left(){
        if(this.Cursor>0){
            this.Cursor--;
        }
        this.setChanged();
        this.notifyObservers(LEFT);
       
    }
    public void Start(){
        this.Cursor=0;
        this.setChanged();
        this.notifyObservers(START);
     
    }
    public void End(){
        this.Cursor=this.Bufferline.size();
        this.setChanged();
        this.notifyObservers(END);
    }
    public void BackSpace(){
        if((this.Cursor>0)&&(this.Cursor<=this.Bufferline.size())&&(this.Bufferline.size()>0)){
            this.Bufferline.remove(this.Cursor-1);
            this.Left();  
            this.Length--;
        }
        this.setChanged();
        this.notifyObservers(BACKSPACE);
    }
    public void Delete(){
       if(this.Cursor<this.Bufferline.size()){
           this.Bufferline.remove(this.Cursor);
           this.Length--;
       }
       this.setChanged();
       this.notifyObservers(DELETE);
     }
    public String getString(){
        String str="";
        int i=0;
        while(i<this.Bufferline.size()){
            int b=this.Bufferline.get(i);
            str= str +(char)b;
            i++;
        }
        return str;
       
    }
   public void removeChar(int pos){
        this.Bufferline.remove(pos);
    }
   public void addChar(int pos){
        if(this.Insert){
            if(this.Cursor<Bufferline.size()){
                this.Bufferline.set(this.Cursor,pos);
            }else{
                this.Bufferline.add(this.Cursor, pos);
            }
        }else{
            this.Length=this.Bufferline.size();
            if(this.Cursor<this.Length){
                for(int i=Length;i>this.Cursor;i--){
                    this.Bufferline.add(i,this.Bufferline.get(i-1));
                    this.Bufferline.remove(i-1);
                }
            }
            this.Bufferline.add(this.Cursor,pos);
        }
        this.car=(char)pos;
        this.Cursor++;
        this.setChanged();
        this.notifyObservers(CHARACTER);
    }
   public void InsertConverter(){
        this.Insert=!this.Insert;
        this.setChanged();
        this.notifyObservers(INSERT);
   
}

}