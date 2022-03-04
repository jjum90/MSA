package com.microservice.unentered;

import com.microservice.unentered.entity.UnenteredCheckHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
/**
 * 멤버 구분 확인 서비스
 */
public class UnenteredService {
    private UnenteredRepository unenteredRepository;

    /**
     * TODO DB 조회로 가입된 멤버인지 확인하기
     * @param memberId
     * @return
     */
    public boolean isUnenteredMember(Integer memberId) {
        unenteredRepository.save(UnenteredCheckHistory.builder()
                .isUnentered(false)
                .memberId(memberId)
                .createdAt(LocalDateTime.now())
                .build()
        );

        return false;
    }
}
