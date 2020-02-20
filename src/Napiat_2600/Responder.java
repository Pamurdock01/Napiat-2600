package Napiat_2600;

import java.util.*;

/**
 * The responder class represents a response generator object. It is
 * used to generate an automatic response.
 *
 * @author Michael Kolling and David J. Barnes
 * @version 0.1  (1.Feb.2002)
 */
public class Responder {
    private Random randomGenerator;
    private ArrayList responses;
    private HashMap responseMap;

    public Responder() {
        randomGenerator = new Random();
        responses = new ArrayList();
        responseMap = new HashMap();
        fillResponses();
        fillResponseMap();
    }

    /**
     * Generate a response.
     *
     * @return A string that should be displayed as the response
     */
    public String pickDefaultResponse() {
        int index = randomGenerator.nextInt(responses.size());
        return (String) responses.get(index);
    }

    private void fillResponses() {
        responses.add("\nThat command is not recognized in this portion of the game");
        responses.add("\nIllegal command");
        responses.add("\nPlease enter a valid command");
        responses.add("\nCommand not valid");
        responses.add("\nUnrecognized command");
        responses.add("\nPlease try another command");
        responses.add("\nNot a valid entry");
        responses.add("\nCommand not recognized");
        responses.add("\nTry another command in this section of the game");
    }

    public void fillResponseMap() {
        responseMap.put("pirates", "It is impossible to avoid the pirates!");
        responseMap.put("gold", "We would all like more gold!");
        responseMap.put("crap", "Profanity is not allowed in this galaxy!");
    }

    public String generateResponse(HashSet words) {
        Iterator it = words.iterator();
        while (it.hasNext()) {
            String word = (String) it.next();
            String response = (String) responseMap.get(word);
            if (response != null) {
                return response;
            }
        }
        return pickDefaultResponse();
    }
}
