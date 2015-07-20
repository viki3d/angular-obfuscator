package com.viki3d.angularobfuscator.layer1_data_access;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The <code>FileManipulator</code> is used for all file operations (read/write)
 *
 * @author Victor Kirov
 */
public class FileManipulator {
	
	private static final Logger logger = 
			Logger.getLogger( FileManipulator.class.getName() );	
    
	/**
	 * The Charset, used for file manipulations (read/write).
	 */
    private static Charset charset = StandardCharsets.UTF_8;

    /**
     * Read whole content of a text file.
     * 
     * @param 	filename	The name of the file, which have to be written
     * 		
     * @return	List of String, containing file lines.
     * 				
     * @throws 	IOException
     * 			If file is not found or read error occurs.
     */
    public static List<String> readAllLines(String filename) throws IOException {
    	
    	logger.log(Level.FINE, "Reading file: " + filename);    	
    	
        Path path = Paths.get(filename);
        
        return Files.readAllLines(path, charset);
    }       

    /**
     * 
     * @param lines		The text lines, which will be the new file content.
     * 
     * @param filename	The filename, which will be overwritten.
     * 
     * @throws IOException	If file is not writable or error occurs during file 
     * writing.
     */
    public static void storeAllLines(List<String> lines, String filename) 
    		throws IOException {

    	logger.log(Level.FINE, "Saving file: " + filename);
    	
        Files.write(
                Paths.get(filename), 
                lines, 
                charset,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }      
    
    public static void saveLogFile(List<String> lines, String filename) 
    		throws IOException {
    	
        //  If log file does not exist -> try to create an empty file
        try {
            Files.createFile(Paths.get(filename));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            
            logger.log(Level.INFO, "Log file exist. It will be overwritten.");
        }
        
        //  When it is sure file exist, go to write contents. 
        //  If file not exists --> Exception will be thrown !!!
        storeAllLines(lines, filename);
    }
    
    /**
     * Renames the file 
     * 
     * @param oldFilename	The old filename
     * @param newFilename	The new filename
     */
    public static void rename(String oldFilename, String newFilename) {
        File fileToRename = new File(oldFilename);
        if (fileToRename.exists()) {
            try {
                fileToRename.renameTo(new File(newFilename));
            }
            catch (RuntimeException e) {
                System.out.println( e.getMessage() );
                logger.log(Level.SEVERE, "Warning: failed to rename: " 
                		+ oldFilename);
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
        else {
            logger.log(Level.SEVERE, "Can not rename: "
            		+ "File does not exist: "+oldFilename);
        }
    }

    /**
     * Get the Charset, used for file manipulations (read/write).
     * 
     * @return the charset
     */
    public static Charset getCharset() {
        return charset;
    }

    /**
     * Set the Charset, used for file manipulations (read/write).
     * 
     * @param aCharset the charset to set
     */
    public static void setCharset(Charset aCharset) {
        charset = aCharset;
    }
    
}

