package kr.or.ddit.buyer.controller;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.dto.BuyerDto;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.simple.ModelAndView;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.validate.groups.InsertGroup;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BuyerInsertController {
    private BuyerService service = new BuyerServiceImpl();

    @RequestMapping("/buyer/create")
    public String formUi(){
        return "buyer/buyerForm";
    }
    
    @RequestMapping(value = "/buyer/create", method = RequestMethod.POST)
    public ModelAndView formDataProcess(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();

        BuyerDto buyer = PopulateUtils.populate(req.getParameterMap(), BuyerDto.class);             
        mav.addAttribute("buyer", buyer);
        Map<String, List<String>> errors = ValidateUtils.validate(buyer, InsertGroup.class);
        mav.addAttribute("errors", errors);

        String lvn = null;
        if(errors.isEmpty()) {
            boolean result = service.createBuyer(buyer);
            if (result) {
                lvn = "redirect:/buyer/detail?what="+buyer.getBuyerId();
            } else {
                mav.addAttribute("message", "알수 없는 오류로 등록 실패. 좀따 다시.");
                lvn = "buyer/buyerForm";
            }
        } else {
            lvn = "buyer/buyerForm";
        }
        mav.setViewName(lvn);
        return mav;
    }
}
