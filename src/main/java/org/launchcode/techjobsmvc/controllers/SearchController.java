package org.launchcode.techjobsmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import org.launchcode.techjobsmvc.models.JobData;
import org.launchcode.techjobsmvc.models.Job;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

// Task 3: Complete SearchController
// Add displaySearchResults handler method to SearchController
//     Use the correct annotation for the method. To configure the correct mapping type and route, refer
//         to the form tag in the search.html template (Use @GetMapping or @PostMapping, not @RequestMapping)
//     Should take in a Model parameter and take in two other parameters specifying type of search and search term.
//     Use correct annotation and names (corresponding form field names in search.html) to properly pass by Spring Boot
//     If user searches "all" or leave the box empty call findAll() from JobData, otherwise search findByColumnAndValue
//         Store results in a 'jobs' ArrayList
//     Pass 'jobs' into search.html view via the 'model' parameter
//     Pass ListController.columnChoices into the view (as existing 'search' handler does)
    @PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs;

        if (searchTerm.isEmpty() || searchTerm.equalsIgnoreCase("all")) {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);

        return "search";
    }

}