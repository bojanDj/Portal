package portal.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portal.dto.Authorities;
import portal.dto.Comment;
import portal.dto.Story;
import portal.dto.Users;
import portal.repository.StoryRepository;
import portal.service.StoryService;

@Service
public class StoryServiceImpl implements StoryService {
    @Autowired
    private final StoryRepository newRepository;

    @Autowired
    public StoryServiceImpl(StoryRepository newRepository) {
        this.newRepository = newRepository;
    }

    @Override
    public List<Story> getAllNews() {
        return newRepository.getAllNews();
    }

    @Override
    public Story getNewByID(int newID) {
        return newRepository.getNewByID(newID);
    }

    @Override
    public void addComment(Comment c) {
        newRepository.addComment(c);
    }

    @Override
    public List<Comment> getAllNotVerifiedComments() {
        return newRepository.getAllNotVerifiedComments();
    }

    @Override
    public void acceptCommentByID(int storyID) {
        newRepository.acceptCommentByID(storyID);
    }

    @Override
    public void declineCommentByID(int storyID) {
        newRepository.declineCommentByID(storyID);
    }

    @Override
    public void updateStory(Story story) {
        newRepository.updateStory(story);
    }

    @Override
    public void addStory(Story story) {
        newRepository.addStory(story);
    }


}
