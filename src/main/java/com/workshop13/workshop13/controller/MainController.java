package com.workshop13.workshop13.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.workshop13.workshop13.models.Contact;
import com.workshop13.workshop13.util.Contacts;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    Contacts contacts;

    @Value("${dataDir}")
    private String fileDir;

    
    public String index() {
        return "index";
    }

    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("contact", new Contact());
        return "form";
    }

    @PostMapping("/contact")
        // public String contact(@ModelAttribute Contact contact, Model model) {
        public String contact(@Valid Contact contact, BindingResult bindingResult, Model model) {
        // model.addAttribute("contact", contact); 
        contact.invalidDateOfBirth(bindingResult); 
        if(bindingResult.hasErrors()) {
            // System.out.println("hihihihihi\n\n\n\n\n\n"); when there are error this msg will print
            return "form";
        }
        Contacts.saveToFile(contact, fileDir); //after posting filled up form and then save it to the dir
        return "contactInfo";
    }
    
    @GetMapping("/contact/{id}")
    public String getContactInfoByID(Model model, @PathVariable String id) {

        Contact contact = Contacts.getContact(id, fileDir);
        model.addAttribute("contact", contact);
        
        return "contactInfo";
    }

    
    @GetMapping("/list")
    public String contactList(Model model){
        HashMap<String, Contact> names = Contacts.getAllFiles(fileDir);
        model.addAttribute("names", names);
  
        return "list";
    }
    
    
}
