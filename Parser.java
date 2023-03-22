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
import java.util.HashMap;

@SuppressWarnings("serial")
public class Robot 
{


private RobotWorldDec world;
private HashMap<String, Integer> variables= new HashMap<String, Integer>();

void setWorld(RobotWorld w) {
world = (RobotWorldDec) w;
}

String salida=new String();
}
PARSER_END(Robot)

SKIP:
{
" "
|"\r"
|"\t"
| "\n"
}

TOKEN: /* Nombres de Comandos */
{
<GOTO:"goto">
|<MOVEINDIR: "moveInDir">
|<MOVE: "move">
|<TURN: "turn">
|<FACE: "face">
| <PUT: "put">
|<PICK: "pick">
|<MOVETOTHE: "moveToThe">
|<JUMPTOTHE: "jumpToThe">
|<JUMPINDIR: "jumpInDir">
|<NOP: "nop">


}

TOKEN:
{
<NORTH: "north">
|<SOUTH: "south">
|<WEST: "west">
|<EAST: "east">
| <BALLONS: "Ballons">
|<CHIPS: "Chips">

}



TOKEN :
{
<NUM: (<DIGIT>)+ >
|<#DIGIT: ["0"-"9"] >
|<LETTER: ["a"-"z"]>
|<NAME:(<LETTER>)+>

}



//boolean command(uniandes.lym.robot.view.Console sistema) :
boolean command(Console sistema):
{

int x,y;
salida=new String();
}


{
	comandos () {return true;}
|
 <EOF> {return false;} 
}
// repeat: number instrucciones+
void repeat():
{
	ArrayList <String> instrucciones= new ArrayList<String>();
}
{
	<REPEAT> var= varOrNum()":"
	(
		comandos(false, ArrayList <String> instrucciones)
	)+
}


void comandos(boolean reproducir):
{
int var1;
int var2;
String dir;
String word;
String object;
}
{
(
<GOTO> ":" var1=varOrNum()"," var2=varOrNum(){
	if (reproducir){

	world.setPostion(var1,var2); word = "Command: goTo";
}
	if (instrucciones !=null){
		instrucciones.add("goto")
		instrucciones.add(Integer.toString(var1));
		instrucicones.add(Integer.toString(var2))
	}
}
|<MOVE> ":" var1=varOrNum(){
	for (i==0,i<var1,i++){
	if (getFacing()=="north"){
		world.up();word="Command: move";
	}
	if (getFacing()=="south"){
		world.down;word="Command: move;"
	}
	if (getFacing()=="east"){
		world.right();word="Command: move";
	}
	if (getFacing()=="west"){
		world.left(),word="Command: move";
	}
}	
}
|<TURN> ":" (<NORTH>|<SOUTH>|<EAST>|<WEST>){world.turnRight(); word = "Command: turn" ;}
|<FACE> ":" dir=(<NORTH>|<SOUTH>|<EAST>|<WEST>){
	facing(dir); word="Command: face";
}

|<PUT>":" var= varOrNum()","objeto=(<Balloons>|<Chips>){
	if (objeto=="Balloons"){
		for (i==0; i<var;i++){
			world.putBalloon(); word= "Command= put balloon";
	}
}
	if (objeto== "Chips"){
		for (i==0,i<var,i++){
			world.putChip(); word="Command= put chip";
		}
	}
}
|<PICK>":" var= varOrNum()","(<Balloons>|<Chips>){
	if (objeto=="Balloons"){
		for (i==0; i<var;i++){
			world.pickupBalloon(); word= "Command= pick balloon";
	}
}
	if (objeto== "Chips"){
		for (i==0,i<var,i++){
			world.pickupChip(); word="Command= pick chip";
		}
	}
}
|<MOVEINDIR>":"var= varOrNum()","dir=(<north>|<south>|<west>|<east>){
	facing(dir);world.;
	for (i==0,i<var1,i++){
		if (dir()=="north"){
			world.up();word="Command: moveInDir";
		}
		if (dir()=="south"){
			world.down;word="Command: moveInDir;"
		}
		if (dir()=="east"){
			world.right();word="Command: moveInDir";
		}
		if (dir()=="west"){
			world.left(),word="Command: moveInDir";
		}
	}	
}

|<JUMPTOTHE>":"var= varOrNum()","dir=(<front>|<right>|<left>|<back>){
	position= world.getPosition();
	if (dir=="front"){
		position[1]-=var
		world.setPostion(position);word="Command: jumpToThe";
	}
	if (dir=="back"){
		position[1]+=var
		world.setPostion(position);word="Command: jumpToThe";
	}
	if (dir=="right"){
		position[0]+=var
		world.setPostion(position);word="Command: jumpToThe";
	}
	if (dir=="front"){
		position[0]-=var
		world.setPostion(position);word="Command: jumpToThe";
	}
}

|<JUMPINDIR>":"var= varOrNum()","dir=(<north>|<south>|<west>|<east>){
	facing(dir);word="Command: face";
	for (i==0,i<var1,i++){
		if (getFacing()=="north"){
			world.up();word="Command: move";
		}
		if (getFacing()=="south"){
			world.down;word="Command: move;"
		}
		if (getFacing()=="east"){
			world.right();word="Command: move";
		}
		if (getFacing()=="west"){
			world.left(),word="Command: move";
		}
	}	

	}

|<NOP>":"{word="Command: nop"}

)
}
int varOrNum() throws Error:
{
int total=0;
String variable;
}
{
(<NAME> {
	variable= tokem.image;
	if (variables.get(variable)!=null){
		total= variables.get(variable);
	else {
		throw new Error ("La variable no existe");
	}
	}
	return total;}

|<NUM> {try
{
total = Integer.parseInt(token.image);
} 
catch (NumberFormatException ee) 
{
throw new Error("Number out of bounds: "+token.image+" !!");
}
return total}
)

}

void facing(dir){
	if (dir="north"){
		world.facingNorth(); word="Command: face";
	}
	if (dir="south"){
		world.facingSouth(); word="Command: face";
	}
	if (dir="east"){
		world.facingEast(); word="Command: face";
	}
	if (dir="west"){
		world.facingWest(); word="Command: face";
	}

}

/**
 * Unsigned decimal number
 * @return the corresponding value of the string
 * @error  corresponding value is too large
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