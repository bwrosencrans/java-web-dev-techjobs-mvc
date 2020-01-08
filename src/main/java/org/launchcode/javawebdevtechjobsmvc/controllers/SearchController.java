package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @RequestMapping(value = "results")
    public String resultsNoPost(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    @RequestMapping(value = "results", method = RequestMethod.POST)
    public String results(Model model, @RequestBody String request) {
        model.addAttribute("columns", columnChoices);

        ArrayList<Job> jobs = null;

        String[] keyValues = request.split("&");
        if(keyValues.length == 2) {
            String[] searchType = keyValues[0].split("=");
            String[] searchTerm = keyValues[1].split("=");

            jobs = JobData.findByColumnAndValue(searchType[1], searchTerm[1]);
        }
        else if(keyValues.length == 1) {
            String[] searchTerm = keyValues[0].split("=");
            jobs = JobData.findByValue(searchTerm[1]);
        }

        model.addAttribute("jobs", jobs);

        return "search";
    }

}