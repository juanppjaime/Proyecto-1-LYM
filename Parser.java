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
public class Robot {
    private RobotWorldDec world;
    private HashMap<String, Integer> variables = new HashMap<String, Integer>();

    void setWorld(RobotWorld w) {
        world = (RobotWorldDec) w;
    }

    String output = "";

    public void executeProgram(ArrayList<String> commands) {
        for (int i = 0; i < commands.size(); i++) {
            String command = commands.get(i);
            if (command.equals("goto")) {
                int x = Integer.parseInt(commands.get(i + 1));
                int y = Integer.parseInt(commands.get(i + 2));
                world.setPosition(new Point(x, y));
                i += 2;
                output += "Command: goto\n";
            } else if (command.equals("move")) {
                int x = Integer.parseInt(commands.get(i + 1));
                move(x);
                i += 1;
            } else if (command.equals("turn")) {
                String direction = commands.get(i + 1);
                turn(direction);
                i += 1;
                output += "Command: turn\n";
            } else if (command.equals("face")) {
                String direction = commands.get(i + 1);
                face(direction);
                i += 1;
                output += "Command: face\n";
            } else if (command.equals("put")) {
                int count = Integer.parseInt(commands.get(i + 1));
                String item = commands.get(i + 2);
                put(count, item);
                i += 2;
            } else if (command.equals("pick")) {
                int count = Integer.parseInt(commands.get(i + 1));
                String item = commands.get(i + 2);
                pick(count, item);
                i += 2;
            } else if (command.equals("movetothe")) {
                String item = commands.get(i + 1);
                moveToThe(item);
                i += 1;
            } else if (command.equals("moveindir")) {
                int count = Integer.parseInt(commands.get(i + 1));
                String direction = commands.get(i + 2);
                moveInDir(count, direction);
                i += 2;
            } else if (command.equals("jumptothe")) {
                String item = commands.get(i + 1);
                jumpToThe(item);
                i += 1;
            } else if (command.equals("repeat")) {
                int count = Integer.parseInt(commands.get(i + 1));
                ArrayList<String> blockCommands = new ArrayList<String>();
                int j = i + 2;
                while (!commands.get(j).equals("endrepeat")) {
                    blockCommands.add(commands.get(j));
                    j++;
                }
                repeat(count, blockCommands);
                i = j;
            } else if (command.equals("if")) {
                String condition = commands.get(i + 1);
                ArrayList<String> block1Commands = new ArrayList<String>();
                ArrayList<String> block2Commands = new ArrayList<String>();
                int j = i + 2;
				while (!commands.get(j).empty() && !found) {
				} else {
					// Ejecutar Block2
					leerComandos(block2);
					}
					}
					else if(token.image.equalsIgnoreCase("while")){
					// Loop: while: condition do: Block
					Match("(");
					boolean condition = Condition();
					Match(")");
					Match("do");
					ArrayList<String> block = Block();
					while(condition){
					leerComandos(block);
					condition = Condition();
					}
					}
					else if(token.image.equalsIgnoreCase("repeat")){
					// RepeatTimes: repeat: n Block
					Match(":");
					int n = Integer.parseInt(token.image);
					Match(TokenConstants.NUMERO);
					ArrayList<String> block = Block();
					for(int i = 0; i < n; i++){
					leerComandos(block);
					}
					}
					}
					
					public boolean Condition(){
					// Condition: comparador | condicional
					// comparador: variable operador variable
					// condicional: (comparador operador comparador)
					boolean res = false;
					if(token.image.equalsIgnoreCase("(")){
					Match("(");
					String var1 = token.image;
					Match(TokenConstants.IDENTIFICADOR);
					String op = token.image;
					Match(TokenConstants.OPERADOR_COMPARACION);
					String var2 = token.image;
					Match(TokenConstants.IDENTIFICADOR);
					Match(")");
					res = comparar(var1, op, var2);
					} else {
					String var1 = token.image;
					Match(TokenConstants.IDENTIFICADOR);
					String op = token.image;
					Match(TokenConstants.OPERADOR_COMPARACION);
					String var2 = token.image;
					Match(TokenConstants.IDENTIFICADOR);
					res = comparar(var1, op, var2);
					}
					return res;
					}
					
public boolean comparar(String var1, String op, String var2){
// Compara dos variables segun el operador
int val1 = getVariableValue(var1);
int val2 = getVariableValue(var2);
if(op.equals(">")){
return val1 > val2;
} else if(op.equals(">=")){
return val1 >= val2;
} else if(op.equals("<")){
return val1 < val2;
} else if(op.equals("<=")){
return val1 <= val2;
} else if(op.equals("==")){
return val1 == val2;
} else {
return val1 != val2;
}
}
					
public int getVariableValue(String var){
// Retorna el valor de una variable
if(variables.containsKey(var)){
return variables.get(var);
} else {
return 0;
}
}
					
public void setVariableValue(String var, int val){
// Asigna un valor a una variable
variables.put(var, val);
}
					
}
void setWorld(RobotWorld w) {
world = (RobotWorldDec) w;
}

String salida=new String();
public void leerComandos(ArrayList <String> comandos){
int i=0
while (i<comandos.size()){
if (comandos.get(i).equals("goto"))
self.goto(Integer.parseInt(comandos.get(i+1),Integer.parseInt(comandos.get(i+2))))

}
i=0
while (i<comandos.size()){
if (comandos.get(i).equals("move"))
self.move(Integer.parseInt(comandos.get(i+1)))
}
}
i=0
while (i<comandos.size()){
if (comandos.get(i).equals("turn"))
self.turn(Integer.parseInt(comandos.get(i+1)))
}
i=0
while (i<comandos.size()){
if (comandos.get(i).equals("face"))
self.face(Integer.parseInt(comandos.get(i+1)))
}
i=0
while (i<comandos.size()){
if (comandos.get(i).equals("put"))
self.put(Integer.parseInt(comandos.get(i+1),Integer.parseInt(i+2)))
}
i=0
while (i<comandos.size()){
if (comandos.get(i).equals("pick"))
self.put(Integer.parseInt(comandos.get(i+1),Integer.parseInt(i+2)))
}
i=0
while (i<comandos.size()){
if (comandos.get(i).equals("movetothe"))
self.put(Integer.parseInt(comandos.get(i+1),Integer.parseInt(i+2)))
}
i=0
while (i<comandos.size()){
if (comandos.get(i).equals("moveindir"))
self.put(Integer.parseInt(comandos.get(i+1),Integer.parseInt(i+2)))
}
i=0
while (i<comandos.size()){
if (comandos.get(i).equals("jumptothe"))
self.put(Integer.parseInt(comandos.get(i+1),Integer.parseInt(i+2)))
}
i=0
while (i<comandos.size()){
if (comandos.get(i).equals("jumpindir"))
self.put(Integer.parseInt(comandos.get(i+1),Integer.parseInt(i+2)))
}
public void goto(int x, int y){
world.setPostion(x,y);
}
public void move(int x){
for (i==0,i<x,i++){
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
public void turn(String x){
	world.turnRight()
}
public void face(String x){
	facing(x);
}
public void put(int x, String y){
	if (y=="Balloons"){
		for (i==0; i<var;i++){
			world.putBalloon(); word= "Command= put balloon";
	}
}
	if (y== "Chips"){
		for (i==0,i<var,i++){
			world.putChip(); word="Command= put chip";
		}
	}
}public void pick(int x, int y){
	if (y=="Balloons"){
		for (i==0; i<var;i++){
			world.pickupBalloon(); word= "Command= pick balloon";
	}
}
	if (y== "Chips"){
		for (i==0,i<var,i++){
			world.pickupChip(); word="Command= pick chip";
		}
	}
}public void moveindir(int x, String dir){
	for (i==0,i<x,i++){
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
}public void jumptothe(int var1, String dir){
	if (dir=="front"){
		position[1]-=var1
		world.setPostion(position);word="Command: jumpToThe";
	}
	if (dir=="back"){
		position[1]+=var1
		world.setPostion(position);word="Command: jumpToThe";
	}
	if (dir=="right"){
		position[0]+=var1
		world.setPostion(position);word="Command: jumpToThe";
	}
	if (dir=="front"){
		position[0]-=var1
		world.setPostion(position);word="Command: jumpToThe";
	}
}public void jumpindir(int var1, String dir){
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
world.setPostion(x,y);
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
		instrucicones.add(Integer.toString(var2));
	}
}
|<MOVE> ":" var1=varOrNum(){
	if (reproducir){
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
if (instrucciones !=null){
	instrucciones.add("move")
	instrucciones.add(Integer.toString(var1));

}
}
|<TURN> ":" (<NORTH>|<SOUTH>|<EAST>|<WEST>){world.turnRight(); word = "Command: turn" ;}
|<FACE> ":" dir=(<NORTH>|<SOUTH>|<EAST>|<WEST>){
	if (reproducir){
	facing(dir); word="Command: face";
}
	if (instrucciones !=null){
		instrucciones.add("face")
		instrucciones.add(Integer.toString(dir));
}
}


|<PUT>":" var1= varOrNum()","object=(<Balloons>|<Chips>){
	if (reprodurcir){
	if (object=="Balloons"){
		for (i==0; i<var;i++){
			world.putBalloon(); word= "Command= put balloon";
	}
}
	if (object== "Chips"){
		for (i==0,i<var,i++){
			world.putChip(); word="Command= put chip";
		}
	}
}
	if (instrucciones !=null){
		instrucciones.add("put")
		instrucciones.add(Integer.toString(var1));
		instrucciones.add(Integer.toString(object));
}
}
|<PICK>":" var1= varOrNum()","object=(<Balloons>|<Chips>){
	if (reproducir){
	if (object=="Balloons"){
		for (i==0; i<var;i++){
			world.pickupBalloon(); word= "Command= pick balloon";
	}
}
	if (object== "Chips"){
		for (i==0,i<var,i++){
			world.pickupChip(); word="Command= pick chip";
		}
	}
}
if (instrucciones !=null){
	instrucciones.add("pick")
	instrucciones.add(Integer.toString(var1));
	instrucciones.add(Integer.toString(object));
}
}
|<MOVEINDIR>":"var1= varOrNum()","dir=(<north>|<south>|<west>|<east>){
	facing(dir);world.;
	if (reproducir){
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
	if (instrucciones !=null){
		instrucciones.add("goto")
		instrucciones.add(Integer.toString(var1));
		instrucciones.add(Integer.toString(dir));
}
}

|<JUMPTOTHE>":"var1= varOrNum()","dir=(<front>|<right>|<left>|<back>){
	position= world.getPosition();
	if (reproducir){
	if (dir=="front"){
		position[1]-=var1
		world.setPostion(position);word="Command: jumpToThe";
	}
	if (dir=="back"){
		position[1]+=var1
		world.setPostion(position);word="Command: jumpToThe";
	}
	if (dir=="right"){
		position[0]+=var1
		world.setPostion(position);word="Command: jumpToThe";
	}
	if (dir=="front"){
		position[0]-=var1
		world.setPostion(position);word="Command: jumpToThe";
	}
}
	if (instrucciones !=null){
		instrucciones.add("goto")
		instrucciones.add(Integer.toString(var1));
		instrucciones.add(Integer.toString(dir));
}
}

|<JUMPINDIR>":"var1= varOrNum()","dir=(<north>|<south>|<west>|<east>){
	facing(dir);word="Command: face";
	if (reproducir){
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
	if (instrucciones !=null){
		instrucciones.add("goto")
		instrucciones.add(Integer.toString(var1));
		instrucciones.add(Integer.toString(dir));
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

public void facing(dir){
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