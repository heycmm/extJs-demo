package czx.me.demo.web.controller;

import czx.me.demo.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping(value = {"/", "index"})
public class IndexController extends BaseController {



    @GetMapping
    public String index(Model model){

        return this.render("simple/index");
    }
}
