/*
Gramatica para el Robot:
Autor: Alejandro Arango
Modificado por: Silvia Takahashi
Modificado por: Juan Pablo Morales.
Agregadas producciones para manejar globos
Modificado por Silvia Takahashi


 **/


options 
{
  
	LOOKAHEAD=1; 
	IGNORE_CASE=true;
	STATIC = false;
	
}
   

PARSER_BEGIN(Robot)

package uniandes.lym.robot.control;

import uniandes.lym.robot.kernel.*;
import uniandes.lym.robot.view.Console;
 
import java.awt.Point;
import java.io.*;
import java.util.Vector;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Robot 
{


	private RobotWorldDec world;
	
	
	void setWorld(RobotWorld w) {
		world = (RobotWorldDec) w;	
	}

	String salida=new String();
}
PARSER_END(Robot)

SKIP:
{
	" "
	|	"\r"
	|	"\t"
	| "\n"
}

TOKEN: /* Nombres de Comandos */
{

			<MOV:  "Mov">
		| 	<RIGHT: "RIGHT">
		| 	<PUT: "Put">
		| 	<PICK: "Pick">
		|  < POP: "Pop" >
		|  <GO: "GO" >
        |  < HOP:  "HOP" >		

}

TOKEN:
{
		<BALLOONS:  "BALLOONS" >
		|	<CHIPS:     "CHIPS"  >	

}



TOKEN :
{
		<NUM: (<DIGIT>)+ >
		|  	<#DIGIT: ["0"-"9"] >
        
}



	//boolean command(uniandes.lym.robot.view.Console sistema) :
	boolean command(Console sistema):
	{	
		
		int x,y;
		salida=new String();	
	}

	
	{
		(
		  (
		   <RIGHT> "(" ")" {world.turnRight();salida = "Command: Turnright";}
		| 	<MOV>  "(" x=num() ")" {world.moveForward(x,false);salida = "Command: Moveforward ";}  
		| 	<HOP>  "(" x=num() ")" {world.moveForward(x,true);salida = "Command:Jumpforward ";}
		| 	<GO>  "(" x=num() "," y=num()")" {world.setPostion(x,y);salida = "Command:GO ";}  
		|  <PUT> "("  put() ")"					  			
		|  <PICK> "(" get()  ")"	
	    |  < POP > "(" x=num() ")" {world.popBalloons(x); salida = "Comando:  Pop";}
		) ";" 

		{
		    try {
	    			 Thread.sleep(900);
	    	    } catch (InterruptedException e) {
	    			        System.err.format("IOException: %s%n", e);
	    		    }
	    			 
			sistema.printOutput(salida);
			return true;
		})+

    	| <EOF> {return false;} 
	}

	void put() :
	{
		int f=1;	
	}
	{
		( <CHIPS>    "," f=num() {world.putChips(f); salida = "Command:  Put Chips"; })
		|  	  ( <BALLOONS>   "," f=num() {world.putBalloons(f); salida = "Command:  Put Balloons";})	 

	}

	void get() :
	{
		int f=1;	
	}
	{
		( <CHIPS>   "," f=num() {world.pickChips(f);salida = "Command:  Pick chips";})
		|  	  ( <BALLOONS>   "," f=num() {world.grabBalloons(f);salida="Command:  Pick balloons";})	 

	}

	
	

	/**
	 * Unsigned decimal number
	 * @return the corresponding value of the string
	 * @error  corresponding value is too large
	 */

	int num() throws Error:
	{	
		int total=1;
	}
	{
		<NUM>
		{
			try 
			{
				total = Integer.parseInt(token.image);
			} 
			catch (NumberFormatException ee) 
			{
				throw new Error("Number out of bounds: "+token.image+" !!");
			}
			return total;
		}
	}
