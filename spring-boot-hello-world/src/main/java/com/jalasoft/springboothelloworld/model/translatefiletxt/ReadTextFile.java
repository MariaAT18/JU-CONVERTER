package com.jalasoft.springboothelloworld.model.translatefiletxt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ReadTextFile {

    // open the original text file to be translated and extract the content, read the content.
    /*
        The following code reads raw byte streams using FileInputStream
        and decodes them into characters using a specific character set using
        an InputStreamReader forms a string using a line separator.
        https://www.techiedelight.com/es/how-to-read-a-file-using-bufferedreader-in-java/     
    */
    
    public static String readFile(File file, Charset encoding) throws IOException {
        StringBuilder sb = new StringBuilder();
        FileInputStream fileStream = new FileInputStream(file);
        BufferedReader br;
        String line;
        br = new BufferedReader(new InputStreamReader(fileStream, encoding));
        while ((line = br.readLine()) != null) {
            sb.append(line + System.lineSeparator());
        }
        return sb.toString();
    }
}
