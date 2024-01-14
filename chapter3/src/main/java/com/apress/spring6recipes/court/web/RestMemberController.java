package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.domain.Member;
import com.apress.spring6recipes.court.domain.Members;
import com.apress.spring6recipes.court.service.MemberService;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.time.Duration;

@RestController
@RequestMapping("/members")
public class RestMemberController {

    private final MemberService memberService;
    private final TaskExecutor taskExecutor;

    public RestMemberController(MemberService memberService, TaskExecutor taskExecutor) {
        this.memberService = memberService;
        this.taskExecutor = taskExecutor;
    }

    @GetMapping
    public ResponseBodyEmitter getRestMembers() {
        var emitter = new ResponseBodyEmitter();
        taskExecutor.execute(() -> {
            var members = memberService.findAll();

            try {
                for (var member: members) {
                    emitter.send(member);
                    Utils.sleep(Duration.ofMillis(25));
                }
                emitter.complete();
            } catch (IOException ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }



    @GetMapping(value = "/members", produces = MediaType.APPLICATION_ATOM_XML_VALUE)
    public String getRestMembersXml(Model model) {
        prepareModel(model);
        return "xmlmembertemplate";
    }

    @GetMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getRestMembersJson(Model model) {
        prepareModel(model);
        return "jsonmembertemplate";
    }

    private void prepareModel(Model model) {
        var members = new Members();
        members.addMembers(memberService.findAll());
        model.addAllAttributes("members", members);
    }

    @GetMapping("/members")
    @ResponseBody
    public String getRestMemebers(Model model) {
        var members = new Member();
        members.addMembers(memberService.findAll());
        return members;
    }
}
