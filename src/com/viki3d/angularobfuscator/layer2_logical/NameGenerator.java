package com.viki3d.angularobfuscator.layer2_logical;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.viki3d.angularobfuscator.layer1_data_access.FileManipulator;

/**
 * <code>NameGenerator</code> is responsible for providing random variables
 * or functions names, needed for the obfuscation process.
 * 
 * @author Victor Kirov
 */
public class NameGenerator {
	
	private static final Logger logger = 
			Logger.getLogger( FileManipulator.class.getName() );	
    
	/**
	 * The minimum length of the names, generated.
	 */
    private static final int CONST_NAMES_MIN_LENGTH = 2;    
	
	/**
	 * The maximum length of the names, generated.
	 */
    private static final int CONST_NAMES_MAX_LENGTH = 8;
    
    /**
     * The charset, used for newly generated names
     * 
     * @see	generateNewName()
     */
    private final String MY_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";    
    
    /**
     * The singleton instance
     * @see	getInstance()
     */
    private static NameGenerator instance = null;
    
    /**
     * All generated names associations are kept here.
     * The key->value are the oldName->newName
     */
    private Map<String, String> names;

    /**
     * @return	The singleton instance of this class
     */
    public static NameGenerator getInstance() {
        if (instance==null)
            instance = new NameGenerator();

        return instance;
    }
    
    /**
     * The only one constructor, which is private in order to
     * implement the Singleton design pattern.
     */
    private NameGenerator() {
        names = new HashMap<String, String>();
    }

    /**
     * Returns a pseudorandom, uniformly distributed int value between 
     * 0 (inclusive) and the <code>max</code> value (exclusive)
     * 
     * @param 	max
     * @return	Returns the random generated <code>int</code> value.
     */
    private int generateRandomNumberFromZeroTo(int max) {

        int theNumber = new Random().nextInt(max);
        
        return theNumber;
    }
    
    /**
     * Returns a pseudorandom, uniformly distributed int value between 
     * <code>min</code> (inclusive) and <code>max</code> (exclusive)
     * 
     * @param 	min
     * @param 	max
     * @return	Returns the random generated <code>int</code> value.
     */
    private int generateRandomNumberInInterval(int min, int max) {
        //  Some robust protection
        if (max<min) max=min;
        
        int delta = max - min;
        
        int theNumber = min + new Random().nextInt(delta);
        
        return theNumber;
    }
    
    /**
     * Generates new random obfuscated name
     * 
     * @return	The name, generated
     * 			Null, when error occurs
     */
    private String generateNewName() {
    	
        StringBuilder newName = new StringBuilder();
        int randomLength = 
        		generateRandomNumberInInterval(
        				CONST_NAMES_MIN_LENGTH, CONST_NAMES_MAX_LENGTH);
        int index;
        for (int i=0;i<randomLength;i++) {
        	index = generateRandomNumberFromZeroTo(MY_CHARSET.length());
        	newName.append(MY_CHARSET.charAt(index));
        }
        
        String newNameInUtf8 = null;
        
        while (newNameInUtf8==null) {
	        try {        
	        	newNameInUtf8 = new String(newName.toString()
	        			.getBytes("UTF-8"), "UTF-8");
	        }
	        catch (UnsupportedEncodingException uee) {
	        	logger.log(Level.SEVERE, uee.getMessage());
	        }
        }
        
        logger.log(Level.FINEST, "New name generated: " + newNameInUtf8);
        
        return newNameInUtf8;
    }
    
    public String getNewName(String oldName) {
    	
		if (names.containsKey(oldName))
			return names.get(oldName);
    	
        String newName =  generateNewName();
        
        //  Re-generate name while it is non-unique in our set
        while (names.containsKey(newName) || oldName.equals(newName) 
        		|| oldName.startsWith(newName) ) {
        	newName = generateNewName();
        }
        
        //  Saves generated association oldName->newName
        names.put(oldName, newName);
        
        //	Return guaranteed unique name for our set
        return newName;
    }
    
   
    
}

