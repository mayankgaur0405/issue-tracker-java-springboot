package com.issuetracker.controller;

import com.issuetracker.model.Issue;
import com.issuetracker.model.IssueStatus;
import com.issuetracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin(origins = "*")
public class IssueController {
    
    @Autowired
    private IssueService issueService;
    
    // Create a new issue
    @PostMapping
    public ResponseEntity<Issue> createIssue(@RequestBody Issue issue) {
        Issue createdIssue = issueService.createIssue(issue);
        return ResponseEntity.ok(createdIssue);
    }
    
    // Get all issues
    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        List<Issue> issues = issueService.getAllIssues();
        return ResponseEntity.ok(issues);
    }
    
    // Get issue by ID
    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long id) {
        return issueService.getIssueById(id)
                .map(issue -> ResponseEntity.ok(issue))
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Update issue status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try {
            IssueStatus status = IssueStatus.valueOf(request.get("status").toUpperCase());
            Issue updatedIssue = issueService.updateIssueStatus(id, status);
            return ResponseEntity.ok(updatedIssue);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Assign issue to user
    @PatchMapping("/{id}/assign")
    public ResponseEntity<Issue> assignIssueToUser(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try {
            String assignedUser = request.get("assignedUser");
            Issue updatedIssue = issueService.assignIssueToUser(id, assignedUser);
            return ResponseEntity.ok(updatedIssue);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Get issues by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Issue>> getIssuesByStatus(@PathVariable String status) {
        try {
            IssueStatus issueStatus = IssueStatus.valueOf(status.toUpperCase());
            List<Issue> issues = issueService.getIssuesByStatus(issueStatus);
            return ResponseEntity.ok(issues);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Get issues assigned to user
    @GetMapping("/assigned/{user}")
    public ResponseEntity<List<Issue>> getIssuesByAssignedUser(@PathVariable String user) {
        List<Issue> issues = issueService.getIssuesByAssignedUser(user);
        return ResponseEntity.ok(issues);
    }
    
    // Search issues by title
    @GetMapping("/search")
    public ResponseEntity<List<Issue>> searchIssuesByTitle(@RequestParam String title) {
        List<Issue> issues = issueService.searchIssuesByTitle(title);
        return ResponseEntity.ok(issues);
    }
    
    // Update entire issue
    @PutMapping("/{id}")
    public ResponseEntity<Issue> updateIssue(@PathVariable Long id, @RequestBody Issue issue) {
        try {
            Issue updatedIssue = issueService.updateIssue(id, issue);
            return ResponseEntity.ok(updatedIssue);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Delete issue
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
        try {
            issueService.deleteIssue(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}


