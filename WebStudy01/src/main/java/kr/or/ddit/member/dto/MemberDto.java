package kr.or.ddit.member.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자의 정보를 가진 domain layer
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberDto {
    private String memId;
    private String memPass;
    private String memName;
    private List<String> memRoles;
}
