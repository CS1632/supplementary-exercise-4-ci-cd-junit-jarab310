package edu.pitt.cs;

import java.util.*;

public class CoffeeMakerQuestImpl implements CoffeeMakerQuest {

	private ArrayList<Room> roomList = new ArrayList<Room>();
	private Player gamePlayer = new Player();
	private Room locationRoom;
	private boolean gameEnd = false;

	/**
	 * Constructor. Rooms are laid out from south to north, such that the
	 * first room in rooms becomes the southernmost room and the last room becomes
	 * the northernmost room. Initially, the player is at the southernmost room.
	 * 
	 * @param player Player for this game
	 * @param rooms  List of rooms in this game
	 */
	CoffeeMakerQuestImpl(Player player, ArrayList<Room> rooms) {
		// TODO
		gamePlayer = player;
		for (int i = 0; i < rooms.size(); i++) {
			Room currRoom = rooms.get(i);
			roomList.add(currRoom);
		}
		locationRoom = roomList.get(0);
	}

	/**
	 * Whether the game is over. The game ends when the player drinks the coffee.
	 * 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isGameOver() {
		return gameEnd;
	}

	/**
	 * The method returns success if and only if: 1) Th southernmost room has a
	 * north door only, 2) The northernmost room has a south door only, and 3) The
	 * rooms in the middle have both north and south doors. If there is only one
	 * room, there should be no doors.
	 * 
	 * @return true if check successful, false otherwise
	 */
	public boolean areDoorsPlacedCorrectly() {
		// TODO
		if (roomList.get(0).getSouthDoor() == null && roomList.get(0).getNorthDoor() != null) {
			if (roomList.get(roomList.size()-1).getNorthDoor() == null && roomList.get(roomList.size()-1).getSouthDoor() != null) {
				for (int i = 1; i < roomList.size()-1; i++) {
					if (roomList.get(i).getNorthDoor() == null || roomList.get(i).getSouthDoor() == null) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
		
	}

	/**
	 * Checks whether each room has a unique adjective and furnishing.
	 * 
	 * @return true if check successful, false otherwise
	 */

	public boolean areRoomsUnique() {
		// TODO
	/*	Room currRoom = roomList.get(0);
		for (int i = rooms; i < roomList.size(); i++) {
			if (roomList.get(i).getAdjective() == currRoom.getAdjective() || roomList.get(i).getFurnishing() == currRoom.getFurnishing()) {
				return false;
			}
			currRoom = roomList.get(i);
		}*/
		Room currRoom;
		for(int currRoomIndex = 0 ; currRoomIndex < roomList.size() - 1 ; currRoomIndex++){
			currRoom = roomList.get(currRoomIndex);
			for(int comparedRoom = currRoomIndex+1 ; comparedRoom < roomList.size() ; comparedRoom++){
				if(roomList.get(comparedRoom).getAdjective() == currRoom.getAdjective() || 
				roomList.get(comparedRoom).getFurnishing() == currRoom.getFurnishing()){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns the room the player is currently in. If location of player has not
	 * yet been initialized with setCurrentRoom, returns null.
	 * 
	 * @return room player is in, or null if not yet initialized
	 */
	public Room getCurrentRoom() {
		// TODO
		for (int i = 0; i < roomList.size(); i++) {
			if (locationRoom == roomList.get(i)) {
				return roomList.get(i);
			}
		}
		return null;
	}

	/**
	 * Set the current location of the player. If room does not exist in the game,
	 * then the location of the player does not change and false is returned.
	 * 
	 * @param room the room to set as the player location
	 * @return true if successful, false otherwise
	 */
	public boolean setCurrentRoom(Room room) {
		// TODO
		if (roomList.contains(room)) {
			locationRoom = room;
			return true;
		}
		return false;
	}

	/**
	 * Get the instructions string command prompt. It returns the following prompt:
	 * " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 * 
	 * @return comamnd prompt string
	 */
	public String getInstructionsString() {
		// TODO
		return " INSTRUCTIONS (N,S,L,I,D,H) > ";
	}

	/**
	 * Processes the user command given in String cmd and returns the response
	 * string. For the list of commands, please see the Coffee Maker Quest
	 * requirements documentation (note that commands can be both upper-case and
	 * lower-case). For the response strings, observe the response strings printed
	 * by coffeemaker.jar. The "N" and "S" commands potentially change the location
	 * of the player. The "L" command potentially adds an item to the player
	 * inventory. The "D" command drinks the coffee and ends the game. Make
	 * sure you use Player.getInventoryString() whenever you need to display
	 * the inventory.
	 * 
	 * @param cmd the user command
	 * @return response string for the command
	 */
	public String processCommand(String cmd) {
		// TODO
		Room currRoom = roomList.get(0);
		int index = 0;
		for (int i = 0; i < roomList.size(); i++) {
			if (roomList.get(i) == locationRoom) {
				locationRoom = roomList.get(i);
				currRoom = roomList.get(i);
				index = i;
				break;
			}
		}

		if (cmd == "N" || cmd == "n") {
			if (index < roomList.size()-1) {
				locationRoom = roomList.get(index+1);
				currRoom = roomList.get(index+1);
				return "";
			}
			else {
				return "A door in that direction does not exist.\n";
			}
		}
		else if (cmd == "S" || cmd == "s") {
			if (index > 0) {
				locationRoom = roomList.get(index-1);
				currRoom = roomList.get(index-1);
				return "";
			}
			else {
				return "A door in that direction does not exist.\n";
			}
		}
		else if (cmd == "L" || cmd == "l") {
			if (currRoom.getItem() != Item.NONE) {
				gamePlayer.addItem(currRoom.getItem());
				if (currRoom.getItem() == Item.COFFEE) {
					return "There might be something here...\nYou found some caffeinated coffee!\n";
				}
				else if (currRoom.getItem() == Item.CREAM) {
					return "There might be something here...\nYou found some creamy cream!\n";
				}
				else if (currRoom.getItem() == Item.SUGAR) {
					return "There might be something here...\nYou found some sweet sugar!\n";
				}
			}
			else {
				return "You don't see anything out of the ordinary.\n";
			}
		}
		else if (cmd == "I" || cmd == "i") {
			return gamePlayer.getInventoryString();
		}
		else if (cmd == "D" || cmd == "d") {
			StringBuilder drinkString = new StringBuilder();
			drinkString.append(startString());
			if (gamePlayer.checkCoffee() == true && gamePlayer.checkCream() == true && gamePlayer.checkSugar() == true) {
				gameEnd = true;
				drinkString.append("You drink the beverage and are ready to study!\nYou win!\n");
				return drinkString.toString();
			}
			else if (gamePlayer.checkCoffee() == false && gamePlayer.checkCream() == false && gamePlayer.checkSugar() == false){
				gameEnd = true;
				drinkString.append("You drink thin air and can only dream of coffee. You cannot study.\nYou lose!\n");
				return drinkString.toString();
			}
			drinkString.append("You refuse to drink this half-made sludge. You cannot study.\nYou lose!\n");
			gameEnd = true;
			return drinkString.toString();
		}
		else if (cmd == "H" || cmd == "h") {
			return "N - Go north\nS - Go south\nL - Look and collect any items in the room\nI - Show inventory of items collected\nD - Drink coffee made from items in inventory\n";
		}
		return "What?\n";
	}

	private String startString(){
		StringBuilder startOfString = new StringBuilder();
		startOfString.append(gamePlayer.getInventoryString());
		startOfString.append("\n");
		return startOfString.toString();
	}
	

}
