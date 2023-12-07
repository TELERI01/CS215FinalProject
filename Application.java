import java.util.*;


public class Application {
	
	//Some Lists of suspects
    private static final List<String> NAMES = List.of("John Doe", "Jane Smith", "Bob Johnson", "Alice Williams");
    //Lists of Crimes
    private static final List<String> CRIME = List.of("Theft", "Assault", "Drug possession", "Trespassing");
    //Random Generator
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Suspect> suspects = new ArrayList<>();//Bag ADT
        Queue<Suspect> inductQueue = new LinkedList<>(); //Queue ADT
        List<Suspect> holdingCells = new ArrayList<>(); //Bag ADT
        Map<String, Suspect> suspectLog = new HashMap<>(); //

        int timeStep = 0; //Timestep Variable

        while (true) {
        	
        	System.out.println("========Welcome to OCP Detroit Metro Police Department========");
            System.out.println("1 : Time Step\n2 : Suspect Log\n3 : Search Log\n4 : Exit");
            String userInput = scanner.nextLine();

            if (userInput.equals("4")) {
                System.out.println("Exiting OCP Detroit Metro Police Department. Have a nice day!.");
                break;
            }else if(userInput.equals("2")){
            	System.out.println("\n===Suspect Log===\n" +suspectLog.keySet());
            	
            }else if(userInput.equals("3")) {
            	do {
            		
            		System.out.println("=====Search Database=====\nType the suspects full name:");
            		String input = scanner.nextLine();
            		if(suspectLog.get(input)==null || suspectLog.isEmpty()) {
            			System.out.println("The Log does not contain entry or there are currently no suspects with that name!\nType in the Suspects full name, or check our Logs for suspect!");
            		}else {
            			System.out.println("Suspect Found!\n");
            			System.out.println("[" +"Name: " + suspectLog.get(input).name + " | Crime Committed: " +suspectLog.get(input).reasonForArrest + " | Current Status : " + suspectLog.get(input).status+"]");
            		}
            		break;
            		
            	}while(!userInput.equals("5"));
            	System.out.println("");
            }

            timeStep++;
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
                holdingCells.add(nextSuspect);
                suspectLog.put(nextSuspect.name, nextSuspect);
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
                    suspect.status = "Released";
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
