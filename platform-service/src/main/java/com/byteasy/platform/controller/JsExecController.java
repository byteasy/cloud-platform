package com.byteasy.platform.controller;

import com.byteasy.Constant;
import com.byteasy.Result;
import com.byteasy.platform.dto.JsRunDTO;
import com.byteasy.platform.util.JsScriptExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.SAPI + "/jsExec")
public class JsExecController {

    @PostMapping("/form/run")
    public Result run(@RequestBody JsRunDTO runDTO) {
        Object data = JsScriptExecutor.runScript("form", runDTO.getInput(), runDTO.getScript() + ";\n" + "(form)");
        runDTO.setOutput(data);
        return Result.success(runDTO);
    }
}
