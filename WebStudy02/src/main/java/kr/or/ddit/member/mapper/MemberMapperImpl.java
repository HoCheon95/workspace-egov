package kr.or.ddit.member.mapper;

import java.util.List;

import kr.or.ddit.member.dto.MemberDto;
import kr.or.ddit.mybatis.MapperProxyGenerator;
import kr.or.ddit.mybatis.MapperTemplate;

// public class MemberMapperImpl implements MemberMapper {

//     // private MapperTemplate<MemberMapper> template = new MapperTemplate<>(MemberMapper.class);
//     private MemberMapper mapper = MapperProxyGenerator.generateMapperProxy(MemberMapper.class);

//     @Override
//     public MemberDto selectMember(String username) {
//         // return template.executeArrounded(mapperProxy -> mapperProxy.selectMember(username));
//         return mapper.selectMember(username);
//     }

//     @Override
//     public List<MemberDto> selectMemberList() {
//         // return template.executeArrounded(mapperProxy -> mapperProxy.selectMemberList());
//         return mapper.selectMemberList();
//     }

//     @Override
//     public int updatePassword(String username, String password) {
//         // return template.executeArrounded(mapperProxy -> mapperProxy.updatePassword(username, password));
//         return mapper.updatePassword(username, password);
//     }
    
// }
