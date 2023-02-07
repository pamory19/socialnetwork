package com.solvd.socialnetwork.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.solvd.socialnetwork.Share;

public class MyShareHandler extends DefaultHandler {
    private List<Share> shares;
    private Share currentShare;
    private String content;

    public List<Share> getShares() {
        return shares;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "Share":
                currentShare = new Share();
                String idString = attributes.getValue("id");
                if (idString != null) {
                    currentShare.setId(Long.parseLong(idString));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "Share":
                if (shares == null) {
                    shares = new ArrayList<>();
                }
                shares.add(currentShare);
                break;
            case "shareCount":
                currentShare.setShareCount(Long.parseLong(content));
                break;
            case "accountId":
                currentShare.setAccountId(Long.parseLong(content));
                break;
            case "postId":
                currentShare.setPostId(Long.parseLong(content));
                break;
            default:
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }
}
