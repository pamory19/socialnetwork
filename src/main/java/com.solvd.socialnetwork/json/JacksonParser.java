package com.solvd.socialnetwork.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.socialnetwork.Account;
import com.solvd.socialnetwork.Reaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JacksonParser {

    private static final Logger logger = LogManager.getLogger(JacksonParser.class);

    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Account account = objectMapper.readValue(new File("/Users/parisamory/Documents/development/SocialNetwork/src/main/java/com.solvd.socialnetwork/json/Account.json"), Account.class);
            logger.info("Parsed Account: " + account);
        } catch (IOException e) {
            logger.info(e);
        }


        try {
            Reaction reaction = objectMapper.readValue(new File("/Users/parisamory/Documents/development/SocialNetwork/src/main/java/com.solvd.socialnetwork/json/Reaction.json"), Reaction.class);
            logger.info("Parsed Account: " + reaction);
        } catch (IOException e) {
            logger.info(e);
        }


    }
}

