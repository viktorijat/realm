package com.user.realm.controller;

import com.user.realm.model.Realm;
import com.user.realm.model.RealmError;
import com.user.realm.service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    @Autowired
    private RealmService realmService;

    @RequestMapping("/")
    public String realmUsers(Map<String, Object> model) {
        List<Realm> realms = realmService.getAllRealms();
        model.put("realmUsers", realms);
        return "realmUsers";
    }

    @RequestMapping(value = {"/newrealm"})
    public String newUser(ModelMap model) {
        Realm realm = new Realm();
        model.addAttribute("realm", realm);
        model.addAttribute("edit", false);
        return "newrealm";
    }

    @PostMapping(value = {"/newrealm"})
    public String saveRealm(@Valid Realm realm, BindingResult result,
                            ModelMap model) {
        if (result.hasErrors()) {
            return "newrealm";
        }

        Object savedRealm = realmService.saveOrUpdateRealm(realm).get();
        if (savedRealm instanceof RealmError) {
            return "newrealm";
        }

        model.addAttribute("success", "Realm " + realm.getName() + " created successfully");
        return "success";
    }

}
