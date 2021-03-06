package edu.osumc.bmi.ird.spring.tutorial.controller;

import edu.osumc.bmi.ird.spring.tutorial.config.SecurityUser;
import edu.osumc.bmi.ird.spring.tutorial.persistence.entity.User;
import edu.osumc.bmi.ird.spring.tutorial.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

/**
 * Created by swang on 3/11/2015.
 */
@Controller
public class UserResource {

    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

    private static UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        UserResource.userService = userService;
    }

    public static SecurityUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User loginUser = userService.findByUsername(username);
            return new SecurityUser(loginUser);
        }
        return null;
    }
}
