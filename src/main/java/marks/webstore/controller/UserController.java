package marks.webstore.controller;

import marks.webstore.domain.Role;
import marks.webstore.domain.User;
import marks.webstore.domain.UserStore;
import marks.webstore.repos.UserStoreRepo;
import marks.webstore.service.StoreService;
import marks.webstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserStoreRepo userStoreRepo;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("stores", storeService.findAllStores());
        model.addAttribute("allowedToCreateStores", user.getAllowedToCreateStores());
        model.addAttribute("userStores", userStoreRepo.findAllByUser(user).stream()
                .map(UserStore::getStore)
                .collect(Collectors.toList()));

        return "userEdit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.saveUser(user, username, form);

        return "redirect:/user";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("{user}/delete")
    public String delete(User user) {
        userService.deleteUser(user);
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfileSuccess(Model model, @AuthenticationPrincipal User user, @RequestParam(required = false) String success) {
        model.addAttribute("usr", user);
        model.addAttribute("userStores", userStoreRepo.findAllByUser(user).stream()
                .map(UserStore::getStore)
                .collect(Collectors.toList()));
        if (success.equals("Proof"))
            model.addAttribute("success", "Your profile has been edited!");

        return "profile";
    }

    @GetMapping("profile/edit")
    public String editProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("usr", user);
        return "profileEdit";
    }

    @PostMapping("profile/edit")
    public ModelAndView updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String city,
            @RequestParam String email
    ) {
        ModelAndView mav = new ModelAndView();

        if (!userService.findUserByUsername(username) && !username.equals(user.getUsername())) {
            mav.addObject("username_taken", "This username is already taken!");
            mav.addObject("usr", user);
            mav.setViewName("profileEdit");

            return mav;
        }

        if (!userService.loadUserByEmail(email) && !email.equals(user.getEmail())) {
            mav.addObject("usr", user);
            mav.addObject("email_taken", "This email is already taken!");
            mav.setViewName("profileEdit");

            return mav;
        }

        if (!username.equals(user.getUsername()) || !name.equals(user.getName()) ||
                !surname.equals(user.getSurname()) || !city.equals(user.getCity()) ||
                !email.equals(user.getEmail()) || !password.equals(user.getPassword()))
            mav.addObject("success", "Proof");
        else
            mav.addObject("success", "Not");
        mav.setViewName("redirect:/user/profile");

        userService.updateProfile(user, username, password, name, surname, city, email);

        return mav;
    }
}
