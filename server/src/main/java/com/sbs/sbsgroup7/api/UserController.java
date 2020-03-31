public class User_Controller
package com.sbs.sbsgroup7.api;

        import com.sbs.sbsgroup7.model.Account;
        import com.sbs.sbsgroup7.model.User;
        import com.sbs.sbsgroup7.service.TransactionService;
        import com.sbs.sbsgroup7.service.AccountService;
        import com.sbs.sbsgroup7.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.annotation.Validated;
        import org.springframework.web.bind.annotation.*;
        import java.util.Random;

        Random rand = new Random();

        import javax.validation.constraints.NotNull;
        import java.util.List;

@RequestMapping("/user")
//@RestController
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;



    @Autowired
    public UserController(UserService userService)
    {
        this.userService=userService;
    }

    @PostMapping("/add")
    public void addUser(@NotNull @Validated @RequestBody User user){
        userService.add(user);
    }

    @PutMapping ("/update")
    public void update(@NotNull @Validated @RequestBody User user){
        userService.update(user);
    }

//    @GetMapping(path = "/{id}")
//    public User getUserById(@PathVariable("id") String id) {
//        return userService.findById(id);
//    }

    @DeleteMapping(path="/remove/{id}")
    public void deleteUserById(@PathVariable("id") String id){
        userService.delete(id);
    }

    @GetMapping(path = "/")
    public List<User> getAllUsers(){
        return userService.findAll();
    }
//    public String getAllUsers(Model model) {
//        model.addAttribute("name", "John");
//        return "index";
//    }

    @DeleteMapping(path="/removeAll")
    public void deleteAll(){
        userService.deleteAll();
    }


    @GetMapping("/createAccount")
    public String createAccount(Model model){
        model.addAttribute("account", new Account());
        return "createAccount";
    }

    @PostMapping("/createAccount")
    public String createAccount(@ModelAttribute("request") Account account){
        try {
            User user = userService.getLoggedUser();
            accountService.createAccount(user, account);

            System.out.println(user.getUserId() + " added to account");

            return "accountRequestSent";
        } catch(Exception e) {
            return e.getMessage();
        }

    }
    @GetMapping("/debitAccount")
    public String debitAccount(Model model){
        model.addAttribute("transaction", new Transaction());
        return "debitAccount";
    }
    @PostMapping("/debitAccount")
    public String debitAccount(@ModelAttribute("transactionrequest") Account account){
        try {
            User user = userService.getLoggedUser();

            transactionService.debitAccount(user,account);

            System.out.println(user.getUserId() + " debited from account");

            return "debitRequestSent";
        } catch(Exception e) {
            return e.getMessage();
        }

    }
    @GetMapping("/creditAccount")
    public String creditAccount(Model model){
        model.addAttribute("transaction", new Transaction());
        return "creditAccount";
    }
    @PostMapping("/creditAccount")
    public String creditAccount(@ModelAttribute("transactionrequest") Transaction transaction,@ModelAttribute("accountrequest") Account account){
        try {
            User user = userService.getLoggedUser();

            transactionService.debitAccount(user, transaction,account);

            System.out.println(user.getUserId() + " credited from account");
            return "creditRequestSent";
        } catch(Exception e) {
            return e.getMessage();
        }

    }


}
