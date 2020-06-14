/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author carlos.torra
 */
public class EditableBufferedReader extends BufferedReader{
    Line_m linea;
    Console_v consola;
    static final int ENTER=13;
    static final int RIGHT=67;
    static final int LEFT=68;
    static final int START=72;
    static final int INSERT=50;
    static final int DELETE=51;
    static final int BACKSPACE=127;
    static final int ESC=27;
    static final int CORCHETE=91;
    static final int VIRGULILLA=126;
    static final int END=0;
    //DEFININIM LES SEQÜÈNCIEs
    static final int SEC_BACKSPACE = 127;
    static final int SEC_INSERT = 2004;
    static final int SEC_DELETE = 2005;
    static final int SEC_SCAPE = 2000;
    static final int SEC_START = 2000;
    static final int SEC_RIGHT = 2001;
    static final int SEC_LEFT = 2002;
    static final int SEC_END = 2003;
   
    public EditableBufferedReader(Reader in){
        super(in);
        this.linea=new Line_m();
        this.consola=new Console_v(this.linea);
        this.linea.addObserver(this.consola);
       
       
    }
    public int read() throws IOException{
        int car=0;
        car=super.read();
        if(car==ESC){
            car=super.read();
            if(car==CORCHETE){
                car=this.read();
                switch(car){
                    case START:
                        return SEC_START;
                    case LEFT:
                        return SEC_LEFT;
                    case RIGHT:
                        return SEC_RIGHT;
                    case END:
                        return SEC_END;
                    case INSERT:
                        car=super.read();
                        if(car==VIRGULILLA){
                            return SEC_INSERT;
                        }
                        return -1;
                    case DELETE:
                        car=super.read();
                        if(car==VIRGULILLA){
                            return SEC_DELETE;
                        }
                        return -1;
                    default:
                        return -1;
            }
            }
        }else if(car==BACKSPACE){
            return BACKSPACE;
        }else{
            return car;
        }
       return -1;
    }
    public String readLine() throws IOException{
        int car=0;
        do{
             car=this.read();
           if(car>=SEC_SCAPE){
            switch(car){
                case SEC_RIGHT:
                    this.linea.Right();
                    break;
                case SEC_LEFT:
                    this.linea.Left();
                    break;
                case SEC_START:
                    this.linea.Start();
                    break;
                case SEC_INSERT:
                    this.linea.InsertConverter();
                    break;
                case SEC_DELETE:
                    this.linea.Delete();
                    break;
                case SEC_BACKSPACE:
                    this.linea.BackSpace();
                    break;        
           }
           }else if(car!=ENTER){
               this.linea.addChar(car);
           
           }else if(car==SEC_BACKSPACE){
                this.linea.BackSpace();
           }
        }while(car!=ENTER);
        this.linea.enter();
        return linea.getString();
    }
}