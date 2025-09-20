package com.issuetracker.service;

import com.issuetracker.model.Issue;
import com.issuetracker.model.IssueStatus;
import com.issuetracker.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueService {
    
    @Autowired
    private IssueRepository issueRepository;
    
    // Create a new issue
    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }
    
    // Get all issues
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }
    
    // Get issue by ID
    public Optional<Issue> getIssueById(Long id) {
        return issueRepository.findById(id);
    }
    
    // Update issue status
    public Issue updateIssueStatus(Long id, IssueStatus status) {
        Optional<Issue> optionalIssue = issueRepository.findById(id);
        if (optionalIssue.isPresent()) {
            Issue issue = optionalIssue.get();
            issue.setStatus(status);
            return issueRepository.save(issue);
        }
        throw new RuntimeException("Issue not found with id: " + id);
    }
    
    // Assign issue to user
    public Issue assignIssueToUser(Long id, String assignedUser) {
        Optional<Issue> optionalIssue = issueRepository.findById(id);
        if (optionalIssue.isPresent()) {
            Issue issue = optionalIssue.get();
            issue.setAssignedUser(assignedUser);
            return issueRepository.save(issue);
        }
        throw new RuntimeException("Issue not found with id: " + id);
    }
    
    // Get issues by status
    public List<Issue> getIssuesByStatus(IssueStatus status) {
        return issueRepository.findByStatus(status);
    }
    
    // Get issues assigned to user
    public List<Issue> getIssuesByAssignedUser(String assignedUser) {
        return issueRepository.findByAssignedUser(assignedUser);
    }
    
    // Search issues by title
    public List<Issue> searchIssuesByTitle(String title) {
        return issueRepository.findByTitleContainingIgnoreCase(title);
    }
    
    // Update entire issue
    public Issue updateIssue(Long id, Issue updatedIssue) {
        Optional<Issue> optionalIssue = issueRepository.findById(id);
        if (optionalIssue.isPresent()) {
            Issue issue = optionalIssue.get();
            issue.setTitle(updatedIssue.getTitle());
            issue.setDescription(updatedIssue.getDescription());
            issue.setStatus(updatedIssue.getStatus());
            issue.setAssignedUser(updatedIssue.getAssignedUser());
            return issueRepository.save(issue);
        }
        throw new RuntimeException("Issue not found with id: " + id);
    }
    
    // Delete issue
    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);
    }
}


