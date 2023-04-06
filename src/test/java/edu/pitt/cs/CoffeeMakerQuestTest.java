package edu.pitt.cs;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.*;
import org.mockito.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class CoffeeMakerQuestTest {

	CoffeeMakerQuest cmq;
	Player player;
	ArrayList<Room> rooms;

	@Before
	
	public void setup() {
		//Config.setBuggyCoffeeMakerQuest(true);
		// 0. Turn on bug injection for Player and Room.
		Config.setBuggyPlayer(true);
		Config.setBuggyRoom(true);

		// TODO: 1. Create a Player with no items (no coffee, no cream, no sugar)
		// and assign to player.
		player = Mockito.mock(Player.class);
		Mockito.when(player.checkCoffee()).thenReturn(false);
		Mockito.when(player.checkCream()).thenReturn(false);
		Mockito.when(player.checkSugar()).thenReturn(false);

		// TODO: 2. Create 6 rooms as specified in rooms.config and add to rooms list.
		rooms = new ArrayList<Room>();
		Room room1 = Mockito.mock(Room.class);
		Mockito.when(room1.getFurnishing()).thenReturn("Quaint sofa");
		Mockito.when(room1.getAdjective()).thenReturn("Small");
		Mockito.when(room1.getItem()).thenReturn(Item.CREAM);
		Mockito.when(room1.getNorthDoor()).thenReturn("Magenta");
		rooms.add(room1);

		Room room2 = Mockito.mock(Room.class);
		Mockito.when(room2.getFurnishing()).thenReturn("Sad record player");
		Mockito.when(room2.getAdjective()).thenReturn("Funny");
		Mockito.when(room2.getItem()).thenReturn(Item.NONE);
		Mockito.when(room2.getNorthDoor()).thenReturn("Beige");
		Mockito.when(room2.getSouthDoor()).thenReturn("Massive");
		rooms.add(room2);

		Room room3 = Mockito.mock(Room.class);
		Mockito.when(room3.getFurnishing()).thenReturn("Tight pizza");
		Mockito.when(room3.getAdjective()).thenReturn("Refinanced");
		Mockito.when(room3.getItem()).thenReturn(Item.COFFEE);
		Mockito.when(room3.getNorthDoor()).thenReturn("Dead");
		Mockito.when(room3.getSouthDoor()).thenReturn("Smart");
		rooms.add(room3);

		Room room4 = Mockito.mock(Room.class);
		Mockito.when(room4.getFurnishing()).thenReturn("Flat energy drink");
		Mockito.when(room4.getAdjective()).thenReturn("Dumb");
		Mockito.when(room4.getItem()).thenReturn(Item.NONE);
		Mockito.when(room4.getNorthDoor()).thenReturn("Vivacious");
		Mockito.when(room4.getSouthDoor()).thenReturn("Slim");
		rooms.add(room4);

		Room room5 = Mockito.mock(Room.class);
		Mockito.when(room5.getFurnishing()).thenReturn("Beautiful bag of money");
		Mockito.when(room5.getAdjective()).thenReturn("Bloodthirsty");
		Mockito.when(room5.getItem()).thenReturn(Item.NONE);
		Mockito.when(room5.getNorthDoor()).thenReturn("Purple");
		Mockito.when(room5.getSouthDoor()).thenReturn("Sandy");
		rooms.add(room5);

		Room room6 = Mockito.mock(Room.class);
		Mockito.when(room6.getFurnishing()).thenReturn("Perfect air hockey table");
		Mockito.when(room6.getAdjective()).thenReturn("Rough");
		Mockito.when(room6.getItem()).thenReturn(Item.SUGAR);
		Mockito.when(room6.getSouthDoor()).thenReturn("Minimalist");
		rooms.add(room6);

		// 3. Create Coffee Maker Quest game using player and rooms, and assign to cmq.
		cmq = CoffeeMakerQuest.createInstance(player, rooms);
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test case for String getInstructionsString().
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.getInstructionsString().
	 * Postconditions: Return value is " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 * </pre>
	 */
	@Test
	public void testGetInstructionsString() {
		// TODO
		//cmq = Mockito.mock(CoffeeMakerQuest.class);
		//Mockito.when(cmq.getInstructionsString()).thenReturn(" INSTRUCTIONS (N,S,L,I,D,H) > ");
		assertEquals("Instruction strings do not match.", " INSTRUCTIONS (N,S,L,I,D,H) > ", cmq.getInstructionsString());
	}

	/**
	 * Test case for Room getCurrentRoom().
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.getCurrentRoom().
	 * Postconditions: Return value is rooms.get(0).
	 * </pre>
	 */
	@Test
	public void testGetCurrentRoom() {
		// TODO
		assertEquals("Current room does not match.", rooms.get(0), cmq.getCurrentRoom());
	}

	/**
	 * Test case for void setCurrentRoom(Room room) and Room getCurrentRoom().
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.setCurrentRoom(rooms.get(2)).
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.setCurrentRoom(rooms.get(2)) is true. 
	 *                 Return value of cmq.getCurrentRoom() is rooms.get(2).
	 * </pre>
	 */
	@Test
	public void testSetCurrentRoom() {
		// TODO
		assertTrue("The rooms do not match.", cmq.setCurrentRoom(rooms.get(2)));
		assertEquals("The rooms do not match.", rooms.get(2), cmq.getCurrentRoom());
	}
	
	/**
	 * Test case for boolean areDoorsPlacedCorrectly() when check succeeds.
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.areDoorsPlacedCorrectly().
	 * Postconditions: Return value of cmq.areDoorsPlacedCorrectly() is true.
	 * </pre>
	 */
	@Test
	public void testAreDoorsPlacedCorrectly() {
		// TODO
		assertTrue("The doors are not placed correctly.", cmq.areDoorsPlacedCorrectly());
	}

	/**
	 * Test case for boolean areDoorsPlacedCorrectly() when check fails.
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                rooms.get(3) is modified so that it has no south door.
	 * Execution steps: Call cmq.areDoorsPlacedCorrectly().
	 * Postconditions: Return value of cmq.areDoorsPlacedCorrectly() is false.
	 * </pre>
	 */
	@Test
	public void testAreDoorsPlacedCorrectlyMissingSouthDoor() {
		// TODO
		Mockito.when(rooms.get(3).getSouthDoor()).thenReturn(null);
		assertFalse("The test failed.", cmq.areDoorsPlacedCorrectly());
	}

	/**
	 * Test case for boolean areRoomsUnique() when check fails.
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                rooms.get(2) is modified so that its adjective is modified to "Small".
	 * Execution steps: Call cmq.areRoomsUnique().
	 * Postconditions: Return value of cmq.areRoomsUnique() is false.
	 * </pre>
	 */
	@Test
	public void testAreRoomsUniqueDuplicateAdjective() {
		// TODO
		Mockito.when(rooms.get(2).getAdjective()).thenReturn("Small");
		assertFalse("The test failed.", cmq.areRoomsUnique());
	}

	/**
	 * Test case for boolean areRoomsUnique() when check fails.
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.areRoomsUnique().
	 * Postconditions: Return value of cmq.areRoomsUnique() is true.
	 * </pre>
	 */
	@Test
	public void testAreRoomsUniqueTrue() {
		// TODO
		assertTrue("The test failed.", cmq.areRoomsUnique());
	}

	/**
	 * Test case for String processCommand("I").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                Player has no items.
	 * Execution steps: Call cmq.processCommand("I").
	 * Postconditions: Return value is "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n".
	 * </pre>
	 */
	@Test
	public void testProcessCommandI() {
		// TODO
		Mockito.when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n");
		assertEquals("The strings do not match.", "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n", cmq.processCommand("I"));
	}

	/**
	 * Test case for String processCommand("H").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.processCommand("H").
	 * Postconditions: Return value is "N - Go north\nS - Go south\nL - Look and collect any items in the room\nI - Show inventory of items collected\nD - Drink coffee made from items in inventory\n".
	 * </pre>
	 */
	@Test
	public void testProcessCommandH() {
		// TODO
		assertEquals("The strings do not match.", "N - Go north\nS - Go south\nL - Look and collect any items in the room\nI - Show inventory of items collected\nD - Drink coffee made from items in inventory\n", cmq.processCommand("H"));
	}

	/**
	 * Test case for String processCommand("l").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some creamy cream!\n".
	 *                 Item.CREAM has been added to the Player.
	 * </pre>
	 */
	@Test
	public void testProcessCommandLCream() {
		cmq.setCurrentRoom(rooms.get(0));

		Mockito.when(player.checkCream()).thenReturn(true);
		assertEquals("The strings do not match.", "There might be something here...\nYou found some creamy cream!\n", cmq.processCommand("l"));
		assertTrue("The item is not present.", player.checkCream());
	}

	/**
	 * Test case for String processCommand("l").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * 				  cmq.setCurrentRoom(rooms.get(2)) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some caffeinated coffee!\n".
	 *                 Item.COFFEE has been added to the Player.
	 * </pre>
	 */
	@Test
	public void testProcessCommandLCoffee() {
		cmq.setCurrentRoom(rooms.get(2));

		Mockito.when(player.checkCoffee()).thenReturn(true);
		assertEquals("The strings do not match.", "There might be something here...\nYou found some caffeinated coffee!\n", cmq.processCommand("l"));
		assertTrue("The item is not present.", player.checkCoffee());
	}

	/**
	 * Test case for String processCommand("l").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * 				  cmq.setCurrentRoom(rooms.get(5)) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some sweet sugar!\n".
	 *                 Item.SUGAR has been added to the Player.
	 * </pre>
	 */
	@Test
	public void testProcessCommandLSugar() {
		cmq.setCurrentRoom(rooms.get(5));

		Mockito.when(player.checkSugar()).thenReturn(true);
		assertEquals("The strings do not match.", "There might be something here...\nYou found some sweet sugar!\n", cmq.processCommand("l"));
		assertTrue("The item is not present.", player.checkSugar());
	}

	/**
	 * Test case for String processCommand("l").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * 				  cmq.setCurrentRoom(rooms.get(1)) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "You don't see anything out of the ordinary.\n".
	 * </pre>
	 */
	@Test
	public void testProcessCommandLEmpty() {
		cmq.setCurrentRoom(rooms.get(1));

		assertEquals("The strings do not match.", "You don't see anything out of the ordinary.\n", cmq.processCommand("l"));
	}

	/**
	 * Test case for String processCommand("n").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                cmq.setCurrentRoom(rooms.get(3)) has been called.
	 * Execution steps: Call cmq.processCommand("n").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("n") is "".
	 *                 Return value of cmq.getCurrentRoom() is rooms.get(4).
	 * </pre>
	 */
	@Test
	public void testProcessCommandN() {
		// TODO
		cmq.setCurrentRoom(rooms.get(3));

		String nTestString = cmq.processCommand("n");
		Room testRoom = cmq.getCurrentRoom();

		assertEquals("The outputs do not match.", "", nTestString);
		assertEquals("The rooms do not match", rooms.get(4), testRoom);
	}

	/**
	 * Test case for String processCommand("s").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.processCommand("s").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("s") is "A door in that direction does not exist.\n".
	 *                 Return value of cmq.getCurrentRoom() is rooms.get(0).
	 * </pre>
	 */
	@Test
	public void testProcessCommandS() {
		// TODO
		assertEquals("The outputs do not match.", "A door in that direction does not exist.\n", cmq.processCommand("s"));
		assertEquals("The rooms do not match.", rooms.get(0), cmq.getCurrentRoom());
	}

	/**
	 * Test case for String processCommand("s").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                cmq.setCurrentRoom(rooms.get(1)) has been called.
	 * Execution steps: Call cmq.processCommand("n").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("s") is "".
	 *                 Return value of cmq.getCurrentRoom() is rooms.get(0).
	 * </pre>
	 */
	@Test
	public void testProcessCommandSWithDoor() {
		// TODO
		cmq.setCurrentRoom(rooms.get(1));

		String sTestString = cmq.processCommand("s");
		Room testRoom = cmq.getCurrentRoom();
		
		assertEquals("The outputs do not match.", "", sTestString);
		assertEquals("The rooms do not match.", rooms.get(0), testRoom);
	}

	/**
	 * Test case for String processCommand("D").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n\nYou drink thin air and can only dream of coffee. You cannot study.\nYou lose!\n".
	 *                 Return value of cmq.isGameOver() is true.
	 * </pre>
	 */
	@Test
	public void testProcessCommandDLose() {
		// TODO
		Mockito.when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n");
		Mockito.when(player.checkCoffee()).thenReturn(false);
		Mockito.when(player.checkCream()).thenReturn(false);
		Mockito.when(player.checkSugar()).thenReturn(false);

		assertEquals("The outputs do not match.", "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n\nYou drink thin air and can only dream of coffee. You cannot study.\nYou lose!\n", cmq.processCommand("D"));
		assertTrue("The game is not over.", cmq.isGameOver());
	}

	/**
	 * Test case for String processCommand("D").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * 				  Player has cream and sugar, but not coffee
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "YOU HAVE NO COFFEE!\nYou have some fresh cream.\nYou have some tasty sugar.\n\nYou refuse to drink this half-made sludge. You cannot study.\nYou lose!".
	 *                 Return value of cmq.isGameOver() is true.
	 * </pre>
	 */
	@Test
	public void testProcessCommandDLoseCoffee() {
		// TODO
		Mockito.when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYou have some fresh cream.\nYou have some tasty sugar.\n");
		Mockito.when(player.checkCoffee()).thenReturn(false);
		Mockito.when(player.checkCream()).thenReturn(true);
		Mockito.when(player.checkSugar()).thenReturn(true);

		assertEquals("The outputs do not match.", "YOU HAVE NO COFFEE!\nYou have some fresh cream.\nYou have some tasty sugar.\n\nYou refuse to drink this half-made sludge. You cannot study.\nYou lose!\n", cmq.processCommand("D"));
		assertTrue("The game is not over.", cmq.isGameOver());
	}

	/**
	 * Test case for String processCommand("D").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 *                Player has all 3 items (coffee, cream, sugar).
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n\nYou drink the beverage and are ready to study!\nYou win!\n".
	 *                 Return value of cmq.isGameOver() is true.
	 * </pre>
	 */
	@Test
	public void testProcessCommandDWin() {
		// TODO
		Mockito.when(player.getInventoryString()).thenReturn("You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n");
		Mockito.when(player.checkCoffee()).thenReturn(true);
		Mockito.when(player.checkCream()).thenReturn(true);
		Mockito.when(player.checkSugar()).thenReturn(true);
	
		assertEquals("The outputs do not match.", "You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n\nYou drink the beverage and are ready to study!\nYou win!\n", cmq.processCommand("D"));
		assertTrue("The game is not over.", cmq.isGameOver());
	}

	/**
	 * Test case for String processCommandUnknown.
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.processCommand("a").
	 					Call cmq.processCommand("5").
	 * Postconditions: Return value of cmq.processCommand("a") is "What?\n".
	 				   Return value of cmq.processCommand("5") is "What?\n".
	 * </pre>
	 */
	@Test
	public void testProcessCommandUnknown() {
		assertEquals("The outputs do not match.", "What?\n", cmq.processCommand("a"));
		assertEquals("The outputs do not match.", "What?\n", cmq.processCommand("5"));
	}

	/**
	 * Test case for String processCommand("N").
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 				  Call cmq.setCurrentRoom(rooms.get(rooms.size()-1)).
	 * Execution steps: Call cmq.processCommand("N").
	 * Postconditions: Return value is "A door in that direction does not exist.\n".
	 * </pre>
	 */
	@Test
	public void testProcessCommandNNoDoor() {
		cmq.setCurrentRoom(rooms.get(rooms.size()-1));
		assertEquals("The outputs do not match.", "A door in that direction does not exist.\n", cmq.processCommand("N"));
	}

	/**
	 * Test case for boolean isGameOver().
	 * 
	 * <pre>
	 * Preconditions: Player, rooms, and cmq test fixture has been created.
	 * Execution steps: Call cmq.isGameOver().
	 * Postconditions: Return value is False.
	 * </pre>
	 */
	@Test 
	public void testIsGameOver(){
		assertFalse("The game is over.", cmq.isGameOver());
	}


	@Test
	public void testStartString() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		/*Mockito.when(player.checkCoffee()).thenReturn(false);
		Mockito.when(player.checkCream()).thenReturn(false);
		Mockito.when(player.checkSugar()).thenReturn(false);*/
		Mockito.when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n");
		Method method = CoffeeMakerQuestImpl.class.getDeclaredMethod("startString");
		method.setAccessible(true);
		String string = new String();
		string = (String)method.invoke(cmq);
		assertEquals("The strings do no match", "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n\n", string);
	}

}
