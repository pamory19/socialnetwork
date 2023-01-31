package com.solvd.socialnetwork.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.solvd.socialnetwork.Post;
import com.solvd.socialnetwork.Reaction;
import com.solvd.socialnetwork.Share;
import com.solvd.socialnetwork.View;
import com.solvd.socialnetwork.Comment;

public class MyPostHandler extends DefaultHandler {
    private List<Post> posts;
    private Post currentPost;
    private String content;
    private List<Share> shares;
    private Share currentShare;
    private List<Comment> comments;
    private Comment currentComment;
    private List<View> views;
    private View currentView;
    private List<Reaction> reactions;
    private Reaction currentReaction;

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "Post":
                currentPost = new Post();
                currentPost.setId(Integer.parseInt(attributes.getValue("id")));
                break;
            case "Shares":
                shares = new ArrayList<>();
                break;
            case "Share":
                currentShare = new Share();
                currentShare.setId(Integer.parseInt(attributes.getValue("id")));
                break;
            case "Comments":
                comments = new ArrayList<>();
                break;
            case "Comment":
                currentComment = new Comment();
                currentComment.setId(Integer.parseInt(attributes.getValue("id")));
                break;
            case "Views":
                views = new ArrayList<>();
                break;
            case "View":
                currentView = new View();
                currentView.setId(Integer.parseInt(attributes.getValue("id")));
                break;
            case "Reactions":
                reactions = new ArrayList<>();
                break;
            case "Reaction":
                currentReaction = new Reaction();
                currentReaction.setId(Integer.parseInt(attributes.getValue("id")));
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "Post":
                if (posts == null) {
                    posts = new ArrayList<>();
                }
                posts.add(currentPost);
                break;

            case "caption":
                currentPost.setCaption(content);
                break;

            case "accountId":
                if (currentShare != null) {
                    currentShare.setAccountId(Integer.parseInt(content));
                } else if (currentComment != null) {
                    currentComment.setAccountId(Integer.parseInt(content));
                } else if (currentView != null) {
                    currentView.setAccountId(Integer.parseInt(content));
                } else if (currentReaction != null) {
                    currentReaction.setAccountId(Integer.parseInt(content));
                }
                break;

            case "Share":
                shares.add(currentShare);
                break;

            case "Shares":
                currentPost.setShares(shares);
                break;

            case "Comment":
                comments.add(currentComment);
                break;

            case "Comments":
                currentPost.setComments(comments);
                break;

            case "View":
                views.add(currentView);
                break;

            case "Views":
                currentPost.setViews(views);
                break;

            case "Reaction":
                reactions.add(currentReaction);
                break;

            case "Reactions":
                currentPost.setReactions(reactions);
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

