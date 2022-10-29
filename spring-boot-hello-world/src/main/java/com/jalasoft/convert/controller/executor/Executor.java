/**
 * Copyright (c) 2022 Jala University.
 * <p>
 * This software is the confidential and proprieraty information of Jalasoft
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * Licence agreement you entered into with Jalasoft
 */
package com.jalasoft.convert.controller.executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * It is responsible for executing the command in console.
 *
 * @author Alvaro Sivila Ramirez, Adriana Olivera Ordoñez
 * @version 1.0
 */

public class Executor {
    private Process process;

    public void runCommand(List<String> command) throws IOException {
        initProcess(command);
    }

    private void initProcess(List<String> command) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();
        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        while ((br.readLine()) != null) {

        }
        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
<<<<<<< HEAD:spring-boot-hello-world/src/main/java/com/jalasoft/convert/controller/executor/Executor.java
         }
=======
        }

        /*builder.redirectErrorStream(true);

        try {
            process = builder.start();
        } catch (IOException e) {
            System.out.println (e);
        }*/
>>>>>>> 60d1909 (solving format errors in controller classes):spring-boot-hello-world/src/main/java/com/jalasoft/convert/model/Executor.java
    }

    private void readConsole() {
        try {
            process.waitFor();
            InputStreamReader streamReader = new InputStreamReader(process.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder result = new StringBuilder();
            while (reader.ready()) {
                result.append((char) reader.read());
            }
            System.out.println(result.toString());
        } catch (InterruptedException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}