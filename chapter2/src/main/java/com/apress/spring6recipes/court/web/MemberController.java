package com.apress.spring6recipes.court.web;

import com.apress.spring6recipes.court.domain.Member;
import com.apress.spring6recipes.court.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    private MemberService memberService;
    public MemberController(MemberService memberService) { this.memberService = memberService; }

    @RequestMapping("/add")
    public String addMember(Model model) {
        model.addAttribute("member", new Member()); model.addAttribute("guests", memberService.list()); return "memberList";
    }

    @RequestMapping(path = { "/remove", "/delete" }, method = RequestMethod.GET) public String removeMember(@RequestParam("memberName") String memberName) {
        memberService.remove(memberName);
        return "redirect:";
    }

    @RequestMapping("/display/{member}")
    public String displayMember(@PathVariable("member") String member, Model model) {
        model.addAttribute("member", memberService.find(member).orElse(null));
        return "member";
    }

    @RequestMapping
    public void memberList() {}

    @PostMapping
    public String submitForm(
        @ModelAttribute("member") Member member,
        BindingResult result,
        Model model
    ) {

    }

    public void memberLogic(String memberName) {}
}
