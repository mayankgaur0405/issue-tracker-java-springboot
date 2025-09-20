package com.issuetracker.repository;

import com.issuetracker.model.Issue;
import com.issuetracker.model.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    
    // Find issues by status
    List<Issue> findByStatus(IssueStatus status);
    
    // Find issues assigned to a specific user
    List<Issue> findByAssignedUser(String assignedUser);
    
    // Find issues by status and assigned user
    List<Issue> findByStatusAndAssignedUser(IssueStatus status, String assignedUser);
    
    // Find issues by title containing (case-insensitive)
    List<Issue> findByTitleContainingIgnoreCase(String title);
}


