package com.idea5.playwithme.timeline.controller;

import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.timeline.domain.Timeline;
import com.idea5.playwithme.timeline.domain.TimelineRequestDto;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.timeline.service.TimelineService;
import com.idea5.playwithme.together.domain.Together;
import com.idea5.playwithme.together.service.TogetherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/timeline")
public class TimelineController {
    private final MemberService memberService;
    private final TimelineService timelineMemoService;
    private final TogetherService togetherService;
    private final TimelineService timelineService;
    private List<Timeline> timelines = new ArrayList<>();


    //@PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public String showTimeline(Model model, Principal principal) {
        // principal null 체크
        if (principal == null) {
            log.info("You are Logged out. Login or Signin Please!");
            return "redirect:/";
        }

        // 현재 로그인한 회원 리턴
        Member member = memberService.findMember(principal.getName());

        // 해당 회원의 Timeline 리스트
        timelines = member.getTimelineList();

        // 위 리스트에서 이벤트들만 추출
        List<Event> events = timelines.stream().map(Timeline::getEvent).collect(Collectors.toList());

        // events 리스트 날짜(LocalDateTime) 기준 내림차순 정렬 (최신 날짜가 상단에 오도록)
        Collections.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                if(o1.getDate().isAfter(o2.getDate())){
                    return -1;
                } else if(o1.getDate().isBefore(o2.getDate())){
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        // timelines 리스트 이벤트 날짜 기준 내림차순 정렬
        Collections.sort(timelines, new Comparator<Timeline>() {
            @Override
            public int compare(Timeline o1, Timeline o2) {
                if(o1.getEvent().getDate().isAfter(o2.getEvent().getDate())){
                    return -1;
                } else if(o1.getEvent().getDate().isBefore(o2.getEvent().getDate())){
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        model.addAttribute("events", events);
        model.addAttribute("timelines", timelines);

        return "timeline";
    }

    // 댓글 작성
    @PostMapping("/memo/{together-id}")
    @ResponseBody
    public ResponseEntity memoSave(Principal principal, @PathVariable Long id, @RequestBody TimelineRequestDto timelineRequestDto) {
        // 로그인 회원 리턴
        Member member = memberService.findMember(principal.getName());

        // Together id 리턴
        Together together = togetherService.findById(id);

        Event event = together.getArticle().getBoard().getEvent();

        return ResponseEntity.ok(timelineService.memoSave(member, together, timelineRequestDto, event));
    }

}