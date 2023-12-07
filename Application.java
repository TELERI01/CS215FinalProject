/**
 * Tyler Lericos
 * Johnson
 * CS215
 * Final Project
 */

import java.util.*;
public class Application {
	
	//Some Lists of suspects
    private static final List<String> NAMES = List.of("John Doe", "Jane Smith", "Bob Johnson", "Alice Williams");
    //Lists of Crimes
    private static final List<String> CRIME = List.of("Theft", "Assault", "Drug possession", "Trespassing");
    //Random Generator
    private static final Random RANDOM = new Random();
    /*
     * main method where the presinct simulator runs on random generators for names and crimes of suspects and random statuses.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);//Scanner Class
        List<Suspect> suspects = new ArrayList<>();//Bag ADT
        Queue<Suspect> inductQueue = new LinkedList<>(); //Queue ADT
        List<Suspect> holdingCells = new ArrayList<>(); //Bag ADT
        Map<String, Suspect> suspectLog = new HashMap<>(); //

        int timeStep = 0; //Timestep Variable
        /*
         * While loop simulator that uses options to forward the timestep, check the logs and search suspects
         */
        while (true) {
        	
        	System.out.println("========Welcome to OCP Detroit Metro Police Department========");
            System.out.println("1 : Time Step\n2 : Suspect Log\n3 : Search Log\n4 : Exit");
            String userInput = scanner.nextLine(); //Scanning the line for input
            
            //4, To Exit the program, 2 to print the log, 3 to search the log, and anything else (Though 1 is displayed) to move the timestep
            if (userInput.equals("4")) {
                System.out.println("Exiting OCP Detroit Metro Police Department. Have a nice day!.");
                break;
            }else if(userInput.equals("2")){
            	System.out.println("\n===Suspect Log===\n" +suspectLog.keySet()); //Prints the keys (Suspects' names)
            	
            }else if(userInput.equals("3")) {
            	do {
            		
            		System.out.println("=====Search Database=====\nType the suspects full name:");
            		String input = scanner.nextLine();
            		if(suspectLog.get(input)==null || suspectLog.isEmpty()) {
            			//Checking if the Log is empty
            			System.out.println("The Log does not contain entry or there are currently no suspects with that name!\nType in the Suspects full name, or check our Logs for suspect!");
            		}else {
            			//Prints retrieved Suspect with the key used
            			System.out.println("Suspect Found!\n");
            			System.out.println("[" +"Name: " + suspectLog.get(input).name + " | Crime Committed: " +suspectLog.get(input).reasonForArrest + " | Current Status : " + suspectLog.get(input).status+"]");
            		}
            		break;//Breaks this do while
            		
            	}while(!userInput.equals("5"));
            	System.out.println("");
            }

            timeStep++; //TimeStep is added +1
            //==============================================================================
            //Output print
            //==============================================================================
            System.out.println("======================================");
            System.out.println("Time Step " + timeStep + ":");

            // Randomly inducts a new suspect
            if (RANDOM.nextBoolean()) {
                Suspect newSuspect = new Suspect(
                        NAMES.get(RANDOM.nextInt(NAMES.size())),
                        CRIME.get(RANDOM.nextInt(CRIME.size())),
                        timeStep
                );
                inductQueue.add(newSuspect);
                System.out.println("New suspect in induction queue: " + newSuspect.name +
                        " (" + newSuspect.reasonForArrest +
                        ") - Time of Induction: " + newSuspect.timeOfInduction);
            }

            // Process each suspect from the induction queue
            while (!inductQueue.isEmpty()) {
                Suspect nextSuspect = inductQueue.poll();
                nextSuspect.inHoldingCell = RANDOM.nextBoolean();
                holdingCells.add(nextSuspect);//Adds suspect to the holdingcell bag
                suspectLog.put(nextSuspect.name, nextSuspect); //Adds to the suspectLog hashMap
                System.out.println("Inducted suspect: " + nextSuspect.name +
                        " (" + nextSuspect.reasonForArrest +
                        ") - Time of Induction: " + nextSuspect.timeOfInduction +
                        ", In Holding Cell: " + nextSuspect.inHoldingCell);
            }

            /*
             * For loop, processing suspects into and out of cells
             */
            for (Suspect suspect : holdingCells) {
                
                if (RANDOM.nextBoolean()) {
                    suspect.status = "Released";//sets status to released whne pulled out of the cells.
                    
                    System.out.println("Released suspect: " + suspect.name +
                            " (" + suspect.reasonForArrest +
                            ") - Time of Release: " + timeStep +
                            ", In Holding Cell: " + suspect.inHoldingCell);
                    
                }
            }

            System.out.println("================================================================\n"); //TimeStep Separator
        }

        
    }
}
