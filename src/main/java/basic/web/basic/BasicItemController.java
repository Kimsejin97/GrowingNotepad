package basic.web.basic;

import basic.domain.memo.Memo;
import basic.domain.memo.MemoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/basic")
@RequiredArgsConstructor
public class BasicItemController {
    private final MemoRepository memoRepository;

    @GetMapping("/memoList")
    public String memoList(Model model){
        List<Memo> memoList = memoRepository.findAll();
        model.addAttribute("memoList", memoList);
        return "basic/memoList";
    }

    @GetMapping("/memo/{memoId}")
    public String memo(@PathVariable Long memoId, Model model){
        Memo memo = memoRepository.findById(memoId);
        model.addAttribute("memo",memo);
        return "basic/memo";
    }

    @GetMapping("/addForm")
    public String addForm(){
        return "basic/addForm";
    }

    @PostMapping("/addForm")
    public String addMemo(@ModelAttribute Memo memo, RedirectAttributes redirectAttributes) {
        Memo saveMemo = memoRepository.save(memo);
        redirectAttributes.addAttribute("memoId", saveMemo.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/memo/{memoId}";
    }

    @GetMapping("/memo/{memoId}/edit")
    public String editForm(@PathVariable Long memoId,Model model){
        Memo memo = memoRepository.findById(memoId);
        model.addAttribute("memo",memo);
        return "basic/editForm";
    }

    @PostMapping("/memo/{memoId}/edit")
    public String editMemo(@PathVariable Long memoId,
                           @ModelAttribute Memo memo){
        memoRepository.update(memoId,memo);
        return "redirect:/basic/memo/{memoId}";
    }

    @PostConstruct
    public void init(){
        memoRepository.save(new Memo("새로운 메모를 만들어보세요", "다양한 글을 저장해보세요.", LocalDateTime.now()));
    }
}
