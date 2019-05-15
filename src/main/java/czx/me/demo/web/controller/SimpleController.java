package czx.me.demo.web.controller;
import czx.me.demo.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;;
@Controller
@RequestMapping(value = "simple")
public class SimpleController extends BaseController {


    @GetMapping
    public String index(){
        return this.render("simple/index");
    }

}
