package portal.service;

import java.util.ArrayList;
import java.util.List;
import portal.dto.Authorities;
import portal.dto.Comment;
import portal.dto.Story;
import portal.dto.Users;

public interface StoryService {
    List<Story> getAllNews();
    Story getNewByID(int newID);
    void addComment(Comment c);
    List<Comment> getAllNotVerifiedComments();
    void acceptCommentByID(int storyID);
    void declineCommentByID(int storyID);
    void updateStory(Story story);
    void addStory(Story story);
}
