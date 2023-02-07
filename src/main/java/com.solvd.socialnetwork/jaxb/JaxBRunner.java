package com.solvd.socialnetwork.jaxb;

import com.solvd.socialnetwork.Account;
import com.solvd.socialnetwork.Message;
import com.solvd.socialnetwork.Share;
import com.solvd.socialnetwork.User;
import com.solvd.socialnetwork.Following;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxBRunner {
    private static final Logger logger = LogManager.getLogger(JaxBRunner.class);

    public static void main(String[] args) throws JAXBException {
        // marshalling
        Account account = new Account();
        account.setId(1L);
        account.setUser_id(1L);
        account.setPassword("jan139");
        account.setUsername("jane_comb");

        // create JaxBContext object
        JAXBContext context = JAXBContext.newInstance(Account.class);

        // create marshaller object from JaxBContext object
        Marshaller marshaller = context.createMarshaller();

        // call marshall method from marshaller object
        marshaller.marshal(account, System.out);

        logger.info("\n");

        // unmarshalling, create unmarshaller object from JaxBContext object
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Share share = new Share();
        share.setId(1L);
        share.setShareCount(5L);
        share.setAccountId(1L);
        share.setPostId(1L);

        JAXBContext context1 = JAXBContext.newInstance(Share.class);

        Marshaller marshaller1 = context1.createMarshaller();

        marshaller1.marshal(share, System.out);

        logger.info("\n");

        User user = new User();
        user.setId(1L);
        user.setFirstName("Jane");
        user.setLastName("Comb");
        user.setAge(23);
        user.setEmail("Jane.Comb@gmail.com");
        user.setPhoneNumber("231-345-2200");

        JAXBContext context2 = JAXBContext.newInstance(User.class);

        Marshaller marshaller2 = context2.createMarshaller();

        marshaller2.marshal(user, System.out);

        logger.info("\n");


        Message message = new Message();
        message.setId(1L);
        message.setText("Hey! How are you?");
        message.setSenderId(1L);
        message.setRecipientId(2L);
        message.setAccountId(1L);

        JAXBContext context3 = JAXBContext.newInstance(Message.class);

        Marshaller marshaller3 = context3.createMarshaller();

        marshaller3.marshal(message, System.out);


        logger.info("\n");


        Following following = new Following();
        following.setId(1L);
        following.setFollowerId(2L);
        following.setFollowingId(1L);
        following.setAccountId(1L);

        JAXBContext context4 = JAXBContext.newInstance(Following.class);

        Marshaller marshaller4 = context4.createMarshaller();

        marshaller4.marshal(following, System.out);



        // call unmarshal method from unmarshaller object
        Account account1 = (Account) unmarshaller.unmarshal(new File("/Users/parisamory/Documents/development/SocialNetwork/src/main/java/com.solvd.socialnetwork/xml/AccountXML.xml"));
        logger.info("\nID: " + account1.getId());
        logger.info("Username: " + account1.getUsername());
        logger.info("User ID: " + account1.getUser_id());
        logger.info("Password: " + account1.getPassword());







    }
}
