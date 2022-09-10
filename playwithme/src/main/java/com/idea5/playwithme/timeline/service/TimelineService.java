package com.idea5.playwithme.timeline.service;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.timeline.domain.Timeline;
import com.idea5.playwithme.timeline.dto.TimelineRequestDto;
import com.idea5.playwithme.timeline.repository.TimelineRepository;
import com.idea5.playwithme.together.domain.Together;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TimelineService {
    private final TimelineRepository timelineRepository;
/* entity 직접 사용
    public Timeline create(Together together, String memo, Member member) {
        Timeline timeline = new Timeline();
        timeline.setMemo(memo);
        timeline.setTogether(together);
        timeline.setMember(member);

        timelineRepository.save(timeline);

        return timeline;
    }
*/
    // CREATE, UPDATE
    @Transactional
    public void memoSave(Long id, Member member, Together together, TimelineRequestDto timelineRequestDto, Event event) {
        Timeline timeline = findById(id);
        timeline.update(timelineRequestDto.getMemo());
        timelineRepository.save(timeline);
    }

    public Timeline findById(Long id){
        return timelineRepository.findById(id).orElse(new Timeline());
    }

}