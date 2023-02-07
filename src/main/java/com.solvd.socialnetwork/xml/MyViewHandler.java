package com.solvd.socialnetwork.xml;

import com.solvd.socialnetwork.View;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class MyViewHandler extends DefaultHandler {

    private List<View> viewsList = new ArrayList<>();
    private StringBuilder characters;
    private boolean viewCount;
    private boolean accountId;
    private boolean postId;
    private boolean profileId;

    public List<View> getViewsList() {
        return viewsList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes a) {
        switch (qName.toLowerCase()){
            case "view":
                View view = new View();
                view.setId(Long.parseLong(a.getValue("id")));
                viewsList.add(view);
                break;

            case "viewcount":
                viewCount = true;
                characters = new StringBuilder();
                break;

            case "accountid":
                accountId = true;
                characters = new StringBuilder();
                break;

            case "postid":
                postId = true;
                characters = new StringBuilder();
                break;

            case "profileid":
                profileId = true;
                characters = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        switch (qName.toLowerCase()){
            case "viewcount":
                viewCount = false;
                Long count = Long.parseLong(characters.toString().trim());
                View currentView = viewsList.get(viewsList.size() - 1);
                currentView.setViewCount(count);
                break;

            case "accountid":
                accountId = false;
                Long account = Long.parseLong(characters.toString().trim());
                currentView = viewsList.get(viewsList.size() - 1);
                currentView.setAccountId(account);
                break;

            case "postid":
                postId = false;
                Long post = Long.parseLong(characters.toString().trim());
                currentView = viewsList.get(viewsList.size() - 1);
                currentView.setPostId(post);
                break;

            case "profileid":
                profileId = false;
                Long profile = Long.parseLong(characters.toString().trim());
                currentView = viewsList.get(viewsList.size() - 1);
                currentView.setProfileId(profile);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (viewCount){
            characters.append(new String(ch, start, length));
        }
        if (accountId){
            characters.append(new String(ch, start, length));
        }
        if (postId){
            characters.append(new String(ch, start, length));
        }
        if (profileId){
            characters.append(new String(ch, start, length));
        }
    }

}
