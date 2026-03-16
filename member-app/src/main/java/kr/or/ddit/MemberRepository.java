package kr.or.ddit;

import java.util.ArrayList;
import java.util.List;

import net.datafaker.Faker;

public class MemberRepository {
	
	private Member generateMocMember() {
		Faker faker = new Faker();
		String mockName = faker.name().fullName();
		String mockMail = faker.internet().emailAddress();
		int mockId = faker.number().numberBetween(1, 1001);
		return new Member(mockId, mockName, mockMail);
		
	}

    public List<Member> findAll() {
    	List<Member> mockList = new ArrayList<>();
    	for(int i=0; i<100; i++) {
    		mockList.add(generateMocMember());
    	}
    		
    	return mockList;
    	
    }

    public Member findById(int id) {
        return findAll().stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }
}