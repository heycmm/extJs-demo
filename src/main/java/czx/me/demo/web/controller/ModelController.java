package czx.me.demo.web.controller;

import ch.rasc.extclassgenerator.IncludeValidation;
import ch.rasc.extclassgenerator.ModelGenerator;
import ch.rasc.extclassgenerator.OutputFormat;
import czx.me.demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ExtDirectSpring包含一個簡單代碼生成器，根據java類生成對應的JavaScript Model對象代碼。
 * 生成器可以生成Ext JS 4.x和Sencha Touch 2.x兩種類庫適用代碼。
 *
 *
 */
@Controller
public class ModelController {
    @RequestMapping("app/model/User.js")
    public void user5(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
    //添加代碼生成器到程序需要創建一個@Controller註釋的類用ModelGenerator.writeModel把Javascript代碼寫到返回響應中。
        // 注意註解@RequestMapping的屬性url，如果程序通過Ext.Loader按需加載，對應的路徑需要跟真實要存儲的路徑一致。
        ModelGenerator.writeModel(request, response, User.class, OutputFormat.EXTJS5,
                IncludeValidation.BUILTIN, true);
    }
}
