package kr.or.ddit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

public class MemberApp {
    public static void main(String[] args) {
        MemberRepository repo = new MemberRepository();
        Gson gson = new GsonBuilder()
                        .setPrettyPrinting().create();

        List<Member> members = repo.findAll();
        System.out.println("=== 회원 목록 ===");
        System.out.println(gson.toJson(members));

        if (args.length > 0) {
            int id = Integer.parseInt(args[0]);
            Member member = repo.findById(id);
            System.out.println(
                "\n=== 회원 상세 (id=" + id + ") ===");
            System.out.println(gson.toJson(member));
        }
    }
}