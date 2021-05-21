/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import portal.dto.Authorities;
import portal.dto.Comment;
import portal.dto.Story;
import portal.dto.Users;

/**
 *
 * @author laptop-02
 */
public interface StoryRepository {
    List<Story> getAllNews();
    Story getNewByID(int newID);
    void addComment(Comment c);
    List<Comment> getAllNotVerifiedComments();
    void acceptCommentByID(int storyID);
    void declineCommentByID(int storyID);
    void updateStory(Story story);
    void addStory(Story story);
    Date getLastPayDateByUsername(String username);
    void updatePayment(String username);
}
