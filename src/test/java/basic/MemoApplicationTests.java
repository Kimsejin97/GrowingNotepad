package basic;

import basic.domain.memo.Memo;
import basic.domain.memo.MemoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemoApplicationTests {

	MemoRepository memoRepository = new MemoRepository();

	@AfterEach
	void afterEach(){
		memoRepository.clear();
	}

	@Test
	@DisplayName("메모저장")
	public void save(){
		//given
		Memo memo = new Memo("Title-1", "본문내용1111", LocalDateTime.now());
		//when
		Memo saveMemo = memoRepository.save(memo);
		//then
		Memo findMemo = memoRepository.findById(memo.getId());
		assertThat(findMemo).isEqualTo(saveMemo);
	}

	@Test
	@DisplayName("메모목록조회")
	public void findAll(){
		//given
		Memo memo1 = new Memo("Title-1", "본문내용1111", LocalDateTime.now());
		Memo memo2 = new Memo("Title-2", "본문내용2222", LocalDateTime.now());
		memoRepository.save(memo1);
		memoRepository.save(memo2);
		//when
		List<Memo> memoList = memoRepository.findAll();
		//then
		assertThat(memoList.size()).isEqualTo(2);
		assertThat(memoList).contains(memo1,memo2);
	}

	@Test
	@DisplayName("메모수정")
	public void update(){
		//given
		Memo memo = new Memo("Title-1", "본문내용1111", LocalDateTime.now());
		Memo saveMemo = memoRepository.save(memo);
		Long memoId = saveMemo.getId();
		//when
		Memo updateMemo = new Memo("Title-2", "본문내용2222", LocalDateTime.now());
		memoRepository.update(memoId,updateMemo);
		Memo findMemo = memoRepository.findById(memoId);
		//then
		assertThat(findMemo.getTitle()).isEqualTo(updateMemo.getTitle());
		assertThat(findMemo.getText()).isEqualTo(updateMemo.getText());
		assertThat(findMemo.getPostTime()).isEqualTo(updateMemo.getPostTime());
	}
}
