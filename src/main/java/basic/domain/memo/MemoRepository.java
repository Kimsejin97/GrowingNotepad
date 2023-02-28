package basic.domain.memo;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoRepository {

    private static final Map<Long,Memo> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    //save
    public Memo save(Memo memo){
        memo.setId(++sequence);
        memo.setPostTime(LocalDateTime.now());
        store.put(memo.getId(), memo);
        return memo;
    }

    //findById
    public Memo findById(Long id){
        return store.get(id);
    }

    //findAll
    public List<Memo> findAll(){
        return new ArrayList<>(store.values());
    }

    //update
    public void update(Long memoId, Memo Param){
        Memo findMemo = findById(memoId);
        findMemo.setTitle(Param.getTitle());
        findMemo.setText(Param.getText());
        findMemo.setPostTime(LocalDateTime.now());
    }

    //clear
    public void clear(){
        store.clear();
    }
}
