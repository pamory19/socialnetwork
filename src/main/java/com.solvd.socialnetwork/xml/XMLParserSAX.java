package com.solvd.socialnetwork.xml;

import com.solvd.socialnetwork.Account;
import com.solvd.socialnetwork.Post;
import com.solvd.socialnetwork.Share;
import com.solvd.socialnetwork.User;
import com.solvd.socialnetwork.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLParserSAX {

    private static final Logger logger = LogManager.getLogger(XMLParserSAX.class);


    public static void main(String[] args){

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyViewHandler handler = new MyViewHandler();
            saxParser.parse(new File("/Users/parisamory/Documents/development/SocialNetwork/src/main/java/com.solvd.socialnetwork/xml/ViewXML"), handler);
            List<View> viewsList = handler.getViewsList();
            for (View view : viewsList){
                logger.info("View Count: " + view.getViewCount());
                logger.info("Account ID: " + view.getAccountId());
                logger.info("Post ID: " + view.getPostId());
                logger.info("Profile ID: " + view.getProfileId());
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }

        logger.info("___________");

        SAXParserFactory saxParserFactory1 = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyAccountHandler handler1 = new MyAccountHandler();
            saxParser.parse(new File("/Users/parisamory/Documents/development/SocialNetwork/src/main/java/com.solvd.socialnetwork/xml/AccountXML"), handler1);
            List<Account> accountsList = handler1.getAccountsList();
            for (Account account : accountsList){
                logger.info("Password: " + account.getPassword());
                logger.info("Username: " + account.getUsername());
                logger.info("User ID: " + account.getUser_id());
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }

        logger.info("___________");

        SAXParserFactory saxParserFactory2 = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyPostHandler handler2 = new MyPostHandler();
            saxParser.parse(new File("/Users/parisamory/Documents/development/SocialNetwork/src/main/java/com.solvd.socialnetwork/xml/PostXML"), handler2);
            List<Post> postsList = handler2.getPosts();
            for (Post post : postsList){
                logger.info("Caption: " + post.getCaption());
                logger.info("Account ID: " + post.getAccountId());
                logger.info("Shares: " + post.getShares());
                logger.info("Comments: " + post.getComments());
                logger.info("Views: " + post.getViews());
                logger.info("Reactions: " + post.getReactions());
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }

        logger.info("___________");

        SAXParserFactory saxParserFactory3 = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyShareHandler handler3 = new MyShareHandler();
            saxParser.parse(new File("/Users/parisamory/Documents/development/SocialNetwork/src/main/java/com.solvd.socialnetwork/xml/ShareXML"), handler3);
            List<Share> sharesList = handler3.getShares();
            for (Share share : sharesList){
                logger.info("Share Count: " + share.getShareCount());
                logger.info("Account ID: " + share.getAccountId());
                logger.info("Post ID: " + share.getPostId());
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }

        logger.info("___________");

        SAXParserFactory saxParserFactory4 = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyUserHandler handler4 = new MyUserHandler();
            saxParser.parse(new File("/Users/parisamory/Documents/development/SocialNetwork/src/main/java/com.solvd.socialnetwork/xml/UserXML"), handler4);
            List<User> usersList = handler4.getUsersList();
            for (User user : usersList){
                logger.info("Firstname: " + user.getFirstName());
                logger.info("Lastname: " + user.getLastName());
                logger.info("Age: " + user.getAge());
                logger.info("Email: " + user.getEmail());
                logger.info("Phone Number: " + user.getPhoneNumber());
                logger.info("Account ID: " + user.getAccount().getId());
            }
        }
        catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }



        // create a SchemaFactory instance that can be used to create a Schema object from an XSD file
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        // create a schema object from XSD file
        Schema schema;
        try {
            schema = factory.newSchema(new File("/Users/parisamory/Documents/development/SocialNetwork/src/main/java/com.solvd.socialnetwork/xml/ViewXSD"));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        // create a validator object from the schema object
        Validator validator = schema.newValidator();

        // create a source object that represents the XML file you want to validate
        Source source = new StreamSource(new File(("/Users/parisamory/Documents/development/SocialNetwork/src/main/java/com.solvd.socialnetwork/xml/ViewXML")));

        // use the validator's validate() method to perform validation
        try {
            validator.validate(source);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }



        SchemaFactory factory1 = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Schema schema1;
        try {
            schema1 = factory1.newSchema(new File("/Users/parisamory/Documents/development/SocialNetwork/src/main/java/com.solvd.socialnetwork/xml/AccountXSD"));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        // create a validator object from the schema object
        Validator validator1 = schema1.newValidator();

        // create a source object that represents the XML file you want to validate
        Source source1 = new StreamSource(new File(("/Users/parisamory/Documents/development/SocialNetwork/src/main/java/com.solvd.socialnetwork/xml/AccountXML")));

        // use the validator's validate() method to perform validation
        try {
            validator1.validate(source1);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}
